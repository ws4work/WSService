package personal.ws.learning.algorithms.calculate;

import java.util.LinkedList;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/15
 * @project：WSService
 */
public class JosephCircle {

    static final int nums = 41;// 总共多少人
    static final int killMan = 3;// 数到3则被杀

    public static void main(String[] args) {

        jufehus(2,41,3);
        long startTime = System.currentTimeMillis(); // 获取开始时间
        process(20, 1, 3);
        System.out.println(removedStr.substring(0, removedStr.length() - 1));
        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    public static void jufehus(int alive,int nums,int killMan) {

        int[] man = new int[nums];// 未被杀的都被标记为0
        int pos = -1;// 数组角标
        int i = 0;
        int count = 1;// 杀到第几个记录值

        while (count <= nums) {

            do {
                pos = (pos + 1) % nums;// 循环标记
                if(man[pos] == 0)
                    i++;

                if(i == killMan){
                    i = 0; // 重置
                    break;// 找到了被杀的位置，跳出循环，进行标记
                }

            } while (true);

            man[pos] = count;
            count++;
        }

        // 显示不被杀的位置
        alive = count - alive;
        for (int j = 0; j < man.length; j++) {

            if(man[j] >= alive)
                System.out.println("不被杀的位置是->" + (j + 1));

        }

    }


    public static void process(int n, int k, int m) {

        // 构建一个list，存放人数
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (i + k > n) {
                list.add(i + k - n);
            } else {
                list.add(i + k);
            }
        }
        int count = 1;// 记录数的人数
        cycleCal(list, count, m);
    }

    private static StringBuffer removedStr = new StringBuffer("");// 记录出列的数字

    public static void cycleCal(LinkedList<Integer> list, int count, int m) {
        int len = list.size();
        if (len > 1) {
            for (int i = 0; i < len; i++) {
                if (count == m) {// 第m个时，remove
                    removedStr.append(list.get(i)).append(",");
                    list.remove(i);
                    len = list.size();
                    i--;
                    count = 0;
                }
                count++;
            }
            cycleCal(list, count, m);
        } else {
            if (len != 0) {
                removedStr.append(list.get(0)).append(",");
            }
        }
    }
    private int josephus(int n, int m) {
        if(n == 1) {
            return 0;
        }
        else {
            return (josephus(n-1, m) + m) % n;
        }
    }

}
