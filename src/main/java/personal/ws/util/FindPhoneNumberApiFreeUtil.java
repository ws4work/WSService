package personal.ws.util;

import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import personal.ws.util.http.CommonsHttpUtil;

import java.io.IOException;

/**
 * @author CJH
 * @version V1.0
 * @Description: 调用接口查询手机号对应信息工具
 * @date 2016-1-26 下午4:30:37
 * @类全名：com.lemeng.api.utils.FindPhoneNumberUtil
 */
public class FindPhoneNumberApiFreeUtil {

    public static void main(String[] args) throws IOException {
//		Cookie cookie = client.
//		cookie.setName("JSSESSION");
//		cookie.setValue("45FE2FD3D8B3B849E046B2CB6597EFA3");
//		cookie.setDomain("www.apifree.net");
//		client.setCookieStore(cookieStore);
        String phone = "15660190869";
        String response = CommonsHttpUtil.sendDefaultGet(getURL(phone));
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
        return new HttpGet("http://www.apifree.net/mobile/" + phone + ".xml");
    }

    private static String getURL(String phone) {
        return "http://www.apifree.net/mobile/" + phone + ".xml";
    }
}
