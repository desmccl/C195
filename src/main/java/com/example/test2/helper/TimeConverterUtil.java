package com.example.test2.helper;

import java.time.*;
/**This utility handles time zone conversions for the database*/
public class TimeConverterUtil {
    /**This method converts times in the application to the users timezone*/
    public static LocalDateTime convertToUserTimeZone(LocalDateTime storedDateTime) {
        ZoneId userZone = ZoneId.systemDefault();
        ZonedDateTime utcDateTime = storedDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime userDateTime = utcDateTime.withZoneSameInstant(userZone);
        return userDateTime.toLocalDateTime();
    }
    /**This method converts times in the application back to utc for the database*/
    public static LocalDateTime convertToUtcTimeZone(LocalDateTime userDateTime) {
        ZoneId userZone = ZoneId.systemDefault();
        ZonedDateTime userZonedDateTime = userDateTime.atZone(userZone);
        ZonedDateTime utcDateTime = userZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        return utcDateTime.toLocalDateTime();
    }


}
