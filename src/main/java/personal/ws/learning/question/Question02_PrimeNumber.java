package personal.ws.learning.question;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class Question02_PrimeNumber {

    /**
     * 【程序2】
     * 题目：判断101-200之间有多少个素数，并输出所有素数。
     */

    /**
     * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
     * 则表明此数不是素数，反之是素数。
     */
    public static void main(String args[]) {
        int i = 0;
        Question02_PrimeNumber q = new Question02_PrimeNumber();
        for (i = 2; i <= 200; i++)
            if(q.iszhishu(i) == true)
                System.out.println(i);
    }

    public int f(int x) {
        if(x == 1 || x == 2)
            return 1;
        else
            return f(x - 1) + f(x - 2);
    }

    public boolean iszhishu(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++)
            if(x % i == 0)
                return false;
        return true;
    }
}