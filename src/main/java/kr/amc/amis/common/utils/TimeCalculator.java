package kr.amc.amis.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TimeCalculator() {}


    public static LocalDate getDateDaysAgo(int daysAgo) {
        return LocalDate.now().minusDays(daysAgo);
    }

    public static String getFormattedDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return FORMATTER.format(dateTime);
    }
}
