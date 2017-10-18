package personal.ws.learning.random;

import org.junit.Test;

import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/8/15
 * @project：WSService
 */
public class RandomCase {

    /**
     *从Random对象中获取随机值
     */
    @Test
    public void randomNextRan(){
        Random random = new Random();
        boolean b = random.nextBoolean();
        System.out.println(b);
        double v = random.nextDouble();
        System.out.println(v);
        float v1 = random.nextFloat();
        System.out.println(v1);
        double v2 = random.nextGaussian();
        System.out.println(v2);
        int i = random.nextInt();
        System.out.println(i);
        int i1 = random.nextInt(10);
        System.out.println(i1);
        long l = random.nextLong();
        System.out.println(l);
    }

    /**
     * Random对jdk8StreamApi支持
     */
    @Test
    public void randomMethod(){
        //非固定seed将会根据System.nanoTime作为seed产生随机数
        Random random = new Random();
        IntStream ints = random.ints(20);
//        long count = ints.count();
//        System.out.println(count);
        ints.filter(num->num>1178124816).forEach(num->{num=Math.abs(num);System.out.println(num);});

        //固定seed将会得到相同的随机数值;
        Random solid = new Random(1000);
        solid.ints(3).forEach(System.out::println);

    }
    
}
