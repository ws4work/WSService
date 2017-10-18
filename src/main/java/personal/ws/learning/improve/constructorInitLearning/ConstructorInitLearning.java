package personal.ws.learning.improve.constructorInitLearning;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class ConstructorInitLearning {
    public static void main(String args[]) {
        /**
         * 加载顺序:
         * 1.子类构造方法调用父类构造方法
         * 2.父类构造方法调用子类getNum方法(子类自有属性未初始化)
         * 3.子类构造方法初始化自身属性,并继续执行
         */
        ConstructorInitSon son = new ConstructorInitSon(5000);
    }
}

abstract class ConstructorInitFather {
    public final static int num = 110;

    ConstructorInitFather() {
        int getNum = getNum();
        System.out.println("父类构造方法中调用:" + getNum);
    }

    abstract protected int getNum();
}

class ConstructorInitSon extends ConstructorInitFather {
    public int sonnum = 120;

    ConstructorInitSon(int number) {
        sonnum = number;
        int getNum = getNum();
        System.out.println("子类构造方法中调用:" + getNum);

    }

    @Override
    protected int getNum() {
        System.out.println("子类数字:" + sonnum);
        System.out.println("父类数字:" + num);
        return sonnum + num;
    }
}