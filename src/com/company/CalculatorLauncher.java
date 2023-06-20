import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;

public class CalculatorLauncher {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.startCalculator();
    }
}

class Calculator {
    public void startCalculator() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Для вычисления введите только арабские или только римские числа от 0 до 10, /n вставив между ними один из операторов * / + - ");
//        String input = scanner.nextLine();
//        String input = "3 - IV";
//        String input = "1 +2 +3";
        String input = "11 / 0";


        String result;
        input = StringManipulator.removeSpacesAndSymbols(input);




        String[] parts = StringManipulator.splitInput(input);
        //Порезать по оператору


        if (parts.length != 2) { // Проверка на количество операндов
            System.out.println("Ошибка: Некорректное количество чисел или операторов");
            return;
        }
        char operator = input.charAt(parts[0].length());

        int num1, num2, sum;
        if (StringManipulator.containsRomanNumerals(parts[0]) && StringManipulator.containsRomanNumerals(parts[1])) { //Если два операнда римские

            num1 = NumberConverter.convertRomanToArabic(parts[0]);
            num2 = NumberConverter.convertRomanToArabic(parts[1]); //Конвертируем
            sum = CalculatorEngine.calculate(num1, num2, operator);
            if (sum > 0 ) {

                result = NumberConverter.convertArabicToRoman(sum);
            } else {
                System.out.print("Ошибка. В римском счислении нет отрицательных чисел");
                return;
            }
        } else {
            try {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[1]);
                result = String.valueOf(CalculatorEngine.calculate(num1, num2, operator));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Должна быть введена только пара арабских или пара римских цифр, каждое число не больше 10");
                return;
            }
        }

        System.out.println("Результат: " + result);



//        scanner.close();
    }
}

class StringManipulator {
    public static String removeSpacesAndSymbols(String input) {
        return input.replaceAll("\\s+", "");
    }

//    public static boolean containsOperator(String input) {
//        return input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/");
//    }

    public static String[] splitInput(String input) {
        if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
            return input.split("[+\\-*/]");
        } else {
            throw new IllegalArgumentException("Некорректный оператор");
        }
    }



    public static boolean containsRomanNumerals(String input) {
        return input.matches(".*[IVX].*");
    }
}

class NumberConverter {
    public static int convertRomanToArabic(String number) {

        switch (number) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
        }

        return 11;
    }

    public static String convertArabicToRoman(int num) {
        if (num > 0) {
            StringBuilder sb = new StringBuilder();
            int times;
            String[] romans = new String[]{"I", "IV", "V", "IX", "X"};
            int[] ints = new int[]{1, 4, 5, 9, 10};
            for (int i = ints.length - 1; i >= 0; i--) {
                times = num / ints[i];
                num %= ints[i];
                while (times > 0) {
                    sb.append(romans[i]);
                    times--;
                }
            }
            return sb.toString();
        } else {
            return "Ошибка. В римском счислении нет отрицательных чисел";
        }
    }
}

class CalculatorEngine {
    public static int calculate(int num1, int num2, char operator) {
            if (num1 > 10 || num2 > 10) {
                throw new IllegalArgumentException("Одно из введённых чисел больше 10");
            }
    try {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("Деление на ноль");
                }
            default:
                throw new IllegalArgumentException("О Некорректный оператор");
        }
    } catch (Exception e) {
        System.out.println("Ошибка: " + e.getMessage());
        return Integer.MIN_VALUE;
    }
}
    }


