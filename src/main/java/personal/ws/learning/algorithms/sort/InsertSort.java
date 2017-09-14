package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

/**
 * @Description:快速插入排序 在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
 * 好顺序的，现在要把第n 个数插到前面的有序数中，使得这 n个数
 * 也是排好顺序的。如此反复循环，直到全部排好顺序。
 * @author: 王上
 * @date: 2017/9/13
 * @project：WSService
 */
public class InsertSort extends CommonSort {

    public static void main(String args[]) {
        InsertSort s = new InsertSort();
        s.sort(a);
        s.print(a);
    }

    @Override
    protected void sort(int[] a) {
        int temp = 0;
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            temp = a[i];
            for (; j >= 0 && temp < a[j]; j--) {
                a[j + 1] = a[j];  //将大于temp 的值整体后移一个单位
            }
            a[j + 1] = temp;
        }
    }
}
