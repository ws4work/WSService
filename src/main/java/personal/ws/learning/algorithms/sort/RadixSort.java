package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:基数排序 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面 补零。
 * 然后，从最低位开始，依次进行一次排序。
 * 这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
 * @author: 王上
 */
public class RadixSort extends CommonSort {

    public static void main(String args[]) {
        RadixSort s = new RadixSort();
        s.sort(a);
        s.print(a);
    }

    public void sort(int[] array) {
        //首先确定排序的趟数;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] > max){
                max = array[i];
            }
        }

        int time = 0;
        //判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }

        //建立10个队列;
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }

        //进行time 次分配和收集;
        for (int i = 0; i < time; i++) {
            //分配数组元素;
            for (int j = 0; j < array.length; j++) {
                //得到数字的第time+1 位数;
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }

            int count = 0;//元素计数器;

            //收集队列元素;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }
}
