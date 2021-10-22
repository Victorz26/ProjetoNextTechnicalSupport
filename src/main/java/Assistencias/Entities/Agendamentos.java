package Assistencias.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AGENDAMENTOS")
public class Agendamentos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID", nullable = false)
    private Long id;

    @Column (name = "DATAS", nullable = false)
    private Date data;

    @Column(name = "HORARIO", nullable = false)
    private Time horario;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Clientes idCliente;

    @ManyToOne
    @JoinColumn (name = "ID_ASSISTENCIA", nullable = false)
    private Assistencias idAssistencia;

    @ManyToOne
    @JoinColumn (name = "ID_PRODUTO", nullable = false)
    private Produtos idProduto;

}
