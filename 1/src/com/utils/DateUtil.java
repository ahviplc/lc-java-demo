package com.utils;


import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 提供跟日期相关的一些通用方法
 *
 * @author
 * @version V1.0
 * @Title DateUtil.java
 * @Package
 * @Description Copyright: Copyright (c) 2018
 * Company:lmt
 * @date 2018-3-19 14:58:24
 */
public final class DateUtil {

    /**
     * 时间格式
     */
    public final static String TIME_FORMAT = "HH:mm:ss:SS";

    /**
     * 缺省短日期格式
     */
    public final static String DEFAULT_SHORT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省短日期格式
     */
    public final static String DEFAULT_SHORT_DATE_FORMAT_ZH = "yyyy年M月d日";

    /**
     * 缺省长日期格式
     */
    public final static String DEFAULT_LONG_DATE_FORMAT = DEFAULT_SHORT_DATE_FORMAT
            + " " + TIME_FORMAT;

    /**
     * 日期时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时间格式 年月(yyyy-MM)
     */
    public final static String DATETIME_FORMAT_NIANYUE = "yyyy-MM";

    /**
     * 缺省的时间戳格式
     */
    public static final String DEFAULT_TIMESTAMP = "yyyyMMddHHmmss";

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd）。
     */
    public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd HH:mm:ss:SS）。
     */
    public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";

    /**
     * 默认的时间段显示格式
     */
    public final static String DEFAULT_PERIOD_FORMAT = "{0}天{1}小时{2}分钟";

    /**
     * Java能支持的最大日期字符串（yyyy-MM-dd）。
     */
    public final static String JAVA_MAX_SHORT_DATE_STR = "9999-12-31";

    /**
     * 把日期对象加减年、月、日后得到新的日期对象
     *
     * @param depart yy-年、MM-月、dd-日、HH-时、mm-分、ss-秒
     * @param number 加减因子
     * @param date   需要加减年、月、日的日期对象
     * @return Date 新的日期对象
     */
    public static Date addDate(String datepart, int number, Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (datepart.equals("yy")) {
            cal.add(Calendar.YEAR, number);
        } else if (datepart.equals("MM")) {
            cal.add(Calendar.MONTH, number);
        } else if (datepart.equals("dd")) {
            cal.add(Calendar.DATE, number);
        } else if (datepart.equals("HH")) {
            cal.add(Calendar.HOUR, number);
        } else if (datepart.equals("mm")) {
            cal.add(Calendar.MINUTE, number);
        } else if (datepart.equals("ss")) {
            cal.add(Calendar.SECOND, number);
        } else {
            throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值："
                    + datepart);
        }

