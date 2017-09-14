package personal.ws.learning.structure;

import personal.ws.learning.structure.bean.TestBean;

import java.util.*;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/14
 * @project：WSService
 */
public class List_Set {

    public static void main(String args[]) {
        Random random = new Random();
        TestBean tb1 = new TestBean();
        tb1.setId(1L);
        tb1.setName("zhao");
        tb1.setSex(false);
        TestBean tb2 = new TestBean();
        tb2.setId(2L);
        tb2.setName("long");
        tb2.setSex(random.nextBoolean());
        TestBean tb3 = new TestBean();
        tb3.setId(1L);
        tb3.setName("zhao");
        tb3.setSex(false);
        TestBean tb4 = new TestBean();
        tb4.setId(3L);
        tb4.setName("wang");
        tb4.setSex(random.nextBoolean());

        List<TestBean> list = new ArrayList<>();
        Collections.addAll(list, tb1, tb2, tb3, tb4);//填充
        System.out.println(list.toString());
        Map<Long, TestBean> map = new HashMap<>();
        for (TestBean order : list) {
            map.put(order.getId(), order);
        }
        System.out.println(map.toString());
        list.clear();
        list.addAll(map.values());
        System.out.println(list.toString());

    }

    public static void test1() {
        Random random = new Random();
        TestBean tb1 = new TestBean();
        tb1.setId(1L);
        tb1.setName("zhao");
        tb1.setSex(false);
        TestBean tb2 = new TestBean();
        tb2.setId(2L);
        tb2.setName("long");
        tb2.setSex(random.nextBoolean());
        TestBean tb3 = new TestBean();
        tb3.setId(1L);
        tb3.setName("zhao");
        tb3.setSex(false);
        TestBean tb4 = new TestBean();
        tb4.setId(3L);
        tb4.setName("wang");
        tb4.setSex(random.nextBoolean());


        List<TestBean> list = new ArrayList<>();
        Collections.addAll(list, tb1, tb2, tb3, tb4);//填充

        System.out.println(list.toString());
        Set<TestBean> set = new HashSet<>();
        set.addAll(list);//给set填充
        System.out.println(set.toString());
        list.clear();//清空list，不然下次把set元素加入此list的时候是在原来的基础上追加元素的
        list.addAll(set);//把set的
        System.out.println(list.toString());
    }
}
