package pro.lukasgorny.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Łukasz on 16.01.2018.
 */
public class DateFormatter {

    private static final String DATE_TIME_HOUR_MINUTE_FORMAT = "dd.MM.yyyy HH:mm";
    private static final String COUNTDOWN_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static String formatDateToHourMinuteFormat(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_HOUR_MINUTE_FORMAT);
        return dateTime.format(dateTimeFormatter);
    }

    public static String formatDateToCountdownFormat(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(COUNTDOWN_FORMAT);
        return dateTime.format(dateTimeFormatter);
    }
}
