package com.company;
import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        // Удаление пробелов и лишних символов
        input = input.replaceAll("\\s+", "");

        // Проверка наличия оператора
        if (!input.contains("+") && !input.contains("-") && !input.contains("*") && !input.contains("/")) {
            System.out.println("Ошибка: Некорректный оператор");
            return;
        }

        // Разделение строки на числа и оператор
        String[] parts = input.split("[+\\-*/]");



        // Проверка количества чисел и операторов
        if (parts.length != 2) { //количество символов больше, чем два и/или одно из чисел массива >10
            System.out.println("Ошибка: Некорректное количество чисел или операторов");
            return;
        }


        double num1, num2;
        try {
            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Некорректный формат чисел");
            return;
        }
        if (num1 >10 || num2 > 10) { //Исключение?
            System.out.println("Ошибка: Одно из введённых чисел больше 10");
            return;
        }


        char operator = input.charAt(parts[0].length());

        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.println("Результат: " + result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Результат: " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Результат: " + result);
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Результат: " + result);
                } else {
                    System.out.println("Ошибка: Деление на ноль");
                }
                break;
            default:
                System.out.println("Ошибка: Некорректный оператор");
        }

        scanner.close();
    }
}
