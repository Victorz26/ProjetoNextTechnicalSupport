package TechnicalAssistance.Validations;

import TechnicalAssistance.Entities.ScheduleAppointments;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class WarrantyValidation {

    public static boolean validateWarranty(ScheduleAppointments scheduleAppointments) {

        LocalDate today = LocalDate.now();

        LocalDate purchaseDay =  LocalDate.parse(scheduleAppointments
                .getPurchaseDate());

        long diff = purchaseDay.until(today, ChronoUnit.DAYS);

        return diff <= 365;}
}
