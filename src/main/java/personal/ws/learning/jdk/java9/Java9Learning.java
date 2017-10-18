package personal.ws.learning.jdk.java9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/26
 * @project：WSService
 */
public class Java9Learning {

    public static void main(String args[]) {
        Java9Learning java = new Java9Learning();
        //java.CollectionFactoryLearning();
        java.StreamNewLearning();
        //java.TryLearning();
    }

    public void CollectionFactoryLearning() {
        //通过工厂构造Set
        Set<Integer> integers = Set.of(1, 2, 3);
        //通过工厂构造List
        List<String> strings = List.of("a", "b", "c");

        Map<String,Object> map = Map.of(
                "p1", new String[]{"0.01", "1", "10"},
                "p10", new String[]{"0.01", "10", "105"},
                "p50", new String[]{"0.01", "50", "540"},
                "p100", new String[]{"0.01", "100", "1100"},
                "p200", new String[]{"0.01", "200", "2240"});
    }

    public void StreamNewLearning() {
        IntStream.iterate(1, i -> i < 50, i -> i + 1).forEach(System.out::println);
        //Optional 和 Stream 之间的结合也得到了改进。
        //现在可以通过 Optional 的新方法 `stram`
        //将一个 Optional 对象转换为一个(可能是空的) Stream 对象
        Stream<Integer> s = Optional.of(1).stream();

        System.out.println("--------------------");

        List<Integer> ints = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            ints.add(i);
        }
        //dropWhile:遇到第一个不匹配的结束
        List<Integer> collect1 = ints.stream().dropWhile(n -> n < 5).collect(Collectors.toList());
        collect1.stream().forEach(System.out::println);

        System.out.println("----------");

        //takeWhile:
        List<Integer> collect2 = ints.stream().takeWhile(n -> n < 5).collect(Collectors.toList());
        collect2.stream().forEach(System.out::println);
    }

    public void TryLearning() {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try (fos; fis) {
            //fos.write(13);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
