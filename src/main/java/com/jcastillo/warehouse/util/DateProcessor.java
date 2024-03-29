package com.jcastillo.warehouse.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final public class DateProcessor {
    public static final String DATE_FORMAT= "yyyy-MM-dd HH:mm";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private DateProcessor() { }

    public static LocalDateTime toDate(final String date) {
        return LocalDateTime.parse(date, formatter);
    }

    public static String toString(final LocalDateTime date){
        return date.format(formatter);
    }
}