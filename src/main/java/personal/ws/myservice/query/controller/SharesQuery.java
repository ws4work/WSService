package personal.ws.myservice.query.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import personal.ws.myservice.query.bean.SharesBean;
import personal.ws.util.http.HttpConnectionUtil;

@SpringBootApplication
@Controller
@RequestMapping("/gupiao")
public class SharesQuery {

	@RequestMapping("/query")
	@ResponseBody
	public String query(@RequestParam String symbol) {
		String urlstr = "http://api.k780.com/";
		String app = "finance.stock_realtime";
		//String symbol = "sh600000";//股票代码
		String appkey = "20678";
		String sign = "16d2e87afc1db5c10dec2a3e1f8729f5";
		String format = "json";
		String params = String.format("app=%s&symbol=%s&appkey=%s&sign=%s&format=%s",
				app, symbol, appkey, sign, format);
		String fullUrl = urlstr + "?" + params;
		String response = HttpConnectionUtil.sendGet(fullUrl);
		System.out.println(response);
		JSONObject respResult = JSONObject.fromObject(response);
		String suc = respResult.getString("success");
		String sendResp = "";
		if ("1".equals(suc)) {
			JSONObject result = respResult.getJSONObject("result");
			JSONObject lists = result.getJSONObject("lists");
			JSONObject datas = lists.getJSONObject(symbol);
			String scode = datas.getString("scode");/* 股票编号 */
			String sname = datas.getString("sname");/* 股票名称 */
			String sname_eng = datas.getString("sname_eng");/* 股票名称英文 */
			String open_price = datas.getString("open_price");/* 开盘价 */
			String yesy_price = datas.getString("yesy_price");/* 昨收价 */
			String last_price = datas.getString("last_price");/* 当前价 */
			String high_price = datas.getString("high_price");/* 最高价 */
			String low_price = datas.getString("low_price");/* 最低价 */
			//保存数据
			SharesBean shares = new SharesBean();
			shares.setSymbol(symbol);
			shares.setScode(scode);
			shares.setSname(sname);
			shares.setSname_eng(sname_eng);
			shares.setOpen_price(open_price);
			shares.setYesy_price(yesy_price);
			shares.setLast_price(last_price);
			shares.setHigh_price(high_price);
			shares.setLow_price(low_price);
			sendResp = JSONObject.fromObject(shares).toString();
		}else{

		}
		return sendResp;
	}
}
