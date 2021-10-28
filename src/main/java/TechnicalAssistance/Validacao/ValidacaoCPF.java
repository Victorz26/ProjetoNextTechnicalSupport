package TechnicalAssistance.Validacao;

import java.util.InputMismatchException;

public class ValidacaoCPF {

    public static boolean eCPF(String CPF) {
        
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11)){

            return(false);
        }

        char digito10, digito11;
        int pesoDigitos, somaDigitos, restoDigitos, num;
        
        try {
        // Primeiro dígito - cálculo
            pesoDigitos = 10;
            somaDigitos = 0;
            
            for (int i = 0; i < 9; i++) { 

                num = (int)(CPF.charAt(i) - 48);
                somaDigitos = somaDigitos + (num * pesoDigitos);
                pesoDigitos = pesoDigitos - 1;
            }

            restoDigitos = 11 - (somaDigitos % 11);

            if ((restoDigitos == 10) || (restoDigitos == 11)) {

                digito10 = '0';
            } else {

                digito10 = (char)(restoDigitos + 48);
            }

        // Segundo dígito - cálculo
            pesoDigitos = 11;
            somaDigitos = 0;
            
            for(int i = 0; i < 10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                somaDigitos = somaDigitos + (num * pesoDigitos);
                pesoDigitos = pesoDigitos - 1;
            }

            restoDigitos = 11 - (somaDigitos % 11);

            if ((restoDigitos == 10) || (restoDigitos == 11)){

                digito11 = '0';
            } else {

                digito11 = (char)(restoDigitos + 48);
            }

        // Verificação dos dois digitos.
            if ((digito10 == CPF.charAt(9)) && (digito11 == CPF.charAt(10))){

                 return(true);
            } else {
                
                return(false);
            }

        } catch (InputMismatchException erro) {

                return(false);
        }
    }

}

    