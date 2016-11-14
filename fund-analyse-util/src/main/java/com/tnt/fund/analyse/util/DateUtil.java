package com.tnt.fund.analyse.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getDateStr(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); // 2015-01-20
        String dateStr = dateFm.format(date);

        return dateStr;
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }
}
