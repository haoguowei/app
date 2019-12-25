package com.hao.app.commons.utils;

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

}
