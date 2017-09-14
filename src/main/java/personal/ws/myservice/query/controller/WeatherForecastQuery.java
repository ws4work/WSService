package personal.ws.myservice.query.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.ws.util.http.HttpConnectionUtil;

@SpringBootApplication
@Controller
@RequestMapping("/weather")
public class WeatherForecastQuery {

	@RequestMapping("/query")
    @ResponseBody
    public String query(@RequestParam String weaid) {
		String urlstr = "http://api.k780.com/";
		String app = "weather.future";
		String appkey = "20678";
		String sign = "16d2e87afc1db5c10dec2a3e1f8729f5";
		String format = "json";
		String params = String.format("app=%s&weaid=%s&appkey=%s&sign=%s&format=%s",
				app, weaid, appkey, sign, format);
		String fullUrl = urlstr + "?" + params;
		String response = HttpConnectionUtil.sendGet(fullUrl);
		System.out.println(response);
		return response;
	}
}
