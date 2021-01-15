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
 *  應對前端CSRF 撰寫servlet從後端進行介接
 *  網址: /ntpubike/json?page=0&size=5
 * @author aaron
 *
 */
@WebServlet(urlPatterns="/ntpubike/json0", asyncSupported=true)
public class APIContact2 extends HttpServlet {
			
	public APIContact2() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 接收網址查詢 回復JSON資料
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 參數處理
		request.setCharacterEncoding("UTF-8");		
		int pageNum = 0;
		int sizeNum = 10;		
		try {
			//try parse query strings, or use default value
			pageNum = Integer.parseInt(request.getParameter("page"));
			sizeNum = Integer.parseInt(request.getParameter("size"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//發出異步請求 在其中一併處理資料
		AsyncContext ac = request.startAsync();
		QryProcess qry = new QryProcess(ac, pageNum, sizeNum);
		qry.start();				
		//如果返回資料處理 使用callable介面
	}

}

class QryProcess extends Thread{
	private AsyncContext aCxt;
	private String urlString = "https://data.ntpc.gov.tw/api/datasets/71CD1490-A2DF-4198-BEF1-318479775E8A/json";
	//private List<UBikeStation> data;
	private JsonArray data;
	
	QryProcess(AsyncContext aCxt, int page, int size) {
		this.aCxt = aCxt;
		this.urlString = String.format("%s?page=%s&size=%s", this.urlString, page, size);
	}


	@Override
	public void run() {
		HttpServletRequest request1 =(HttpServletRequest)aCxt.getRequest();
		HttpServletResponse response1=(HttpServletResponse)aCxt.getResponse();
				
		try {
			//準備參數
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
				
				Gson gson = new Gson();
				
				JsonReader jsonReader  = gson.newJsonReader(new InputStreamReader(iS,"UTF-8"));
				data = gson.fromJson(jsonReader, JsonArray.class);
				/*
				 * jsonReader.beginArray(); while(jsonReader.hasNext()) { UBikeStation item =
				 * gson.fromJson(jsonReader, UBikeStation.class); data.add(item); }
				 * jsonReader.endArray();
				 */
				jsonReader.close();
				iS.close();
				
				//成功 派送畫面A (位於ntpqry之下)
				request1.setAttribute("data", data);				
				RequestDispatcher dispxr = request1.getRequestDispatcher("ntpqry.jsp");
				dispxr.forward(request1, response1);
				
			} else {
				//失敗 派送畫面B
				//InputStream iS = connxn.getErrorStream();
				request1.setAttribute("msg", connxn.getResponseMessage() );
				RequestDispatcher dispxr = request1.getRequestDispatcher("/error.jsp");
				dispxr.forward(request1, response1);
			}
			
			
		} catch (Exception e) {
			// 有狀況 主控台顯示
			e.printStackTrace();
			
		}
		
		aCxt.complete();
	}
	
	
	
}
