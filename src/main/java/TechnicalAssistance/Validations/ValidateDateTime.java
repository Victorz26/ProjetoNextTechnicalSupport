package TechnicalAssistance.Validations;

import java.time.LocalDate;
import java.time.LocalTime;

public class ValidateDateTime {

    public static boolean validateDate(String date) {
        LocalDate localDate = LocalDate.now();
        LocalDate dateScheduled = LocalDate.parse(date);
        boolean before = dateScheduled.isBefore(localDate.plusDays(1));

        return before;
    }
    public static boolean validateTime(String time) {
        LocalTime timeScheduled = LocalTime.parse(time);
        int hour = timeScheduled.getHour();
        int minute = timeScheduled.getMinute();
        boolean invalidTime = ((hour >= 18 || hour < 8) || (minute > 0 && minute < 30) || (minute > 30));

        return invalidTime;
    }
}

