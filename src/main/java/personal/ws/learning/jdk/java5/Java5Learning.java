package personal.ws.learning.jdk.java5;

import java.util.*;
import static java.lang.Math.*;

/**
 * Created by w on 2017/8/1.
 */
public class Java5Learning {

    public static void main(String args[]) {
        //1.泛型
        //generic();

        //2.For-Each循环
        //foreach();

        //3.Autoboxing/unboxing自动装箱
        //boxing();

        //4.Enum枚举
        //enums();

        //5.Varargs可变参数
        //varargs();

        //6.Static Imports静态导入
        static_imports();
    }

    /**
     * 加入泛型:允许指定集合里元素的类型，这样你可以得到强类型在编译时刻进行类型检查的好处
     */
    public static void generic() {
        /**
         * Old:无检查
         */
        //Collection<String> c = new ArrayList();
        //c.add(new Date());

        /**
         * New:报错
         */
        //Collection<String> c = new ArrayList();
        //c.add(new Date());

    }

    /**
     * For-Each循环得加入简化了集合的遍历。
     */

    public static void foreach() {

        List<String> list = new ArrayList<>();
        list.add(String.valueOf(Math.random()));
        list.add(String.valueOf(Math.random()));
        list.add(String.valueOf(Math.random()));

        /**
         * Old
         */
        for (Iterator i = list.iterator(); i.hasNext(); ) {
            String str = (String) i.next();
            System.out.println(str);
        }
        /**
         * New
         */
        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     * 自动装包/拆包大大方便了基本类型数据和它们包装类地使用。
     * 自动装包：基本类型自动转为包装类.(int >> Integer)
     * 自动拆包：包装类自动转为基本类型.(Integer >> int)
     */
    public static void boxing() {
        int a = 3;
        Collection<Integer> collection = new ArrayList();

        /**
         * Old
         */
        collection.add(new Integer(a));
        Integer b = new Integer(2);
        collection.add(new Integer(b.intValue())+2);

        /**
         * New
         */
        collection.add(a);//自动转换成Integer.
        Integer c = new Integer(2);
        collection.add(c + 2);


    }

    /**
     * 枚举:定义一个枚举类型
     */
    public static void enums(){
        /**
         * New
         */
        for (Color c : Color.values()) {
            System.out.println(c);
        }
    }

    /**
     * 可变参数:可变参数使程序员可以声明一个接受可变数目参数的方法。注意，可变参数必须是函数声明中的最后一个参数。
     */
    public static void varargs(String ...params) {
        for (String str:params) {
            System.out.println(str);
        }
    }

    /**
     * 静态导入:
     * 要使用用静态成员（方法和变量）我们必须给出提供这个方法的类。
     * 使用静态导入可以使被导入类的所有静态变量和静态方法在当前类直接可见，使用这些静态成员无需再给出他们的类名。
     */
    public static void static_imports() {
        /**
         * Old
         */
        double r1 = Math.sin(Math.PI*2);
        /**
         * New
         */
        double r2 = sin(PI * 2);
    }

}
enum Color{
    Red,White,Blue
}