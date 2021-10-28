package TechnicalAssistance.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "APPOINTMENTS")
public class Appointments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID", nullable = false)
    private Long id;

    @Column (name = "DATES", nullable = false)
    private LocalDate dates;

    @Column(name = "HOURS", nullable = false)
    private LocalTime hours;

    @Column(name = "PURCHASE_DATE", nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER", nullable = false)
    private Customers idCustomer;

    @ManyToOne
    @JoinColumn (name = "ID_ASSISTANCE", nullable = false)
    private Assistances idAssistance;

    @ManyToOne
    @JoinColumn (name = "ID_PRODUCT", nullable = false)
    private Products idProduct;
}
