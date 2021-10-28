package Assistencias.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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
    private String data;

    @Column(name = "HORARIO", nullable = false)
    private String horario;

    @Column(name = "DATA_DA_COMPRA", nullable = false)
    private Date dataDaCompra;


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
