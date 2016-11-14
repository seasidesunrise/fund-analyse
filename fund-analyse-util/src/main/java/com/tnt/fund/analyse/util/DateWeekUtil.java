package com.tnt.fund.analyse.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateWeekUtil {

    /**
     * 根据日期，返回星期几
     *
     * @param dateStr 类似2015-01-20 格式的日期
     * @return
     */
    public static int getDayOfWeek(String dateStr) {
        int defaultExp = -1;
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); // 2015-01-20
        try {
            Date date = dateFm.parse(dateStr);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dow = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (dow < 0) {
                dow = defaultExp;
            }

            return dow;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return defaultExp;
    }

    public static Date getDate(String dateStr) {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); // 2015-01-20
        try {
            return dateFm.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getWeekNum(String dateStr) {
        int defaultExp = -1;
        Calendar ca = Calendar.getInstance();
        try {
            ca.setTime(DateFormat.getDateInstance().parse(dateStr));
            int a = ca.WEEK_OF_YEAR;
            return a;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultExp;
    }
}
