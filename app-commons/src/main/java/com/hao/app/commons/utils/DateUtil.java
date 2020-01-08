package com.hao.app.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 */
public class DateUtil {

	public static final SimpleDateFormat FULLDATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat CNDATE = new SimpleDateFormat("yyyy年MM月dd日");

	/**
	 * 将日期时间变为unix时间戳
	 * 
	 * @param dateTime
	 *            日期类型
	 * @return
	 */
	public static long getUnixTimes(Date dateTime) {
		return dateTime.getTime() / 1000;
	}

	/**
	 * 日期加减N天
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();
    }

    public static Date addMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    public static String getPassAgeDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -68); //68岁前的人
        int year = calendar.get(Calendar.YEAR);
        return year + "-01-01";
    }


    public static Date getSoonPassAgeDate() {
		int chaolingMonth = -1 * (67 * 12 + 11);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.add(Calendar.MONTH, chaolingMonth);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		String monthStr = month < 10 ? "0" + month : String.valueOf(month);

		SimpleDateFormat xx = new SimpleDateFormat("yyyy-MM-dd");
		String full = year + monthStr + "-01";

		try {
			return xx.parse(full);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSoonPassAgeDateStr() {
		Date dt = getSoonPassAgeDate();
		return new SimpleDateFormat("yyyy-MM-dd").format(dt);
	}

}
