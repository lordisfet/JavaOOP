/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №2
 * Дата:          2025-03-11 14:00 (UTC+2)
 * Час виконання: 1 година
 *
 * Опис:
 * Програма запитує значення від -999 до 999 та виводить його у буквений формат.
 *
 * Використання штучного інтелекту:
 * - Генерування значень для Enum
 * - Довідка для класу StringBuilder
 * - Довідка для типу даних enum
 */

import java.util.Scanner;
import labs.lab1.*;
import labs.lab2_3.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of lab: ");
        int labNumber = scanner.nextInt();

        switch (labNumber) {
            case 1 -> lab1.main(null);
            case 2, 3 -> lab2_3.main(null);
        }
    }
}