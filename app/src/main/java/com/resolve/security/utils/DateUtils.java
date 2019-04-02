package com.resolve.security.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by srikrishna on 20/10/18.
 */

public class DateUtils {

    private static SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private static SimpleDateFormat sdf02 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    // 2018-07-06T14:30:00+05:30
    public static String getFormattedDate(){
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        return dateFormat.format(new Date()) + getCurrentTimezoneOffset();

    }

    public static String getFormattedDateForOffline(String str){
        String dateString = str.split("T")[0];
        try {
            Date d = sdf01.parse(dateString);
            String fDateStr = sdf02.format(d);
            return fDateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    private static String getCurrentTimezoneOffset() {

        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = (offsetInMillis >= 0 ? "+" : "-") + offset;

        return offset;
    }
}
