package com.allst.micro.date;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Hutu
 * @since 2022-08-27 下午 08:21
 */
public class DateUtil {
    private static final long DAYTIME_MILLISECONDS = 86400000L;
    public static final String SDF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String SDF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String SDF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String SDF_HH_MM = "HH:mm";
    public static final String SDF_YYYY = "yyyy";

    public static final String SDF_YYYY_DOT_M_DOT_D = "yyyy.M.d";
    public static final String SDF_YYYY_DOT_MM = "yyyy.MM";

    public static final String SDF_YYYY_MM_DD_AT_HH_MM_SS = "yyyy-MM-dd@HH:mm:ss";

    /**
     * 字符串转换成日期
     *
     * @param date    字符串
     * @param pattern 转化的格式
     *
     * @return 结果
     *
     * @throws ParseException 异常
     */
    public static Date string2Date(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 字符串转换成日期
     *
     * @param date 以yyyy-MM-dd HH:mm:ss的pattern进行转换
     *
     * @return 结果
     *
     * @throws ParseException 异常
     */
    public static Date string2Date(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(SDF_YYYY_MM_DD_HH_MM_SS);
        return sdf.parse(date);
    }

    /**
     * 将日期转换为字符串
     *
     * @param date    日期
     * @param pattern 转化的格式，如yyyy-MM-dd HH:mm:ss
     *
     * @return 结果
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 将日期转换为字符串
     *
     * @param date 默认转化为yyyy-MM-dd HH:mm:ss
     */
    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SDF_YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }

    /**
     * 将传入的日期向前（或向后）滚动|amount|年
     *
     * @param date   日期
     * @param amount 年数
     */
    public static Date rollByYear(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    public static Date rollByYear(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, amount);
        return calendar.getTime();
    }

    /**
     * 将传入的日期向前（或向后）滚动|amount|月
     *
     * @param date   日期
     * @param amount 月数
     */
    public static Date rollByMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 将传入的日期向前（或向后）滚动|amount|月
     *
     * @param date   日期
     * @param amount 月数
     */
    public static long rollByMonths(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTimeInMillis();
    }

