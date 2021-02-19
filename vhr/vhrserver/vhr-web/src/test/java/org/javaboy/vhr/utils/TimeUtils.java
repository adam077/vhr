package org.javaboy.vhr.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeUtils {
    /*
    时区的东西需要搞懂
     */
    public static void main(String[] args) {
        List<Object> theList = new ArrayList<>();
        theList.add(getCurrentTimestamp());
        theList.add(getCurrentTime());
        theList.add(getCurrentDate(""));
        theList.add(getWeekday());
        for (Object theOne : theList) {
            System.out.println(theOne);
        }
    }

    public static long getCurrentTimestamp() {
        /*
        获取毫秒级时间戳
         */
//        return Calendar.getInstance().getTimeInMillis();
        return System.currentTimeMillis();
    }

    public static String getCurrentTime() {
        /*
        获取当前时间
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public static String getCurrentDate(String format) {
        LocalDate localDate = LocalDate.now();
        if ("".equals(format)) {
            return localDate.toString();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public static int getWeekday() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfWeek().getValue();
    }

}
