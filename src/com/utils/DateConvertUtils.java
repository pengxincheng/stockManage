package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pxc on 2018/5/4.
 */
public class DateConvertUtils {

    /**
     * 转换日期为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateToString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } else {
            return "";
        }
    }


    /**
     * 转换字符串为日期
     *
     * @param dateStr
     * @param format
     * @return
     * @throws java.text.ParseException
     */
    public static Date formatStringToDate(String dateStr, String format) {
        if (dateStr != null && !"".equals(dateStr)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            try {
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
