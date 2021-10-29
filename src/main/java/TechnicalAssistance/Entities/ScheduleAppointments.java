package TechnicalAssistance.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleAppointments {
    private String date;
    private String hour;
    private Long idCustomer;
    private Long idAssistance;
    private Long idProduct;
    private String purchaseDate;
}
