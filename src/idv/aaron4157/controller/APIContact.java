/**
 * 
 */
package idv.aaron4157.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

/**
 *  應對前端CORS 撰寫servlet從後端進行介接
 *  網址: /ntpubike/json
 * @author aaron
 *
 */
@WebServlet(urlPatterns="/ntpubike/json", asyncSupported=true)
public class APIContact extends HttpServlet {
			
	public APIContact() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 接收網址查詢 回覆JSON資料
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//發出異步請求 在其中處理資料與轉址
		AsyncContext ac = request.startAsync();
		QryThread qry = new QryThread(ac);
		qry.start();				
	}

}

class QryThread extends Thread{
	private AsyncContext aCxt;	
	private String urlString = "https://data.ntpc.gov.tw/api/datasets/71CD1490-A2DF-4198-BEF1-318479775E8A/json";	
	private JsonArray data = new JsonArray();

	
	
	QryThread(AsyncContext aCxt) {
		this.aCxt = aCxt;
	}


	@Override
	public void run() {
		HttpServletRequest request1 =(HttpServletRequest)aCxt.getRequest();
		HttpServletResponse response1=(HttpServletResponse)aCxt.getResponse();
		Gson gson = new Gson();
		int size=60;
		
		try {
		//執行11次查詢	
			for(int page=0; page < 11; page++) {
	
				//準備參數
				String urlString = String.format("%s?page=%s&size=%s", this.urlString, page, size);
				URL urlObj = new URL(urlString);	
				HttpsURLConnection connxn = (HttpsURLConnection)urlObj.openConnection();
				connxn.setRequestMethod("GET");
				connxn.setUseCaches(true);
				connxn.setRequestProperty("Accept", "application/json");
				connxn.setRequestProperty("Accept-Chrset", "UTF-8");			
				//送出查詢
				connxn.connect();
				if(connxn.getResponseCode() == 200) {
					//成功 解析資料(=JSON 陣列)				
					InputStream iS = connxn.getInputStream();					
					JsonReader jsonReader  = gson.newJsonReader(new InputStreamReader(iS,"UTF-8"));
					JsonArray rawdata = gson.fromJson(jsonReader, JsonArray.class);
					data.addAll(rawdata);
					
					jsonReader.close();
					iS.close();
														
				} else {
					throw new IOException("連接出錯");
				}
				
			}
			
			//完成 派送畫面A (檔案位於ntpqry之下)
			request1.setAttribute("data", data);				
			RequestDispatcher dispxr = request1.getRequestDispatcher("ntpqry.jsp");
			dispxr.forward(request1, response1);
		
		} catch (Exception e) {
			// 有狀況 主控台顯示 派送錯誤畫面
			e.printStackTrace();						
		}
				
		aCxt.complete();
	}
	
	
	
}
