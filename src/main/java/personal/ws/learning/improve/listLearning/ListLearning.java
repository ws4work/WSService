package personal.ws.learning.improve.listLearning;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class ListLearning {
    final public static Random random = new Random();

    public static void main(String args[]) {
        //并集
        addAll();
        //交集
        retainAll();
        //差集
        removeAll();
        //乱序
        shufffle();
        //倒序
        reverse();
    }

    public static void reverse() {
        List<Integer> list = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list:"+list.toString());
        Collections.reverse(list);
        System.out.println("倒序排序:"+list.toString());
    }

    public static void shufffle() {
        List<Integer> list = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list:"+list.toString());
        Collections.shuffle(list);
        System.out.println("随机排序:"+list.toString());
    }

    public static void retainAll() {
        List<Integer> list1 = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list1:"+list1.toString());
        List<Integer> list2 = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list2:"+list2.toString());
        list1.retainAll(list2);
        System.out.println("交集:"+list1.toString());
    }

    public static void addAll() {
        List<Integer> list1 = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list1:"+list1.toString());
        List<Integer> list2 = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list2:"+list2.toString());
        list1.addAll(list2);
        System.out.println("并集:"+list1.toString());
    }

    public static void removeAll() {
        List<Integer> list1 = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list1:"+list1.toString());
        List<Integer> list2 = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        System.out.println("list2:"+list2.toString());
        list1.removeAll(list2);
        System.out.println("差集:"+list1.toString());
    }

}
