package personal.ws.util;

import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import personal.ws.util.http.CommonsHttpUtil;

import java.io.IOException;

/**
 * @Description: 调用接口查询手机号对应信息工具
 * @author CJH
 * @date 2016-1-26 下午4:30:37
 * @version V1.0
 * @类全名：com.lemeng.api.utils.FindPhoneNumberUtil
 */
public class FindPhoneNumberUtil {

	public static void main(String[] args) throws IOException {
        String phone = "15660125869";
        String response = CommonsHttpUtil.sendDefaultGet(getURL(phone));
        System.out.println(response);
        JSONObject json = JSONObject.fromObject(response);
        System.out.println(json);//打印结果页面
    }

	/**
	 * 查询电话信息
	 * 
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	public static JSONObject findPhoneInfo(String phone) throws IOException {
//		HttpClient client = new HttpClient();
//		HttpMethod method = getGetMethod(phone); // 使用 POST 方式提交数据
//		client.executeMethod(method);
//		System.out.println(method.getStatusLine()); //打印服务器返回的状态
//		String response = new String(method.getResponseBodyAsString());
//		JSONObject json = JSONObject.fromObject(response);
//		System.out.println(json);//打印结果页面
//		method.releaseConnection();
//		return json;
        return null;
    }

    private static HttpGet getGetMethod(String phone) {
        return new HttpGet("http://api.k780.com:88/?app=phone.get&phone=" + phone + "&appkey=20677&sign=06d68f9c13324b16d48f39cf055e90c3&format=json");
    }

    private static String getURL(String phone) {
        return "http://api.k780.com:88/?app=phone.get&phone=" + phone + "&appkey=20677&sign=06d68f9c13324b16d48f39cf055e90c3&format=json";
    }
}
