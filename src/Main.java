import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner problemInput = new Scanner(System.in);
        String problem = problemInput.nextLine();
        problem = problem.replaceAll("\\s", "");

        char[] chars = problem.toCharArray();
        String operation = null;
        boolean operatorFound = false;

        for (char c: chars){
            if (!Character.isDigit(c)) {
                if (!operatorFound)
                switch (String.valueOf(c)){
                    case "*" -> { operation = "\\*"; operatorFound = true;}
                    case "+" -> { operation = "\\+"; operatorFound = true;}
                    case "/" -> { operation = "/";   operatorFound = true;}
                    case "-" -> { operation = "-";   operatorFound = true;}
                    default ->  { wrongFormatOutput(); return;}
                }
                else
                { wrongFormatOutput(); return;}
            }
        }

        if (operation != null) {
            String[] numbers = problem.split(operation);
            var firstNum = Integer.valueOf(numbers[0]);
            var secondNum= Integer.valueOf(numbers[1]);

            if (firstNum <= 10 && firstNum >= -10 &&secondNum <= 10){
                switch (operation) {
                case "\\*" -> out.println(firstNum * secondNum);
                case "\\+" -> out.println(firstNum + secondNum);
                case "-" -> out.println(firstNum - secondNum);
                case "/" -> {
                    if (secondNum != 0) out.println(firstNum / secondNum);
                    else out.println("На ноль делить нельзя, попробуйте еще раз");
                    }
                }
            }
            else out.println("Числа больше 10 не могут быть введены, попробуйте еще раз");
        }
        else wrongFormatOutput();
    }

    static void wrongFormatOutput(){
        out.println("Формат вашего выражения не соответствует заданным требованиям - два " +
                "операнда и один оператор (+, -, /, *), попробsssуйте еще раз");
    }
}




