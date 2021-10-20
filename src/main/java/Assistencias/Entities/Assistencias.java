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
@Table(name = "ASSISTENCIAS")
public class Assistencias implements Serializable {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ID", nullable = false)
    private Long id;

    @Column (name = "ENDERECO", nullable = false)
    private String endereco;

    @Column (name = "NOME", nullable = false)
    private String nome;

}
