package com.company;

import java.util.Scanner;

public class CalculatorLauncher {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.startCalculator();
    }
}

class Calculator {
    public void startCalculator() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Для вычисления введите пару арабских или пару римских чисел от 1 до 10, " +
                "вставив между ними один из операторов * / + - ");
        String input = scanner.nextLine();



        try {
            String result = calculateResult(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    private String calculateResult(String input) {
        String inputWithoutSpaces = StringManipulator.removeSpaces(input);
        String[] parts = StringManipulator.splitInput(inputWithoutSpaces);

        if (parts.length == 1) {
            throw new IllegalArgumentException("Не введено второе число");
        }
        if (inputWithoutSpaces.matches(".*[+\\-*/]{2,}.*")) {
            throw new IllegalArgumentException("Операторы не могут следовать друг за другом");
        }
//        if (parts.length > 2) {
//            throw new IllegalArgumentException("Некорректное количество чисел, можно ввести не более двух");
//        }
        if (parts.length != 2) {
            throw new IllegalArgumentException("Некорректное количество чисел, операторов или число отрицательное");
        }
        if (inputWithoutSpaces.substring(parts[0].length() + parts[1].length() + 1).matches(".*[+\\-*/].*")) {
            throw new IllegalArgumentException("Оператор может быть только один");
        }


        char operator = inputWithoutSpaces.charAt(parts[0].length());


        int num1, num2, sum;
        if (StringManipulator.containsRomanNumerals(parts[0]) && StringManipulator.containsRomanNumerals(parts[1])) {
            num1 = NumberConverter.convertRomanToArabic(parts[0]);
            num2 = NumberConverter.convertRomanToArabic(parts[1]);
            sum = CalculatorEngine.calculate(num1, num2, operator);
            if (sum < 0) {
                throw new IllegalArgumentException("В римском счислении нет отрицательных чисел");
            }
            return NumberConverter.convertArabicToRoman(sum);
        } else {
            try {
                num1 = Integer.parseInt(parts[0]);
                num2 = Integer.parseInt(parts[1]);

                            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
            }

                return String.valueOf(CalculatorEngine.calculate(num1, num2, operator));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Должна быть введена только пара арабских или пара римских цифр, " +
                        "каждое число в диапазоне от 1 до 10");
            }
        }
    }
}

class StringManipulator {
    public static String removeSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }

    public static String[] splitInput(String input) {
        if (input.matches(".*[+\\-*/].*")) {
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
            default:
                throw new IllegalArgumentException("Некорректное римское число или число больше 10");
        }
    }


        public static String convertArabicToRoman(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Результат не может быть отрицательным или равным нулю");
        }

        StringBuilder sb = new StringBuilder();
        String[] romans = new String[]{"I", "IV", "V", "IX", "X"};
        int[] ints = new int[]{1, 4, 5, 9, 10};
        for (int i = ints.length - 1; i >= 0; i--) {
            while (num >= ints[i]) {
                sb.append(romans[i]);
                num = num - ints[i];
            }
        }
        return sb.toString();
    }
}

class CalculatorEngine {
    public static int calculate(int num1, int num2, char operator) {
        if (num1 > 10 || num2 > 10) {
            throw new IllegalArgumentException("Одно из введённых чисел больше 10");
        }
        if (num1 < 1 || num2 < 1 ) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10");
        }

        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Некорректный оператор");
        }
    }
}
