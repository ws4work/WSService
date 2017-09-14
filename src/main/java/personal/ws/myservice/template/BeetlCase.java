package personal.ws.myservice.template;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.ws.myservice.template.bean.BeetlTestBean;
import personal.ws.util.template.BeetlUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/4
 * @project：WSService
 */
@Scope("prototype")
@Controller
@RequestMapping("beetl")
public class BeetlCase {

    @RequestMapping("show")
    @ResponseBody
    public String show(){
        Template t = null;
        //查询模板
        try {
            t = BeetlUtil.loadTemplate("/template","beetl.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //绑定数据
        t.binding("name", "Petter");
        t.binding("sex", 1);
        //绑定list
        List<String> list = new ArrayList<>();
        list.add("Susan");
        list.add("Jan");
        list.add("Tom");
        t.binding("userList", list);
        //绑定对象
        BeetlTestBean btb = new BeetlTestBean();
        btb.setName("abc");
        btb.setValue("cba");
        t.binding("btb",btb);
        t.binding("salary",BigDecimal.valueOf(13564.135f));
        String str = null;
        //输出到文件
        try {
            str = BeetlUtil.template2File(t,"new.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);
        return str;
    }


}
