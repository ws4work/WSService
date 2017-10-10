package personal.ws.learning.current_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description:java.current包
 * @author: 王上
 * @date: 2017/9/26
 * @project：WSService
 */
public class CurrentLearning {
    /**
     * Executor                  ：具体Runnable任务的执行者。
     * ExecutorService           ：一个线程池管理者，其实现类有多种，我会介绍一部分。我们能把Runnable,Callable提交到池中让其调度。
     * Semaphore                 ：一个计数信号量
     * ReentrantLock             ：一个可重入的互斥锁定 Lock，功能类似synchronized，但要强大的多。
     * Future                    ：是与Runnable,Callable进行交互的接口，比如一个线程执行结束后取返回的结果等等，还提供了cancel终止线程。
     * BlockingQueue             ：阻塞队列。
     * CompletionService         : ExecutorService的扩展，可以获得线程执行结果的
     * CountDownLatch            ：一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
     * CyclicBarrier             ：一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点
     * Future                    ：Future 表示异步计算的结果。
     * ScheduledExecutorService ：一个 ExecutorService，可安排在给定的延迟后运行或定期执行的命令。
     */

    public static void main(String args[]) {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future<Integer> submit = executorService.submit(() -> {
                int[] ints = new Random().ints(10, 0, 100).toArray();
                Arrays.stream(ints).forEach(System.out::println);
                return ints[ints.length - 1];
            });
            Integer integer = null;
            try {
                integer = submit.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            list.add(integer);
            System.out.println(integer);
        }
        while (true) {
            if(list.size() == 20){
                System.out.println("---运行结束");
                executorService.shutdown();
                break;
            }
        }
    }

    public void current_map(){
        ConcurrentHashMap map = new ConcurrentHashMap();

    }
}
