package com.example.restaurant.util;

import java.time.format.DateTimeFormatter;

public class AppUtil {

    public static DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public static DateTimeFormatter getTimeFormatter(){
        return DateTimeFormatter.ofPattern("HH:mm");
    }
}
