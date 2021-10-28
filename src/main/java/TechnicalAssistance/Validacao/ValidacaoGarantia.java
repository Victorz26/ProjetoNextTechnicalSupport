package TechnicalAssistance.Validacao;

import TechnicalAssistance.Entities.MarcacaoAgendamentos;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidacaoGarantia {

    public static boolean validaGarantia(MarcacaoAgendamentos marcacaoAgendamentos) {

        LocalDate dataDeHoje = LocalDate.now();

        LocalDate dataCompra =  LocalDate.parse(marcacaoAgendamentos
                .getDataDaCompra());

        long diferenca = dataCompra.until(dataDeHoje, ChronoUnit.DAYS);

        return diferenca <= 365;
    }
}
