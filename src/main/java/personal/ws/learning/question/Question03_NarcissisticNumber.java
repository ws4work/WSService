package personal.ws.learning.question;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class Question3_NarcissisticNumber {

    /**
     * 【程序3】
     * 题目：打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。
     * 例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     */

    /**
     * 程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
     */

    public static void main(String args[]) {
        int i = 0;
        Question3_NarcissisticNumber q = new Question3_NarcissisticNumber();
        for (i = 100; i <= 999; i++)
            if(q.shuixianhua(i) == true)
                System.out.println(i);
    }

    public int f(int x) {
        if(x == 1 || x == 2)
            return 1;
        else
            return f(x - 1) + f(x - 2);
    }

    public boolean iszhishu(int x) {
        for (int i = 2; i <= x / 2; i++)
            if(x % i == 0)
                return false;
        return true;
    }

    public boolean shuixianhua(int x) {
        int i = 0, j = 0, k = 0;
        i = x / 100;
        j = (x % 100) / 10;
        k = x % 10;
        if(x == i * i * i + j * j * j + k * k * k)
            return true;
        else
            return false;

    }
}
