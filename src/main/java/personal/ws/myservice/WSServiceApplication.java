package personal.ws.myservice;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WSServiceApplication {

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(App.class);
//        Banner banner = new ResourceBanner();
//        app.setBanner(banner);
//        app.setShowBanner(true);
        //创建程序入口
        SpringApplicationBuilder sab = new SpringApplicationBuilder(WSServiceApplication.class);
        //是否显示Banner
        sab.bannerMode(Banner.Mode.CONSOLE);

        //sab.properties("flyway.properties");
        //运行Application
        sab.run(args);
    }
}