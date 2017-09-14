package personal.ws.myservice.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimmer {

    private static Timer t;

    /*public static void main(String args[]) {
        t = new Timer();
        System.out.println("开始" + new Date().toString());
        timerSet();
        t = null;
        System.out.println("结束" + new Date().toString());
        System.gc();
    }

    private static void timerSet() {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行了");
            }
        };
        Calendar c = Calendar.getInstance();
        c.set(2017, 6, 7, 13, 42, 00);
        Date time = c.getTime();
        System.out.println(time.toString());
        t.schedule(tt, time);
    }*/

    public static void main(String[] args) throws ParseException {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("Synchro data to other servers.");
                timer.cancel();
            }
        };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-07-07 18:21:30");
        timer.schedule(task, date);
    }
}
