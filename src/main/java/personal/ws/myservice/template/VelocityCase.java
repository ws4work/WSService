package personal.ws.myservice.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/4
 * @project：WSService
 */
@Scope("prototype")
@Controller
@RequestMapping("velocity")
public class VelocityCase {

    Template template = null;
    VelocityContext context = null;

    @RequestMapping("show")
    @ResponseBody
    public String show() {
        Velocity.init(loadProperties());
        context = new VelocityContext();
        context.put("info", "oneDay");
        ArrayList<String> list = new ArrayList<>();
        list.add("num1");
        list.add("num2");
        list.add("num3");
        context.put("list", list);
        //选择要用到的模板
        String path = "/template/velocity.vm";
        loadTemplate(path);
        StringWriter sw = new StringWriter();
        //合并输出
        template.merge(context, sw);
        return String.valueOf(sw);
    }

    public Properties loadProperties() {
        Properties p = new Properties();
        p.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return p;
    }

    public void loadTemplate(String path){
        try {
            template = Velocity.getTemplate(path);
        } catch (ResourceNotFoundException rnfe) {
            // couldn't find the template
        } catch (ParseErrorException pee) {
            // syntax error : problem parsing the template
        } catch (MethodInvocationException mie) {
            // something invoked in the template
            // threw an exception
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
