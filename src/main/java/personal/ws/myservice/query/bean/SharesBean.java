package personal.ws.myservice.query.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SharesBean implements Serializable {

	private static final long	serialVersionUID	= -973248402540068522L;
	private String				symbol;
	private String				recordTime;
	private String				scode;
	private String				sname;
	private String				sname_eng;
	private String				open_price;
	private String				yesy_price;
	private String				last_price;
	private String				high_price;
	private String				low_price;

	public SharesBean() {
		this.recordTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSname_eng() {
		return sname_eng;
	}

	public void setSname_eng(String sname_eng) {
		this.sname_eng = sname_eng;
	}

	public String getOpen_price() {
		return open_price;
	}

	public void setOpen_price(String open_price) {
		this.open_price = open_price;
	}

	public String getYesy_price() {
		return yesy_price;
	}

	public void setYesy_price(String yesy_price) {
		this.yesy_price = yesy_price;
	}

	public String getLast_price() {
		return last_price;
	}

	public void setLast_price(String last_price) {
		this.last_price = last_price;
	}

	public String getHigh_price() {
		return high_price;
	}

	public void setHigh_price(String high_price) {
		this.high_price = high_price;
	}

	public String getLow_price() {
		return low_price;
	}

	public void setLow_price(String low_price) {
		this.low_price = low_price;
	}
}
