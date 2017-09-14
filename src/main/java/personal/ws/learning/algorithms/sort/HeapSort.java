package personal.ws.learning.algorithms.sort;

import personal.ws.learning.algorithms.common.CommonSort;

import java.util.Arrays;

/**
 * @Description:堆排序 堆排序是一种树形选择排序，是对直接选择排序的有效改进。
 * 堆的定义如下：具有n个元素的序列（h1,h2,...,hn),当且仅当满足（hi>=h2i,hi>=2i+1）或
 * （hi<=h2i,hi<=2i+1）(i=1,2,...,n/2)时称之为堆。在这里只讨论满足前者条件的堆。由堆的
 * 定义可以看出，堆顶元素（即第一个元素）必为最大项（大顶堆）。完全二叉树可以很直观
 * 地表示堆的结构。堆顶为根，其它为左子树、右子树。初始时把要排序的数的序列看作是一
 * 棵顺序存储的二叉树，调整它们的存储序，使之成为一个堆，这时堆的根节点的数最大。然
 * 后将根节点与堆的最后一个节点交换。然后对前面(n-1)个数重新调整使之成为堆。依此类
 * 推，直到只有两个节点的堆，并对它们作交换，最后得到有 n个节点的有序序列。从算法
 * 描述来看，堆排序需要两个过程，一是建立堆，二是堆顶与堆的最后一个元素交换位置。所
 * 以堆排序有两个函数组成。一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数。
 * @author: 王上
 * @date: 2017/9/13
 * @project：WSService
 */
public class HeapSort extends CommonSort {

    public static void main(String args[]) {
        HeapSort s = new HeapSort();
        s.sort(a);
        s.print(a);
    }

    @Override
    public void sort(int[] a) {
        System.out.println("开始排序");
        int arrayLength = a.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆
            buildMaxHeap(a, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(a, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }

    private void swap(int[] data, int i, int j) {
        // TODO Auto-generated method stub
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    //对data 数组从0到lastIndex 建大顶堆
    private void buildMaxHeap(int[] data, int lastIndex) {
        // TODO Auto-generated method stub
        //从lastIndex 处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k 保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k 节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex 小于lastIndex，即biggerIndex+1 代表的k 节点的右子节点存在
                if(biggerIndex < lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex] < data[biggerIndex + 1]){
                        //biggerIndex 总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k] < data[biggerIndex]){
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex 赋予k，开始while 循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }
}
