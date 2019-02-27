package io.damo.common.utils;


import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 日期处理
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String DATE_FULL_PATTERN = "yyyyMMddHHmmss";
    /**
     * 日期时间格式：yyyyMMddHHmmssSSS
     */
    public static final String FULL_READ_INDENT_PATTERN = "yyyyMMddHHmmssSSS";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * Description：计算两个日期 相差的天数
     *
     * @param date1 大日期
     * @param date2 小日期
     * @return
     */
    public static int getDateday(String date1, String date2) {
        long between_days = 0;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date1));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date2));
            long time2 = cal.getTimeInMillis();
            between_days = (time1 - time2) / (1000 * 3600 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 当前时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 返回 yyyy-MM-dd HH:mm:ss 格式的当前时间
     */
    public static String nowTime() {
        return now(DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间日期的字符串
     */
    public static String now(DateFormatType dateFormatType) {
        return format(now(), dateFormatType);
    }

    /**
     * 格式化日期 yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD);
    }

    /**
     * 格式化时间 HH:mm:ss
     */
    public static String formatTime(Date date) {
        return format(date, DateFormatType.HH_MM_SS);
    }

    /**
     * 格式化日期和时间 yyyy-MM-dd HH:mm:ss
     */
    public static String formatFull(Date date) {
        return format(date, DateFormatType.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化日期对象成字符串
     */
    public static String format(Date date, DateFormatType type) {
        if (U.isBlank(date) || U.isBlank(type)) {
            return U.EMPTY;
        }

        return new SimpleDateFormat(type.getValue()).format(date);
    }

    /**
     * 获取当前日期前一天日期
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }


    /**
     * 获取当前月份
     *
     * @param date
     * @return 当前月份
     */
    public static String getMonthDay(Date date) {
        SimpleDateFormat formatter = new java.text.SimpleDateFormat("MM");
        String month = formatter.format(date); //将日期时间格式化
        SimpleDateFormat formatterDay = new java.text.SimpleDateFormat("dd");
        String day = formatterDay.format(date); //将日期时间格式化
        return month+"月"+day+"日";
    }

    /**
     * 获取当前日期加一天
     *
     * @param date
     * @return
     */
    public static Date addDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取确定剩余时间
     *
     * @param date
     * @return
     */
    public static String timeLeft(Date date) {
        if (date == null) {
            return "48时00分";
        }

        Long createDate = date.getTime();
        Long nowDate = new Date().getTime();
        if (nowDate - createDate > 48 * 60 * 60 * 1000) {
            return "00时00分";
        }
        Long hour = 47 - (nowDate - createDate) / (1000 * 60 * 60);
        Long minute = 59 - (nowDate - createDate) % (1000 * 60 * 60) / (1000 * 60);
        return (hour > 9 ? hour : "0" + hour) + "时" + (minute > 9 ? minute : "0" + minute) + "分";
    }

    /**
     * 获取确定剩余时间
     *
     * @param date
     * @return
     */
    public static String time24Left(Date date) {
        if (date == null) {
            return "24时00分";
        }

        Long createDate = date.getTime();
        Long nowDate = new Date().getTime();
        if (nowDate - createDate > 24 * 60 * 60 * 1000) {
            return "00时00分";
        }
        Long hour = 23 - (nowDate - createDate) / (1000 * 60 * 60);
        Long minute = 59 - (nowDate - createDate) % (1000 * 60 * 60) / (1000 * 60);
        return (hour > 9 ? hour : "0" + hour) + "时" + (minute > 9 ? minute : "0" + minute) + "分";
    }

    /**
     * 获取确定剩余时间
     *
     * @param date
     * @return
     */
    public static String timeLeftOneHour(Date date, Integer type) {
        if (date == null) {
            return "00时00分";
        }
        Long createDate = date.getTime();
        Long nowDate = new Date().getTime();

        if (type == 0) {
            if (nowDate - createDate > 60 * 60 * 1000) {
                return "00时00分";
            }
            Long minute = 59 - (nowDate - createDate) % (1000 * 60 * 60) / (1000 * 60);
            return "00时" + (minute > 9 ? minute : "0" + minute) + "分";
        } else {
            if (nowDate - createDate > 24 * 60 * 60 * 1000) {
                return "00时00分";
            }
            Long hour = 23 - (nowDate - createDate) / (1000 * 60 * 60);
            Long minute = 59 - (nowDate - createDate) % (1000 * 60 * 60) / (1000 * 60);
            return (hour > 9 ? hour : "0" + hour) + "时" + (minute > 9 ? minute : "0" + minute) + "分";
        }
    }

    /**
     * 将字符串转换成 Date 对象. 类型基于 DateFormatType 一个一个试
     *
     * @see DateFormatType
     */
    public static Date parse(String source) {
        if (U.isBlank(source)) {
            return null;
        }

        source = source.trim();
        SimpleDateFormat dateFormat;
        for (DateFormatType type : DateFormatType.values()) {
            if (type.isCst()) {
                dateFormat = new SimpleDateFormat(type.getValue(), Locale.US);
            } else {
                dateFormat = new SimpleDateFormat(type.getValue());
            }
            try {
                Date date = dateFormat.parse(source);
                if (date != null) {
                    return date;
                }
            } catch (ParseException e) {
                // ignore
            }
        }
        return null;
    }

    /**
     * 时间戳转化为时间
     *
     * @param source
     * @return
     */
    public static Date parse2(String source) {
        if (U.isBlank(source)) {
            return null;
        }

        return new Date(Long.valueOf(source));
    }

    /**
     * 获取每月第一天
     *
     * @return
     */
    public static Date getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取每月最后一天
     *
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }

    /**
     * 获取一个日期所在天的最开始的时间(00:00:00 000), 对日期查询尤其有用
     */
    public static Date getDayStart(Date date) {
        return new DateTime(date).hourOfDay().withMinimumValue()
                .minuteOfHour().withMinimumValue()
                .secondOfMinute().withMinimumValue()
                .millisOfSecond().withMinimumValue().toDate();
    }

    /**
     * 获取一个日期所在天的最晚的时间(23:59:59 999), 对日期查询尤其有用
     */
    public static Date getDayEnd(Date date) {
        return new DateTime(date).hourOfDay().withMaximumValue()
                .minuteOfHour().withMaximumValue()
                .secondOfMinute().withMaximumValue()
                .millisOfSecond().withMaximumValue().toDate();
    }

    /**
     * 获取指定年月开始时间
     */
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取指定年月结束时间
     */
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 取得指定日期 N 天后的日期
     *
     * @param day 正数表示多少天后, 负数表示多少天前
     */
    public static Date addDays(Date date, int day) {
        return new DateTime(date).plusDays(day).toDate();
    }

    /**
     * 假设date 是周五, day是1, ignoreWeekend是true, name返回周一, 否则返回周六
     *
     * @param ignoreWeekend 是否忽略周末
     * @param date
     * @param day           增加的天数
     * @return
     */
    public static Date addDays(boolean ignoreWeekend, Date date, int day) {
        if (ignoreWeekend) {
            int actualAddedDay = 0;
            Date newDate = date;
            if (newDate.getDay() == 6) {
                newDate = addDays(newDate, 2);
            } else if (newDate.getDay() == 0) {
                newDate = addDays(newDate, 1);
            }
            while (actualAddedDay < day) {
                newDate = addDays(newDate, 1);
                if (!isWeekend(newDate)) {
                    ++actualAddedDay;
                }
            }

            return newDate;
        }
        return new DateTime(date).plusDays(day).toDate();
    }

    /**
     * 取得指定日期 N 个月后的日期
     *
     * @param month 正数表示多少月后, 负数表示多少月前
     */
    public static Date addMonths(Date date, int month) {
        return new DateTime(date).plusMonths(month).toDate();
    }

    /**
     * 取得指定日期 N 天后的日期
     *
     * @param year 正数表示多少年后, 负数表示多少年前
     */
    public static Date addYears(Date date, int year) {
        return new DateTime(date).plusYears(year).toDate();
    }

    /**
     * 取得指定日期 N 分钟后的日期
     *
     * @param minute 正数表示多少分钟后, 负数表示多少分钟前
     */
    public static Date addMinute(Date date, int minute) {
        return new DateTime(date).plusMinutes(minute).toDate();
    }

    /**
     * 取得指定日期 N 小时后的日期
     *
     * @param hour 正数表示多少小时后, 负数表示多少小时前
     */
    public static Date addHours(Date date, int hour) {
        return new DateTime(date).plusHours(hour).toDate();
    }

    /**
     * 取得指定日期 N 秒后的日期
     *
     * @param second 正数表示多少秒后, 负数表示多少秒前
     */
    public static Date addSeconds(Date date, int second) {
        return new DateTime(date).plusSeconds(second).toDate();
    }

    /**
     * 取得指定日期 N 周后的日期
     *
     * @param week 正数表示多少周后, 负数表示多少周前
     */
    public static Date addWeeks(Date date, int week) {
        return new DateTime(date).plusWeeks(week).toDate();
    }

    /**
     * 传入的时间晚于当前时间就返回传入的时间, 否则就返回当前时间
     */
    public static Date before(Date date) {
        Date now = now();
        return now.after(date) ? now : date;
    }

    /**
     * 传入的时间是不是当月当日. 用来验证生日
     */
    public static boolean wasBirthday(Date date) {
        DateTime dt = DateTime.now();
        DateTime dateTime = new DateTime(date);
        return dt.getMonthOfYear() == dateTime.getMonthOfYear() && dt.getDayOfMonth() == dateTime.getDayOfMonth();
    }

    /**
     * 传入的两个时间是不是同一天
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);
        return isSameDate;
    }

    /**
     * 传入的时间是不是周六周日
     */
    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return week == 6 || week == 0;
    }

    public static DateBean getDataBean(Date dateStart, Date dateStop) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        DateBean dateBean = new DateBean();
        try {
            d1 = dateStart;
            d2 = dateStop;
            //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            dateBean.setDiffDays(diffDays);
            dateBean.setDiffHours(diffHours);
            dateBean.setDiffMinutes(diffMinutes);
            dateBean.setDiffSeconds(diffSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateBean;
    }

    public static void main(String[] srgs) {


    }

    /***
     * 剩余收货时间
     * @param payTime
     * @return
     */
    public static String getOrderTime(Date payTime){
        Calendar c=Calendar.getInstance();
        c.setTime(payTime);
        c.add(Calendar.DAY_OF_MONTH,7);
        Calendar now=Calendar.getInstance();
        long aTime=now.getTimeInMillis();
        long bTime=c.getTimeInMillis();
        //时间相减
        long cTime=bTime-aTime;
        long sTime=cTime/1000;//时间差，单位：秒
        long mTime=sTime/60;
        long hTime=mTime/60;
        long dTime=hTime/24;
        /*SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间："+f.format(now.getTime()));
        System.out.println("设定时间："+f.format(c.getTime()));
        System.out.println("时间差："+dTime+"天"+hTime%24+"时"+mTime%60+"分"+sTime%60+"秒");*/
        if(cTime>0){
            return dTime+"天"+hTime%24+"小时";
        }
        return "";
    }

    /**
     * 剩余应诉时间
     * @param appealDate
     * @return
     */
    public static String getAppealTime(Date appealDate) {
        Calendar c=Calendar.getInstance();
        c.setTime(appealDate);
        c.add(Calendar.DAY_OF_MONTH,3);
        Calendar now=Calendar.getInstance();
        long aTime=now.getTimeInMillis();
        long bTime=c.getTimeInMillis();
        //时间相减
        long cTime=bTime-aTime;
        long sTime=cTime/1000;//时间差，单位：秒
        long mTime=sTime/60;
        long hTime=mTime/60;
        long dTime=hTime/24;
        /*SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间："+f.format(now.getTime()));
        System.out.println("设定时间："+f.format(c.getTime()));
        System.out.println("时间差："+dTime+"天"+hTime%24+"时"+mTime%60+"分"+sTime%60+"秒");*/
        if(cTime>0){
            return dTime+"天"+hTime%24+"小时";
        }
        return "";
    }

    public static class DateBean {
        /*天*/
        private long diffDays;
        /*小时*/
        private long diffHours;
        /*分钟*/
        private long diffMinutes;
        /*秒*/
        private long diffSeconds;

        public long getDiffDays() {
            return diffDays;
        }

        public void setDiffDays(long diffDays) {
            this.diffDays = diffDays;
        }

        public long getDiffHours() {
            return diffHours;
        }

        public void setDiffHours(long diffHours) {
            this.diffHours = diffHours;
        }

        public long getDiffMinutes() {
            return diffMinutes;
        }

        public void setDiffMinutes(long diffMinutes) {
            this.diffMinutes = diffMinutes;
        }

        public long getDiffSeconds() {
            return diffSeconds;
        }

        public void setDiffSeconds(long diffSeconds) {
            this.diffSeconds = diffSeconds;
        }
    }
}
