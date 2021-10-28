package TechnicalAssistance.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcacaoAgendamentos {
    private String data;
    private String horario;
    private Long idCliente;
    private Long idAssistencia;
    private Long idProduto;
    private String dataDaCompra;
}
