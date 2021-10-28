package Assistencias.Validacao;

import Assistencias.Entities.MarcacaoAgendamentos;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ValidacaoGarantia {

    public static boolean validaGarantia(MarcacaoAgendamentos marcacaoAgendamentos) {

        LocalDate dataDeHoje = LocalDate.now();

        LocalDate dataCompra =  LocalDate.parse(marcacaoAgendamentos
                .getDataDaCompra());

        long diferenca = dataCompra.until(dataDeHoje, ChronoUnit.DAYS);

        return diferenca < 365;
    }
}