    /**
     * 将传入的日期向前（或向后）滚动|amount|天
     *
     * @param date   日期
     * @param amount 天数
     */
    public static long rollByDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, amount);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到几天前/后的时间
     *
     * @param date   日期
     * @param amount 天数
     */
    public static Date rollByDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, amount);
        return calendar.getTime();
    }

    public static Date rollByMinutes(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTime();
    }

    /**
     * 获取 amount 分钟的前后时间
     *
     * @param date   日期
     * @param amount 秒数
     */
    public static Date rollBySeconds(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, amount);
        return calendar.getTime();
    }

    public static boolean isBefore(Date date, Date date1) {
        return date1.before(date);
    }

    /**
     * 得到几小时前/后的时间
     */
    public static Date rollByHour(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, amount);
        return calendar.getTime();
    }

    /**
     * 得到当下date
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当天零点时间
     */
    public static Date getCurrentZeroTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 得到日期字符串
     */
    public static String getDateFromTime(Date date) {
        SimpleDateFormat sdf_date = new SimpleDateFormat(SDF_YYYY_MM_DD);
        return sdf_date.format(date);
    }

    /**
     * 格式化日期
     * 当天的时间 ： HH:mm发布
     * 3天内的时间 ： n天前发布
     * 大于三天 ： yyyy-MM-dd
     *
     * @param date   日期对象
     * @param format 三天后的日期格式， 为null 默认yyyy-MM-dd
     */
    public static String formatTime(Date date, String format) throws ParseException {
        if (date == null)
            return "";
        format = StringUtils.isBlank(format) ? "yyyy-MM-dd" : format;
        int result = differDate(date, new Date());
        result = result - 1;
        if (result < 1) {
            return getDate(date, "HH:mm发布");
        }
        if (result == 1)
            return getDate(date, "1天前发布");
        else if (result == 2)
            return getDate(date, "2天前发布");
        else if (result == 3)
            return getDate(date, "3天前发布");
        else
            return getDate(date, "yyyy-MM-dd");
    }

    //格式化时间 列表页显示
    public static String formatTime(String stime, String format) throws ParseException {
        if (stime == null)
            return "";
        Date ctime = string2Date(stime);
        return formatTime(ctime, format);
    }

    /**
     * 计算两个日期的相差天数
     * 注：只计算日期的差值，不精确到小时；结果可以有负值
     */
    public static int differDate(Date fromDate, Date endDate) throws ParseException {
        return differDate(date2String(fromDate, SDF_YYYY_MM_DD),
                date2String(endDate, SDF_YYYY_MM_DD));
    }

    /**
     * 计算两个日期的相差天数 （2014-10-01 到 2014-10-07  为 7天）
     * 注：只计算日期的差值，不精确到小时；结果可以有负值
     */
    public static int differDate(String fromDate, String endDate) throws ParseException {
        Date fDate = string2Date(fromDate, SDF_YYYY_MM_DD);
        Date eDate = string2Date(endDate, SDF_YYYY_MM_DD);
        int cha = calculateDiffDay(fDate, eDate);
        return cha >= 0 ? (cha + 1) : (cha - 1);
    }

    private static int calculateDiffDay(Date fDate, Date eDate) {
        long cha = eDate.getTime() - fDate.getTime();
        return (int) (cha / DAYTIME_MILLISECONDS);
    	/*Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(eDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		int day = day2 - day1;
		return day < 0 ? 0 - day : day + 1;*/
    }


    public static boolean compete(String startDate, String endDate, String formatStr) {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)
                || StringUtils.isBlank(formatStr)) {
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date sDate = format.parse(startDate);
            if (endDate.contains("至今")) {
                Calendar calendar = Calendar.getInstance();
                if (formatStr.equals("yyyy")) {
                    endDate = calendar.get(Calendar.YEAR) + "";
                } else if (formatStr.equals("yyyy.MM")) {
                    endDate = calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1);
                }
            }
            Date eDate = format.parse(endDate);
            if (eDate.getTime() >= sDate.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
        return false;

    }

    public static String getDate(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return date != null ? format.format(date) : "";
    }

    /**
     * 将日期格式的字符串转换为长整型
     */
    public static long convert2long(String date, String format) {
        try {
            if (format == null || "".equals(format))
                format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * BBS的发布时间
     */
    public static String formatBBSTime(Date fDate) throws ParseException {
        Date oDate = new Date();
        long m = oDate.getTime() - fDate.getTime();
        long sec = m / 1000;//秒
        long min = m / 1000 / 60;//分
        long hour = m / 1000 / 60 / 60;//小时
        long day = m / 1000 / 60 / 60 / 24;//天
        long month = m / 1000 / 60 / 60 / 24 / 30;//月
        long year = m / 1000 / 60 / 60 / 24 / 30 / 12;//年

        if (year != 0L)
            return year + "年前";
        if (month != 0L)
            return month + "月前";
        if (day != 0L)
            return day + "天前";
        if (hour != 0L)
            return hour + "小时前";
        if (min != 0L)
            return min + "分钟前";
        if (sec != 0L)
            return sec + "秒前";
        return "刚刚更新";
        //return getDate(fDate, "yyyy-MM-dd");
    }

    /**
     * 时间计算
     */
    public static Date calcDate(int year, int month, int day, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR) + year;
        month = cal.get(Calendar.MONTH) + month;
        day = cal.get(Calendar.DATE) + day;
        hour = cal.get(Calendar.HOUR_OF_DAY) + hour;
        min = cal.get(Calendar.MINUTE) + min;
        sec = cal.get(Calendar.SECOND) + sec;
        cal.set(year, month, day, hour, min, sec);
        return cal.getTime();
    }

    /**
     * 时间计算
     */
    public static String calcDate(int year, int month, int day, int hour, int min, int sec,
                                  String format) {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR) + year;
        month = cal.get(Calendar.MONTH) + month;
        day = cal.get(Calendar.DATE) + day;
        hour = cal.get(Calendar.HOUR_OF_DAY) + hour;
        min = cal.get(Calendar.MINUTE) + min;
        sec = cal.get(Calendar.SECOND) + sec;
        cal.set(year, month, day, hour, min, sec);
        Date date = cal.getTime();
        return date2String(date, format);
    }

    /**
     * 计算相差天数
     */
    public static int calcDate(String endDate, String startDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            long t = formatter.parse(endDate).getTime() - formatter.parse(startDate).getTime();
            return new Long((t / (1000 * 24 * 60 * 60))).intValue();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * @param dateStr 计算dateStr与days之和
     */
    public static String calcDate(String dateStr, int days, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            long time = formatter.parse(dateStr).getTime();
            long t = time + days * 1000 * 24 * 60 * 60;

            Date d = new Date(t);
            return formatter.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }
    }

    /**
     * 计算与当前时间相差天数
     */
    public static int calcDays(String date, String format) {
        if (date == null) {
            return 5;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        //开始结束相差天数
        try {
            return new Long((System.currentTimeMillis() - formatter.parse(date).getTime())
                    / (1000 * 24 * 60 * 60)).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 5;
        }
    }

    /**
     * 格式化页面时间显示
     */
    public static String formatShowTime(String deliverTime, String fmtStr) {
        SimpleDateFormat sFmt = new SimpleDateFormat(SDF_YYYY_MM_DD_HH_MM_SS);
        Date date = null;
        try {
            date = sFmt.parse(deliverTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatShowTime(date, fmtStr);

    }

    /**
     * 格式化页面时间显示
     */
    public static String formatShowTime(Date deliverTime, String fmtStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
            return fmt.format(deliverTime);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 时间计算
     */
    public static String calcDate(Date date, int year, int month, int day, int hour, int min, int sec, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR) + year;
        month = cal.get(Calendar.MONTH) + month;
        day = cal.get(Calendar.DATE) + day;
        hour = cal.get(Calendar.HOUR_OF_DAY) + hour;
        min = cal.get(Calendar.MINUTE) + min;
        sec = cal.get(Calendar.SECOND) + sec;
        cal.set(year, month, day, hour, min, sec);
        Date resultDate = cal.getTime();
        return date2String(resultDate, format);
    }

    /**
     * 得到今夕是何年
     *
     * @return 形如 yyyy
     */
    public static String getYear() {
        return date2String(new Date(), SDF_YYYY);
    }

    /**
     * 得到今夕是何年
     *
     * @return 形如 yyyy
     */
    public static int getYear(Date date) {
        return Integer.parseInt(date2String(date, SDF_YYYY));
    }

    public static long getSecond(Date begin, Date end) {
        return (end.getTime() - begin.getTime()) / 1000;
    }

    public static long getMillisecond(Date begin, Date end) {
        return (end.getTime() - begin.getTime());
    }

    /**
     * 计算当前年份,并和传入值比较
     */
    public static int calCurrentYear(String year, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        Date now = new Date();
        String currentYear = fmt.format(now);
        return year.compareTo(currentYear);
    }
}
