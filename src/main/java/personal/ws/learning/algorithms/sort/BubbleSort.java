package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

/**
 * @Description:冒泡排序 在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对
 * 相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的
 * 数比较后发现它们的排序与排序要求相反时，就将它们互换。
 * @author: 王上
 */
public class BubbleSort extends CommonSort {
    public static void main(String args[]) {
        BubbleSort s = new BubbleSort();
        s.sort(a);
        s.print(a);
    }

    @Override
    protected void sort(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if(a[j] > a[j + 1]){
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}

