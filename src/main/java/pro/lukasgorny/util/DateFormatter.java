package pro.lukasgorny.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Łukasz on 16.01.2018.
 */
public class DateFormatter {

    private static final String DATE_TIME_HOUR_MINUTE_FORMAT = "dd.MM.yyyy HH:mm";

    public static String formatDateToHourMinuteFormat(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_HOUR_MINUTE_FORMAT);
        return dateTime.format(dateTimeFormatter);
    }
}