        return cal.getTime();
    }

    /**
     * 比较时间大小 默认和当前时间比较
     *
     * @param time1
     * @return
     */
    public static boolean compareNow(String time1) {
        return compareTime(time1, currentStr(), DATETIME_FORMAT);
    }

    /**
     * 比较时间大小
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compareTime(String time1, String time2) {
        return compareTime(time1, time2, DATETIME_FORMAT);
    }

    /**
     * 比较时间大小
     *
     * @param time1
     * @param time2
     * @param dateFormat
     * @return 如果time1小于time2返回true, 否则返回false
     */
    public static boolean compareTime(String time1, String time2, String dateFormat) {

        SimpleDateFormat t1 = new SimpleDateFormat(dateFormat);
        SimpleDateFormat t2 = new SimpleDateFormat(dateFormat);
        try {
            Date d1 = t1.parse(time1);
            Date d2 = t2.parse(time2);
            return d1.before(d2);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 将指定格式的字符串转换成日期类型
     *
     * @param date   - 待转换的日期字符串
     * @param format - 日期格式字符串
     * @return - Date日期对象
     */
    public static Date convert(String date, String format) {

        if (date == null || date.equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            throw new RuntimeException("DateUtil.convert():"
                    + e.getMessage());
        }
    }

    /**
     * 将日期类型转换成指定格式的日期字符串
     *
     * @param date   - Date日期对象
     * @param format - 日期格式字符串
     * @return - 指定日期类型格式的时间字符串
     */
    public static String convert(Date date, String format) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将时间戳转换成指定格式的日期字符串
     *
     * @param date
     * @param format
     * @return
     * @auth <a href="mailto:zouds@sinotn.com">邹大嵩</a>
     * @date 2016年7月7日 上午10:17:51
     */
    public static String convert(Long date, String format) {
        if (date == null) {
            return "";
        }
        return convert(convert(date), format);
    }

    /**
     * 将时间字符串转成日期对象
     * 备注:时间格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date - 时间字符串
     * @return - 日期对象
     */
    public static Date convert(String date) {
        return convert(date, DATETIME_FORMAT);
    }

    /**
     * 将日期对象转成时间字符串-年月
     * 备注:时间格式(yyyy-MM)
     *
     * @param date - 日期对象
     * @return - 时间字符串
     */
    public static Date convertNianYue(String date) {
        return convert(date, DATETIME_FORMAT_NIANYUE);
    }


    public static Date convert(long time) {
        return new Date(time);
    }

    public static Date convert(Long time) {
        return new Date(time.longValue());
    }


    /**
     * 将日期对象转成时间字符串
     * 备注:时间格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date - 日期对象
     * @return - 时间字符串
     */
    public static String convert(Date date) {
        return convert(date, DATETIME_FORMAT);
    }


    /**
     * 将对象转成日期对象
     *
     * @param o
     * @return
     */
    public static Date toDate(Object o) {

        if (null == o) {
            return null;
        }
        if (o instanceof Date) {
            return (Date) o;
        }
        if (o instanceof String) {
            return convert((String) o);
        }
        if (o instanceof Long) {
            Long t = (Long) o;
            return new Date(t.longValue());
        }
        if (o instanceof Integer) {
            Long t = ((Integer) o).longValue();
            return new Date(t.longValue());
        }
        throw new RuntimeException("invalid time object:" + o);
    }

    /**
     * 把输入的时间转换成时间段显示
     *
     * @param period 单位为妙
     * @return 默认格式为"d天h小时m分钟"
     */
    public static String format(long period) {

        long dayUnit = 24 * 60 * 60L;
        long hourUnit = 60 * 60L;
        long minUnit = 60L;
        String result = MessageFormat.format(DEFAULT_PERIOD_FORMAT,
                (period / dayUnit), (period % dayUnit / hourUnit), (period
                        % hourUnit / minUnit));
        return result;
    }

    /**
     * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
     *
     * @param datepart  两位的格式字符串，yy表示年，MM表示月，dd表示日
     * @param startdate 开始日期
     * @param enddate   结束日期
     * @return double 如果enddate>startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
     */
    public static double dateDiff(String datepart, Date startdate, Date enddate) {

        if (datepart == null || datepart.equals("")) {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值："
                    + datepart);
        }

        double days = (double) (enddate.getTime() - startdate.getTime())
                / (60 * 60 * 24 * 1000);

        if (datepart.equals("yy")) {
            days = days / 365;
        } else if (datepart.equals("MM")) {
            days = days / 30;
        } else if (datepart.equals("dd")) {
            return days;
        } else {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值："
                    + datepart);
        }
        return days;
    }

    /**
     * 返回当前时间，默认格式为yyyy-MM-dd HH:mm:ss,
     *
     * @return
     */
    public static String currentStr() {
        return currentStr("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * <p>
     * 取得当前日期，并将其转换成格式为"dateFormat"的字符串 例子：假如当前日期是 2003-09-24 9:19:10，则：
     *
     * <pre>
     * getCurrDateStr("yyyyMMdd")="20030924"
     * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
     * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
     * </pre>
     *
     * </p>
     *
     * @param dateFormat - String 日期格式字符串
     * @return String
     */
    public static String currentStr(String dateFormat) {
        return convert(new Date(), dateFormat);
    }

    /**
     * 得到系统当前时间的Timestamp对象
     *
     * @return 系统当前时间的Timestamp对象
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取时间戳字符串的毫秒表示，字符串格式 “yyyy-MM-dd HH:mm:ss”
     *
     * @param strTime 时间戳字符串的毫秒表示，1363140153000 1354723201000
     */
    public static long getTimeMillis(String dateStr) {
        return convert(dateStr, DATETIME_FORMAT).getTime();
    }

    /**
     * 获取指定日期所在月份的第一天
     *
     * @param date 指定日期
     * @return 第一天
     */
    public static Date getFirstMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 获取指定日期下个月第一天的开始时间
     *
     * @param date 指定日期
     * @return 下个月第一天的开始时间
     */
    public static Date getFirstDayNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前月第一天的开始时间
     *
     * @param date 指定日期
     * @return 下个月第一天的开始时间
     */
    public static Date getFirstDayCurMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月份的最后一天
     *
     * @param date 指定日期
     * @return 最后一天
     */
    public static Date getLastMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public static Date dayStartTimeAt(long time) {

        Date d = new Date(time);
        return dayStartTimeAt(d);
    }

    public static Date dayStartTimeAt(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, date.getDate());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date dayEndTimeAt(long time) {

        Date d = new Date(time);
        return dayEndTimeAt(d);
    }

    public static Date dayEndTimeAt(Date date) {

        long t = date.getTime() + 24 * 3600 * 1000L;
        Date newDate = new Date(t);
        return dayStartTimeAt(newDate);
    }

    /**
     * 防止非法实例化
     */
    private DateUtil() {
    }

    public static void main(String args[]) {
        System.out.println(convert(new Date(), DEFAULT_TIMESTAMP));//yyyyMMddHHmmss-20181010112832
        System.out.println(convert("20181010112832", DEFAULT_TIMESTAMP));
    }
}
