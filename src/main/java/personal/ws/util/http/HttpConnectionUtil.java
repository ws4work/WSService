package personal.ws.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnectionUtil {

	/**
	 * 发送get请求
	 */
	public static String sendGet(String fullUrl) {
		PrintWriter out = null;
		BufferedReader in = null;
		String response = "";
		try {
			URL url = new URL(fullUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			// 获取URLConnection对象对应的输出流  
			out = new PrintWriter(urlConnection.getOutputStream());
			// 发送请求参数  
			out.print("");
			// flush输出流的缓冲  
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应  
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				response += line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return response;
	}
}
