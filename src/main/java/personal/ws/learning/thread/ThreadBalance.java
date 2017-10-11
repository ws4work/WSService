package personal.ws.learning.thread;

import java.util.concurrent.TimeUnit;

public class ThreadBalance {

    private String name;
    private double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        ThreadBalance balance = new ThreadBalance();
        new Thread(() -> balance.set("WS", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("balance:" + balance.getBalance("WS"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("balance:" + balance.getBalance("WS"));

    }

}
