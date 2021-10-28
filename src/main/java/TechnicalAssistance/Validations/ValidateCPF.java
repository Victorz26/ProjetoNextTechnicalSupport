package TechnicalAssistance.Validations;

import java.util.InputMismatchException;

public class ValidateCPF {

    public static boolean isCPF(String cpf) {
        
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11)){

            return(false);
        }

        char digit10, digit11;
        int digitWeight, digitSum, digitRemainder, number;
        
        try {

            digitWeight = 10;
            digitSum = 0;
            
            for (int i = 0; i < 9; i++) { 

                number = (int)(cpf.charAt(i) - 48);
                digitSum = digitSum + (number * digitWeight);
                digitWeight = digitWeight - 1;
            }

            digitRemainder = 11 - (digitSum % 11);

            if ((digitRemainder == 10) || (digitRemainder == 11)) {

                digit10 = '0';
            } else {

                digit10 = (char)(digitRemainder + 48);
            }


            digitWeight = 11;
            digitSum = 0;
            
            for(int i = 0; i < 10; i++) {
                number = (int)(cpf.charAt(i) - 48);
                digitSum = digitSum + (number * digitWeight);
                digitWeight = digitWeight - 1;
            }

            digitRemainder = 11 - (digitSum % 11);

            if ((digitRemainder == 10) || (digitRemainder == 11)){

                digit11 = '0';
            } else {

                digit11 = (char)(digitRemainder + 48);
            }


            if ((digit10 == cpf.charAt(9)) && (digit11 == cpf.charAt(10))){

                 return(true);
            } else {
                
                return(false);
            }

        } catch (InputMismatchException error) {

                return(false);
        }
    }

}

    