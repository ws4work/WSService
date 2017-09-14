package personal.ws.util.template;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/6
 * @project：WSService
 */
public class BeetlUtil {

    /**
     * 获取模板的GroupTemplate
     * @param root_path
     * @return
     * @throws IOException
     */
    public static GroupTemplate loadGroupTemplate(String root_path) throws IOException {
        Configuration cfg = Configuration.defaultConfiguration();
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(root_path);
        return new GroupTemplate(resourceLoader, cfg);
    }

    public static Template loadTemplate(String root_path,String template_path) throws IOException {
        GroupTemplate group_template = loadGroupTemplate(root_path);
        return group_template.getTemplate(template_path);
    }

    public static String template2File(Template template,String file_path) throws IOException {
        String str = template.render();
        File file = new File(file_path);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(str.getBytes());
        return str;
    }
}
