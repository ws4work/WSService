package personal.ws.learning.jdk.java7;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by w on 2017/8/1.
 */
public class Java7Learning {
    public static void main(String args[]){
        //1.switch
        //new_switch();

        //2.EnviromentApi
        //enviroment();

        //3.try-with-resources
        //tryWithResources();

        //4.

    }

    public static void new_switch(){
        String s = "oneDay";
        switch (s) {
            case "oneDay" :
                System.out.println("oneDay");
            case "test1" :
                System.out.println("test1");
                break ;
            default :
                System.out.println("break");
                break ;
        }
    }

    public static void enviroment(){
//        File javaIoTempDirmp = System.getJavaIoTempDir(); // IO临时文件夹
//        File javaHomeDir = System.getJavaHomeDir(); // JRE的安装目录
//        File userHomeDir = System.getUserHomeDir(); // 当前用户目录
//        File userDir = System.getUserDir(); // 启动java进程时所在的目录
    }

    public static void tryWithResources() throws IOException {
        try( InputStream ins = new FileInputStream("/home/biezhi/a.txt") ){
            char charStr = (char) ins.read();
            System.out.print(charStr);
        }
    }

    public static void readWriteFile() throws IOException {
        //你可以使用Files类快速实现文件操作，例如读取文件内容:
        byte[] data    = Files.readAllBytes(Paths.get("/home/biezhi/a.txt"));
        String content = new String(data, StandardCharsets.UTF_8);

        //如果希望按照行读取文件，可以调用
        List<String> lines = Files.readAllLines(Paths.get("/home/biezhi/a.txt"));

        //反之你想将字符串写入到文件可以调用
        Files.write(Paths.get("/home/biezhi/b.txt"), "Hello JDK7!".getBytes());
    }
}
