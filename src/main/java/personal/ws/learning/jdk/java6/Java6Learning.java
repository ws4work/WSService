package personal.ws.learning.jdk.java6;

import com.sun.net.httpserver.HttpServer;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by w on 2017/8/1.
 */
public class Java6Learning {

    public static void main(String args[]) {

        //1.STAX解析器
        //stax();

        //2.Compiler API
        //compilerApi();

        //3.轻量级Http Server
        //httpServer();

        //4.Web Services metadata Web服务元数据

    }

    /**
     * STAX解析xml
     */
    public static void stax() {
        //StaxTest;
    }

    /**
     * Compiler API结合反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的特征。
     * 这个特性对于某些需要用到动态编译的应用程序相当有用， 比如JSP Web Server，
     * 当我们手动修改JSP后，是不希望需要重启Web Server 才可以看到效果的，
     * 这时候我们就可以用Compiler API来实现动态编译JSP文件，当然，现在的JSP Web Server也是支持JSP热部署的，
     * 现在的JSP Web Server通过在运行期间通过Runtime.exec 或ProcessBuilder 来调用javac 来编译代码，
     * 这种方式需要我们产生另一个进程去做编译工作，不够优雅而且容易使代码依赖与特定的操作系统；
     * Compiler API通过一套易用的标准的API提供了更加丰富的方式去做动态编译,而且是跨平台的。
     */
    public static void compilerApi(){
        try {
            JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);

            File javaFile = new File("Hello.java");
            Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);
            jc.getTask(null, sjfm, null, null, null, fileObjects).call();
            // Add more compilation tasks
            sjfm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支持Http 和Https 协议,提供了HTTP1.1的部分实现，没有被实现的那部分可以通过扩展已有的Http Server API来实现,
     * 程序员必须自己实现HttpHandler接口,HttpServer 会调用HttpHandler 实现类的回调方法来处理客户端请求,
     * 在这里,我们把一个Http 请求和它的响应称为一个交换,包装成HttpExchange类,
     * HttpServer负责将HttpExchange传给HttpHandler实现类的回调方法
     */
    public static void httpServer(){
        try {
            HttpServer hs = HttpServer.create(new InetSocketAddress(8888), 0);//设置HttpServer的端口为8888
            hs.createContext("/lj", new MyHandler());//用MyHandler类内处理到/lj的请求
            hs.setExecutor(null); // creates a default executor
            hs.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
