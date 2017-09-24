package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

/**
 * @Description:简单选择排序 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一
 * 个数比较为止。
 * @author: 王上
 */
public class SelectSort extends CommonSort {

    public static void main(String args[]) {
        SelectSort s = new SelectSort();
        s.sort(a);
        s.print(a);
    }

    @Override
    protected void sort(int[] a) {
        int position = 0;
        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            position = i;
            int temp = a[i];
            for (; j < a.length; j++) {
                if(a[j] < temp){
                    temp = a[j];
                    position = j;
                }
            }
            a[position] = a[i];
            a[i] = temp;
        }
    }
}
