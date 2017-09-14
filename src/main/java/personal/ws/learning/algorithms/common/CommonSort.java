package personal.ws.learning.algorithms.common;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/14
 * @project：WSService
 */
public abstract class CommonSort {
    protected final static int streamSize=10;
    protected final static int randomNumberOrigin=0;
    protected final static int randomNumberBound=100;
    protected static int a[] = new Random().ints(streamSize,randomNumberOrigin,randomNumberBound).toArray();
    protected abstract void sort(int a[]);
    protected void print(int a[]){
        Arrays.stream(a).forEach(System.out::println);
    }
}
