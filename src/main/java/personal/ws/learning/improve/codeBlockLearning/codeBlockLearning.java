package personal.ws.learning.improve.codeBlockLearning;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class codeBlockLearning {
    public static void main(String args[]) {
        new CodeBlockTest();
        new CodeBlockTest("");
        new CodeBlockTest(1);
        System.out.println(CodeBlockTest.getNum());
    }
}

class CodeBlockTest {
    private static int num = 0;

    {
        num++;
    }

    public CodeBlockTest() {
    }

    //调用其他构造方法的构造方法不会重复调用构造代码块
    public CodeBlockTest(String string) {
        this();
    }

    public CodeBlockTest(int i) {
    }

    public static int getNum() {
        return num;
    }
}