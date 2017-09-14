package personal.ws.myservice.template;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/4
 * @project：WSService
 */
@Scope("prototype")
@Controller
@RequestMapping("freemarker")
public class FreeMarkerCase {

    @RequestMapping("show")
    @ResponseBody
    public String show(){

        return null;
    }

}
