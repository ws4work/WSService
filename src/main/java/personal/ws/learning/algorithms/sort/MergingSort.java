package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

import java.util.Arrays;

/**
 * @Description:归并排序 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有
 * 序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并
 * 为整体有序序列。
 * @author: 王上
 * @date: 2017/9/13
 * @project：WSService
 */
public class MergingSort extends CommonSort {

    public static void main(String args[]) {
        MergingSort s = new MergingSort();
        s.sort(a);
        s.print(a);
    }

    @Override
    protected void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(int[] data, int left, int right) {

        // TODO Auto-generatedmethod stub

        if(left < right){
            //找出中间索引
            int center = (left + right) / 2;
            //对左边数组进行递归
            sort(data, left, center);
            //对右边数组进行递归
            sort(data, center + 1, right);
            //合并
            merge(data, left, center, right);
        }
    }

    public void merge(int[] data, int left, int center, int right) {
        // TODO Auto-generatedmethod stub
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        //third 记录中间数组的索引
        int third = left;
        int tmp = left;

        while (left <= center && mid <= right) {
            //从两个数组中取出最小的放入中间数组
            if(data[left] <= data[mid]){
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }

        //剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }

        //将中间数组中的内容复制回原数组
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
        System.out.println(Arrays.toString(data));
    }
}
