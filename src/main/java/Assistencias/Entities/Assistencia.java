package Assistencias.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ASSISTENCIA")
public class Assistencia implements Serializable {

    @Column(name = "HORARIOSDISPONIVEIS", nullable = false)
    private String horariosdisponiveis;

    @Column (name = "ELETRONICOS", nullable = false)
    private String eletronicos;

    @Column (name = "ELETRODOMESTICOS", nullable = false)
    private String eletrodomesticos;

}
