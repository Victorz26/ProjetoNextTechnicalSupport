package Assistencias.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTE")
public class Cliente implements Serializable {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ID", nullable = false)
    private Long id;

    @Column (name = "CPF", nullable = false, unique = true)
    private String cpf;

    @Column (name = "NOME", nullable = false)
    private String nome;

    @Column (name = "HORARIO", nullable = false)
    private String horario;

    @Column (name = "EQUIPAMENTO", nullable = false)
    private String equipamento;

    @Column (name = "ASSISTTEC", nullable = false)
    private String assistec;



}
