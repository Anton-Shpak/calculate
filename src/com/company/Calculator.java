package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Calculator {
        public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);
            String soscan = scanner;
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String stringWithoutSpaces = deleteSpaces(scanner);
            System.out.println(stringWithoutSpaces);
//            int name = Integer.parseInt(reader.readLine());
//            System.out.println(name);







//            int intAge = Integer.parseInt(reader.readLine());


/*
            Scanner scanner = new Scanner(System.in);
            double num1 = scanner.nextInt();
            System.out.print("Введите первое число: ");
            double num1 = scanner.nextDouble();

            System.out.print("Введите второе число: ");
            double num2 = scanner.nextDouble();

            System.out.println("Выберите операцию (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

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
                    System.out.println("Ошибка: Некорректная операция");
            }

            scanner.close();*/
        }
    public static String deleteSpaces(String string) {
        //\s означает пробелы \s+ большие пробелы и табуляции ",?\\s+" (,? – запятые, которых может не быть
        return string.replaceAll("\\s+","");

    }
    }

