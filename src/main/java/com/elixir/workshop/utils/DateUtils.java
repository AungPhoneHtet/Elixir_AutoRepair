package com.elixir.workshop.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    private static String UIDatePattern = "dd/MM/yyyy";
    private static String YYYYMMDD = "yyyyMMdd";

    public static Date changeUIDateToSQLDate(String UIDate) {
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat(UIDatePattern).parse(UIDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(date.getTime());
    }

    public static String changeSQLDateToUIDate(Date sqlDate) {
        return new SimpleDateFormat(UIDatePattern).format(sqlDate);

    }

    public static String getCurrentDateInYYYYMMDD() {
        return new SimpleDateFormat(YYYYMMDD).format(new java.util.Date());
    }

    public static Date getCurrentDate() {
        return new Date(new java.util.Date().getTime());
    }
}
