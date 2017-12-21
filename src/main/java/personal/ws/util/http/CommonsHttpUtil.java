package personal.ws.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CommonsHttpUtil {

    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com";
        HttpClient client = HttpClients.createMinimal(new PoolingHttpClientConnectionManager());
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = getCommonRequestConfig(30000, 30000, 30000, true);
        defaultHeader(httpGet);
        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = client.execute(httpGet);
        System.out.println(httpResponse.getStatusLine()); //打印服务器返回的状态
        String response = EntityUtils.toString(httpResponse.getEntity());
        httpGet.releaseConnection();
    }

    /**
     * 发送默认Get请求
     *
     * @param url get请求的地址
     * @return
     * @throws IOException
     */
    public static String sendDefaultGet(String url) throws IOException {
        return sendGet(url, 30000, 30000, 30000, true);
    }

    /**
     * 发送Get请求
     *
     * @param url get请求的地址
     * @param timeout 链接超時時間
     * @param requestTimeout 请求超时时间
     * @param socketTimeout 套接字超时时间
     * @param redirectable 是否可被重定向
     * @return
     * @throws IOException
     */
    public static String sendGet(String url, int timeout, int requestTimeout, int socketTimeout, boolean redirectable) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = getCommonRequestConfig(timeout, requestTimeout, socketTimeout, redirectable);
        defaultHeader(httpGet);
        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = client.execute(httpGet);
        System.out.println(httpResponse.getStatusLine()); //打印服务器返回的状态
        String response = EntityUtils.toString(httpResponse.getEntity());
        httpGet.releaseConnection();
        return response;
    }

    /**
     * 发送Get请求
     *
     * @param url get请求的地址
     * @param timeout 链接超時時間
     * @param requestTimeout 请求超时时间
     * @param socketTimeout 套接字超时时间
     * @param redirectable 是否可被重定向
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, int timeout, int requestTimeout, int socketTimeout, boolean redirectable) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = getCommonRequestConfig(timeout, requestTimeout, socketTimeout, redirectable);
        defaultConfig(httpPost);
        httpPost.setConfig(requestConfig);
        HttpResponse httpResponse = client.execute(httpPost);
        System.out.println(httpResponse.getStatusLine()); //打印服务器返回的状态
        String response = EntityUtils.toString(httpResponse.getEntity());
        httpPost.releaseConnection();
        return response;
    }

    /**
     * 默认配置
     *
     * @param request
     */
    private static void defaultConfig(HttpRequestBase request) {
        RequestConfig requestConfig = getDefaultRequestConfig();
        request.setConfig(requestConfig);
        defaultHeader(request);
    }

    /**
     * 设置默认的配置
     * timeout=30000
     * requestTimeout=30000
     * socketTimeout=30000
     * redirectable=ture
     *
     * @param request
     */
    private static void defaultRequestConfig(HttpRequestBase request) {
        request.setConfig(getDefaultRequestConfig());
    }

    /**
     * 设置默认头信息
     * ContentType=UTF-8
     *
     * @param request
     */
    private static void defaultHeader(HttpRequestBase request) {
        request.addHeader("ContentType", "UTF-8");
    }

    /**
     * 配置链接设置
     * timeout=30000
     * requestTimeout=30000
     * socketTimeout=30000
     * redirectable=ture
     *
     * @return RequestConfig
     */
    private static RequestConfig getDefaultRequestConfig() {
        return getCommonRequestConfig(30000, 30000, 30000, true);
    }

    /**
     * 配置链接设置
     *
     * @param timeout
     * @param requestTimeout
     * @param socketTimeout
     * @param redirectable
     * @return
     */
    private static RequestConfig getCommonRequestConfig(int timeout, int requestTimeout, int socketTimeout, boolean redirectable) {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(timeout);
        builder.setConnectionRequestTimeout(requestTimeout);
        builder.setSocketTimeout(socketTimeout);
        builder.setRedirectsEnabled(redirectable);
        return builder.build();
    }
}
