package personal.ws.learning.thread;

public class ThreadSync1 implements Runnable{

    int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.println("count:" + count);
    }

    public static void main(String[] args) {
        ThreadSync1 t = new ThreadSync1();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}
