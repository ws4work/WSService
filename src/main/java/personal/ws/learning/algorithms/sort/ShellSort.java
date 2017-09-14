package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

/**
 * @Description:希尔排序（最小增量排序） 算法先将要排序的一组数按某个增量 d（n/2,n为要排序数的个数）分成若
 * 干组，每组中记录的下标相差 d.对每组中全部元素进行直接插入排序，然后再用一个较小
 * 的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到 1 时，进行直接
 * 插入排序后，排序完成。
 * @author: 王上
 * @date: 2017/9/13
 * @project：WSService
 */
public class ShellSort extends CommonSort {

    public static void main(String args[]) {
        ShellSort s = new ShellSort();
        s.sort(a);
        s.print(a);
    }

    @Override
    protected void sort(int[] a) {
        double d1 = a.length;
        int temp = 0;
        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i += d) {
                    int j = i - d;
                    temp = a[i];
                    for (; j >= 0 && temp < a[j]; j -= d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
            if(d == 1){
                break;
            }
        }
    }
}
