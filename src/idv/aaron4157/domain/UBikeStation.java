package idv.aaron4157.domain;
/**
 * UBike站點JSON資料容器
 * @author aaron
 *
 */
public class UBikeStation {
	private String sno;//站點編號
	private String sna;//站點名稱
	private String tot;//停車格
	private String sbi;//車數
	private String sarea;//行政區
	private String mday;//更新時間
	private String lat;//緯度
	private String lng;//經度
	private String ar;//街道名
	private String sareaen;//行政區ENG
	private String snaen;//站點名ENG
	private String aren;//街道名ENG
	private String bemp;//空位數
	private String act;//?
	
	

	public String getSno() {
		return sno;
	}



	public void setSno(String sno) {
		this.sno = sno;
	}



	public String getSna() {
		return sna;
	}



	public void setSna(String sna) {
		this.sna = sna;
	}



	public String getTot() {
		return tot;
	}



	public void setTot(String tot) {
		this.tot = tot;
	}



	public String getSbi() {
		return sbi;
	}



	public void setSbi(String sbi) {
		this.sbi = sbi;
	}



	public String getSarea() {
		return sarea;
	}



	public void setSarea(String sarea) {
		this.sarea = sarea;
	}



	public String getMday() {
		return mday;
	}



	public void setMday(String mday) {
		this.mday = mday;
	}



	public String getLat() {
		return lat;
	}



	public void setLat(String lat) {
		this.lat = lat;
	}



	public String getLng() {
		return lng;
	}



	public void setLng(String lng) {
		this.lng = lng;
	}



	public String getAr() {
		return ar;
	}



	public void setAr(String ar) {
		this.ar = ar;
	}



	public String getSareaen() {
		return sareaen;
	}



	public void setSareaen(String sareaen) {
		this.sareaen = sareaen;
	}



	public String getSnaen() {
		return snaen;
	}



	public void setSnaen(String snaen) {
		this.snaen = snaen;
	}



	public String getAren() {
		return aren;
	}



	public void setAren(String aren) {
		this.aren = aren;
	}



	public String getBemp() {
		return bemp;
	}



	public void setBemp(String bemp) {
		this.bemp = bemp;
	}



	public String getAct() {
		return act;
	}



	public void setAct(String act) {
		this.act = act;
	}



	public UBikeStation() {
		// TODO Auto-generated constructor stub
	}

}
