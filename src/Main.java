import java.util.Objects;
import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner problemInput = new Scanner(System.in);
        String problem = problemInput.nextLine();
        problem = problem.replaceAll("\\s", "");

        String[] roman = new String[100];
        romanCalculate(roman);

        char[] chars = problem.toCharArray();
        String operation = null;
        boolean operatorFound = false;

        for (char c : chars) {
            if (!Character.isDigit(c)) {
                if (!operatorFound) {
                    switch (c) {
                        case '*' -> {
                            operation = "\\*";
                            operatorFound = true;
                        }
                        case '+' -> {
                            operation = "\\+";
                            operatorFound = true;
                        }
                        case '/' -> {
                            operation = "/";
                            operatorFound = true;
                        }
                        case '-' -> {
                            operation = "-";
                            operatorFound = true;
                        }
                        default -> {
                            if (c != 'X' && c != 'I' && c != 'V'){
                                wrongFormatOutput();
                                return;
                            }
                        }
                    }
                }
                else {
                    if (c != 'X' && c != 'I' && c != 'V'){
                        wrongFormatOutput();
                        return;
                    }
                }
            }
        }

        if (operation != null) {
            String[] numbers = problem.split(operation);
            int firstIsRoman = 0, secondIsRoman = 0;


            for (String s : roman) {
                if (Objects.equals(s, numbers[0])) {
                    firstIsRoman = 1000;
                }
                else {
                    firstIsRoman -= 1;
                }
            }


            for (String s1 : roman){
                if (Objects.equals(s1,numbers[1])){
                    secondIsRoman = 1000;
                    for (int i1 = 0; i1 < roman.length; i1++) {
                        if (numbers[0].equals(roman[i1])) {
                            for (int i2 = 0; i2 < roman.length; i2++) {
                                if (numbers[1].equals(roman[i2])) {
                                    int firstNumRoman = i1 + 1;
                                    int secondNumRoman = i2 + 1;
                                    calculate(firstNumRoman, secondNumRoman, roman, operation, true);
                                }
                            }
                        }
                    }
                }
                else secondIsRoman -= 1;
            }


            if (firstIsRoman > 0 && secondIsRoman < 0 ||
                firstIsRoman < 0 && secondIsRoman > 0) {
                out.println("Используются разные системы исчисления, попробуйте еще раз");
            }
            else
            if (secondIsRoman < 0 && firstIsRoman < 0){
            var firstNum = Integer.parseInt(numbers[0]);
            var secondNum = Integer.parseInt(numbers[1]);
            calculate(firstNum, secondNum, roman, operation, false);
            }
        }
            else wrongFormatOutput();

    }


    static void wrongFormatOutput(){
        out.println("Формат вашего выражения не соответствует заданным требованиям - два " +
                "операнда и один оператор (+, -, /, *), попробуйте еще раз");
    }

    static void calculate(int firstNum, int secondNum, String [] roman, String operation, boolean isRoman){
        if (firstNum <= 10 && secondNum <= 10) {
            int sum = 0;
            switch (operation) {
                case "\\*" -> sum = firstNum * secondNum;
                case "\\+" -> sum = firstNum + secondNum;
                case "-" -> sum = firstNum - secondNum;
                case "/" -> {
                    if (secondNum != 0) sum = firstNum / secondNum;
                    else out.println("На ноль делить нельзя, попробуйте еще раз");
                }
            }
            if (isRoman){
                if (sum-1 > 0) out.println(roman[sum-1]);
                else if (sum - 1 == 0) out.println("В римской системе нет нуля, попробуйте еще раз");
                else out.println("В римской системе нет отрицательных чисел, попробуйте еще раз");
            }
            else out.println(sum);
        }
        else out.println("Числа больше 10 не могут быть введены, попробуйте еще раз");
    }

    static void romanCalculate(String[] roman){
        roman[0] = "I";
        roman[1] = "II";
        roman[2] = "III";
        roman[3] = "IV";
        roman[4] = "V";
        roman[5] = "VI";
        roman[6] = "VII";
        roman[7] = "VIII";
        roman[8] = "IX";
        roman[9] = "X";

        for (int i = 10; i < roman.length; i = i + 1 ){
            if (i < 20) {roman[i] = "X"+ roman[i - 10];}
            else if (i < 30) {roman[i] = "XX"+ roman[i - 20];}
            else if (i < 39) {roman[i] = "XXX"+ roman[i - 30];}
            else if (i == 39){roman[i] = "XL";}
            else if (i < 49) {roman[i] = "XL"+ roman[i - 40];}
            else if (i == 49) {roman[i] = "L";}
            else if (i < 60) {roman[i] = "L"+ roman[i - 50];}
            else if (i < 70) {roman[i] = "LX"+ roman[i - 60];}
            else if (i < 80) {roman[i] = "LXX"+ roman[i - 70];}
            else if (i < 89) {roman[i] = "LXXX"+ roman[i - 80];}
            else if (i == 89) {roman[i] = "XC";}
            else if (i < 99) {roman[i] = "XC"+ roman[i - 90];}
            else {roman[i] = "C";}
        }
    }
}




