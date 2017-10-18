package personal.ws.learning.improve.gotoLearning;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/10/18
 * @project：WSService
 */
public class GotoLearning {

    public static void main(String args[]) {
        List<Integer> list = new ArrayList<>();
        int num = 0;
        int test = 0;
        inner:
        while (num < 50) {
            outer:
            for (int i = 0; i < num; i++) {
                test++;
                if(i == num - 1){
                    list.add(test);
                    break outer;
                }
            }
            num++;
        }

        System.out.println(num);
        System.out.println(test);
        list.stream().forEach(System.out::println);

    }
}
