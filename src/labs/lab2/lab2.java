/*
Виконав програміст Савченко Максим
Студент ІН-22-1
ООП на мові Java
Лабораторна робота №2
2025-03-11 14:00+02:00
1 година
Програма запитує значення від -999 до 999 та виводить його в буквений формат
Використання штучного інтелекту: Генерування значень для Enum, довідка для класу StringBuilder та типу даних enum
*/

import java.util.Scanner;
import numberToLatters.*;

public class Lab2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number in the range -999 to 999: ");
        int number = scanner.nextInt();

        while (Math.abs(number) > 999) {
            System.out.print("Pls enter the number IN THE RANGE -999 TO 999: ");
            number = scanner.nextInt();
        }
        int numberCopy = number;

        StringBuilder result = new StringBuilder();

        if (number == 0) {
            result.append("ноль");
        } else {
            if (number < 0) {
                result.append("мінус").append(" ");
                number = Math.abs(number);
            }

            if (number >= 100) {
                result.append(Hundreds.getNumberWord(number)).append(" ");
                number %= 100;
            }

            if (number >= 10 && number <= 19) {
                result.append(Teens.getNumberWord(number)).append(" ");
            } else {
                if (number >= 20) {
                    result.append(Tens.getNumberWord(number)).append(" ");
                }
                if (number % 10 != 0) {
                    result.append(Units.getNumberWord(number));
                }
            }
        }

        System.out.print(numberCopy + " == " + result.toString());
    }
}