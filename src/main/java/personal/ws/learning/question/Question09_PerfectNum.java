package personal.ws.learning.question;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class Question9_PerfectNum {

    /**
     * 【程序9】
     * 题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3.编程   找出1000以内的所有完数。
     */

    public static void main(String[] args) {
        int s;
        for (int i = 1; i <= 1000; i++) {
            s = 0;
            for (int j = 1; j < i; j++)
                if(i % j == 0)
                    s = s + j;
            if(s == i)
                System.out.print(i + " ");
        }
        System.out.println();
    }
}
