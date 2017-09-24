package personal.ws.learning.question;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class Question6_CommonDivisor {

    /**
     * 【程序6】
     * 题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
     */
    /**
     * 1.程序分析：利用辗除法。
     */

    public static void main(String args[]) {
        commonDivisor(24, 32);

        int a = 24;
        int b = 32;
        int c = gcd(a, b);
        System.out.println("最小公倍数：" + a * b / c + "\n最大公约数：" + c);
    }

    //最大公约数：
    public static int commonDivisor(int M, int N) {
        if(N < 0 || M < 0){
            System.out.println("ERROR!");
            return -1;
        }
        if(N == 0){
            System.out.println("the biggest common divisor is :" + M);
            return M;
        }
        return commonDivisor(N, M % N);
    }

    //最小公倍数和最大公约数：
    public static int gcd(int m, int n) {
        while (true) {
            if((m = m % n) == 0)
                return n;
            if((n = n % m) == 0)
                return m;
        }
    }
}
