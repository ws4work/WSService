package personal.ws.learning.improve.listLearning;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class ArrayListLearning {

    public static void main(String args[]){
        Random random = new Random();
        List<Integer> list = random.ints(10, 0, 100).boxed().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
        System.out.println();
        list.subList(5,7).clear();
        list.stream().forEach(System.out::println);
    }
}
