package personal.ws.learning.improve.innerStaticClassLearning;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class InnerStaticClassLearning {

    public static void main(String args[]) {
        InnerStaticClassTestBean bean = new InnerStaticClassTestBean();
        //调用静态内部类方法
        boolean isMale = bean.bean.isMale;
        System.out.println(isMale);
    }
}

class InnerStaticClassTestBean {

    public String name = "abc";

    public InnerStaticClassTestInnerBean bean;

    InnerStaticClassTestBean(){
        bean = new InnerStaticClassTestInnerBean();
    }

    public static class InnerStaticClassTestInnerBean {

        public boolean isMale = true;
    }

}