package personal.ws.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DateUtils {

	public final static String PATTERN_YYYYMM = "yyyyMM";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//		System.out.println(getNowHour(3));
		//		System.out.println(getBetweenMonths("2015-01", "2015-09"));
		//		System.out.println(getBetweenDatesByMonth("2015-03"));;
		//		getTwoHoursAgoTime();
		//		Date date = DateUtils.StringToLongDate("2015-06-11 00:00:00");
		//		String timestamp = String.valueOf(date.getTime()/1000);
		//		System.out.println(timestamp);
		//		getTomorrowByDate("2015-07-31");
		System.out.println(DateToStr1(new Date()));
	}

	// 判断两个日期是否交叉
	public static boolean checkTime(String beg, String end, String begOld,
			String endOld) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long bgNew = sdf.parse(beg).getTime();
		Long endNew = sdf.parse(end).getTime();
		Long bold = sdf.parse(begOld).getTime();
		Long eold = sdf.parse(endOld).getTime();
		if (bgNew > eold || bold > endNew) {
			return true;
		} else {
			return false;
		}
	}

	//获取当前时间是周几
	public static int getWeek() {
		int week = 0;
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			week = 7;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			week = 1;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			week = 2;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			week = 3;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			week = 4;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			week = 5;
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			week = 6;
		}
		return week;
	}

	/**
	 * 日期转换成字符串
	 * 格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 日期转换成字符串
	 * 格式为 yyyy-M-d HH:mm:ss
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToShortStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 日期转换成字符串
	 * 格式为yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr1(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 日期转换成字符串
	 * 格式为YYYYMMDDHHMMSS
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStrMobile_HB(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMDDHHMMSS");
		String str = format.format(date);
		return str;
	}

	/**
	 * 日期转换成字符串
	 * 格式为yyyyMMdd
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr2(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StringToDate(String strDt) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(strDt);
	}

	public static Date StringToLongDate(String strDt) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDt);
	}

	// 获取两个日期之间的日期
	public static List<String> getBetweenDates(String beginTime, String endTime) {
		List<String> dates = null;
		try {
			dates = new ArrayList<String>();
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = df.parse(beginTime);
			// System.out.println(df.format(startDate.getTime()));
			startCalendar.setTime(startDate);
			dates.add(df.format(startDate.getTime()));
			Date endDate = df.parse(endTime);
			// System.out.println(df.format(endDate.getTime()));
			endCalendar.setTime(endDate);
			// 一天
			if (!df.format(startDate.getTime()).equals(
					df.format(endDate.getTime()))) {
				while (true) {
					startCalendar.add(Calendar.DAY_OF_MONTH, 1);
					if (startCalendar.getTimeInMillis() < endCalendar
							.getTimeInMillis()) {
						// System.out.println(df.format(startCalendar.getTime()));
						dates.add(df.format(startCalendar.getTime()));
					} else {
						break;
					}
				}
				dates.add(df.format(endDate.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

	// 获取两个日期之间的月份
	public static List<String> getBetweenMonths(String beginMonth, String endMonth) {
		List<String> dates = null;
		try {
			dates = new ArrayList<String>();
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			Date startDate = df.parse(beginMonth);
			// System.out.println(df.format(startDate.getTime()));
			startCalendar.setTime(startDate);
			dates.add(df.format(startDate.getTime()));
			Date endDate = df.parse(endMonth);
			// System.out.println(df.format(endDate.getTime()));
			endCalendar.setTime(endDate);
			// 一天
			if (!df.format(startDate.getTime()).equals(
					df.format(endDate.getTime()))) {
				while (true) {
					startCalendar.add(Calendar.MONTH, 1);
					if (startCalendar.getTimeInMillis() < endCalendar
							.getTimeInMillis()) {
						// System.out.println(df.format(startCalendar.getTime()));
						dates.add(df.format(startCalendar.getTime()));
					} else {
						break;
					}
				}
				dates.add(df.format(endDate.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

	public static List<String> getBetweenDatesByMonth(String month) {
		List<String> dates = null;
		try {
			dates = new ArrayList<String>();
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(month);
			startCalendar.setTime(date);
			endCalendar.setTime(date);
			int beginday = startCalendar.getActualMinimum(Calendar.DAY_OF_MONTH);
			int lastday = startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			startCalendar.set(Calendar.DAY_OF_MONTH, beginday);
			endCalendar.set(Calendar.DAY_OF_MONTH, lastday);
			Date startDate = startCalendar.getTime();
			Date endDate = endCalendar.getTime();
			// 一天
			dates.add(df1.format(startDate.getTime()));
			if (!df1.format(startDate.getTime()).equals(
					df1.format(endDate.getTime()))) {
				while (true) {
					startCalendar.add(Calendar.DAY_OF_MONTH, 1);
					if (startCalendar.getTimeInMillis() < endCalendar
							.getTimeInMillis()) {
						// System.out.println(df.format(startCalendar.getTime()));
						dates.add(df1.format(startCalendar.getTime()));
					} else {
						break;
					}
				}
				dates.add(df1.format(endDate.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

	// 获取本周的开始和结束日期
	public static String[] getWeekDay() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int weekDay = c.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : c
				.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DATE, Calendar.MONDAY - weekDay);
		Date start = c.getTime();
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();
		// System.out.println(simpleDateFormat.format(start) + "\t" +
		// simpleDateFormat.format(end));
		String[] weekdayArr = new String[] { simpleDateFormat.format(start),
				simpleDateFormat.format(end) };
		return weekdayArr;
	}

	public static String[] getWeekDays() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String[] week = new String[2];
		try {
			Calendar cal = Calendar.getInstance(Locale.CHINA);
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.setTime(new Date());
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()); // Monday
			// System.out.println(sdf.format(cal.getTime()));
			week[0] = sdf.format(cal.getTime());
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6); // Sunday
			System.out.println(sdf.format(cal.getTime()));
			week[1] = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return week;
	}

	// 获取本周的所有日期
	public static List<String> getAllDayByWeek() {
		List<String> resu = null;
		try {
			String[] beginAndEnd = DateUtils.getWeekDay();
			resu = DateUtils.getBetweenDates(beginAndEnd[0], beginAndEnd[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resu;
	}

	// 获取本月第X周的所有日期
	public static List<String> getDays(int weekOfMonth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		List<String> list = new ArrayList<String>();
		try {
			Calendar calendar = Calendar.getInstance(Locale.CHINA);
			calendar.set(Calendar.WEEK_OF_MONTH, weekOfMonth);
			for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
				list.add(sdf.format(calendar.getTime()));
				//System.out.println(sdf.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 获取当前时间前6个月的日期
	public static List<String> getBeforAllMonth() {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 6; i++) {
			calendar.add(Calendar.MONTH, -1);
			System.out.println(matter.format(calendar.getTime()));
			list.add(matter.format(calendar.getTime()));
		}
		return list;
	}

	// 获取某月第一天日期
	public static String getFirtDayOfThisMonth(String date)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		Date date2 = f.parse(date);
		String strTemp = "";
		Calendar c = Calendar.getInstance();
		c.setTime(date2);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int days = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, days);
		strTemp = format.format(c.getTime());
		System.out.println(strTemp);
		return strTemp;
	}

	// //获取某月最后一天日期
	public static String getEndDayOfThisMonth(String date)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		Date date2 = f.parse(date);
		String strTemp = "";
		Calendar c = Calendar.getInstance();
		c.setTime(date2);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, days);
		strTemp = format.format(c.getTime());
		System.out.println(strTemp);
		return strTemp;
	}

	//获取昨天日期
	public static String getYesterday()
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		//System.out.println(yesterday);
		return yesterday;
	}

	//获取昨天日期
	public static String getTomorrowByDate(String date) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sf.parse(date));
		cal.add(Calendar.DAY_OF_YEAR, +1);
		String nextDate_1 = sf.format(cal.getTime());
		System.out.println(nextDate_1);
		return nextDate_1;
	}

	//获取今天日期
	public static String getToday() throws ParseException {
		Calendar cal = Calendar.getInstance();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		//System.out.println(today);
		return today;
	}

	/**
	 * 获取今天日期 含时分秒(24小时制)
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getToday2() throws ParseException {
		Calendar cal = Calendar.getInstance();
		String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		return today;
	}

	//获取上月开始日期和结束日期
	public static String[] getBeforMonthDate() {
		String[] resu = new String[2];
		Date nowdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		/* 设置为当前时间 */
		cal.setTime(nowdate);
		//System.out.println("当前时间是：" + sdf.format(nowdate));
		/* 当前日期月份-1 */
		cal.add(Calendar.MONTH, -1);
		// 得到前一个月的第一天
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		//System.out.println("上个月的第一天是：" + sdf.format(cal.getTime()));
		resu[0] = sdf.format(cal.getTime());
		// 得到前一个月的最后一天
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		resu[1] = sdf.format(cal.getTime());
		//System.out.println("上个月的最后一天是：" + sdf.format(cal.getTime()));
		return resu;
	}

	/**
	 * 获取当前具体年，月，日，时，分，秒
	 * 
	 * @param type
	 *            0年，1月，2日，3时，4分，5秒
	 * @return
	 */
	public static int getNowHour(int type) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int resu = 0;
		switch (type) {
		case 0:
			resu = year;
			break;
		case 1:
			resu = month + 1;
			break;
		case 2:
			resu = date;
			break;
		case 3:
			resu = hour;
			break;
		case 4:
			resu = minute;
			break;
		case 5:
			resu = second;
			break;
		default:
			resu = 0;
			break;
		}
		return resu;
	}

	//获取n天前的日期
	public static String getBeforeNdays(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -n);
		String ndaysbefore = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return ndaysbefore;
	}

	//获取前一小时时间
	public static long getOneHoursAgoTime() {
        //String oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(oneHourAgoTimeMillis);// 获取到完整的时间
		return System.currentTimeMillis() - 60 * 60 * 1000;
	}

	//获取前2小时时间
	public static long getTwoHoursAgoTime() {
        //String oneHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(oneHourAgoTimeMillis);// 获取到完整的时间
		//System.out.println(oneHoursAgoTime);
		return System.currentTimeMillis() - 60 * 120 * 1000;
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 获取某几个月第一天和最后一天的日期
	 * 
	 * @param months
	 * @return
	 */
	public static Map<String,String> getfirstAndLastByMonths(List<String> months) {
		List<String> days = new ArrayList<String>();
		for (String month : months) {
			List<String> monthDays = getBetweenDatesByMonth(month);
			days.addAll(monthDays);
		}
		Collections.sort(days);
		String firstDay = days.get(0);
		String lastDay = days.get(days.size() - 1);
		Map<String,String> map = new HashMap<String,String>();
		map.put("first", firstDay);
		map.put("last", lastDay);
		return map;
	}

	/**
	 * 获取当前年
	 */
	public static String getCurYear() {
		int thisYear = getNowHour(0);
		return String.valueOf(thisYear);
	}

	/**
	 * 获取当前月
	 */
	public static String getCurMonth() {
		int thisYear = getNowHour(1);
		return String.valueOf(thisYear);
	}

	/**
	 * 根据日期获取年
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getCurYearByDate(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		//System.out.println(year);
		return year;
	}

	/**
	 * 根据日期获取年
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getCurMonthByDate(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		//System.out.println(month);
		return month;
	}
}
