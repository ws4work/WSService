package personal.ws.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

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
		HttpClient client = new HttpClient();
		String phone = "15660190869";
		HttpMethod method = getGetMethod(phone); // 使用 POST 方式提交数据
		client.executeMethod(method);
		System.out.println(method.getStatusLine()); //打印服务器返回的状态
		String response = new String(method.getResponseBodyAsString().getBytes("utf-8"));
		JSONObject json = JSONObject.fromObject(response);
		System.out.println(json);//打印结果页面
		method.releaseConnection();
	}

	/**
	 * 查询电话信息
	 * 
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	public static JSONObject findPhoneInfo(String phone) throws IOException {
		HttpClient client = new HttpClient();
		HttpMethod method = getGetMethod(phone); // 使用 POST 方式提交数据
		client.executeMethod(method);
		System.out.println(method.getStatusLine()); //打印服务器返回的状态
		String response = new String(method.getResponseBodyAsString());
		JSONObject json = JSONObject.fromObject(response);
		System.out.println(json);//打印结果页面
		method.releaseConnection();
		return json;
	}

	private static HttpMethod getGetMethod(String phone) {
		return new GetMethod("http://api.k780.com:88/?app=phone.get&phone=" + phone + "&appkey=20677&sign=06d68f9c13324b16d48f39cf055e90c3&format=json");
	}
}
