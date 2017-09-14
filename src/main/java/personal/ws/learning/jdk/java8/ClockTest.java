package personal.ws.learning.jdk.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ClockTest {
	public static void main(String[] args) {
		Clock clock = Clock.systemDefaultZone();
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);
		//本地日期
		LocalDate ld = LocalDate.now();
		//本地时间
        LocalTime lt = LocalTime.now();
        //本地日期时间
		LocalDateTime ldt = LocalDateTime.now();
		//本地日期加法
        ldt=ldt.plusDays(5);
        //本地时间格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(ldt);
        System.out.println(ld);
		System.out.println(lt);
		System.out.println(ldt);
        System.out.println(format);
	}
}
