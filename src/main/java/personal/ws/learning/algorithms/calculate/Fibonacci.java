package personal.ws.learning.algorithms.calculate;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/9/14
 * @project：WSService
 */
public class Fibonacci {

    public static void main(String args[]) {
        System.out.println(fibonacci(10));
    }

    private static int fibonacci(int n) {
        if(n <= 2)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
