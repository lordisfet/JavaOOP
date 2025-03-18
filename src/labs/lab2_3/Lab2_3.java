/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №3
 * Дата:          2025-03-18 14:00 (UTC+2)
 * Час виконання: 1 година
 *
 * Опис:
 * Програма зчитує з файлу input.txt значення від -999 до 999 та записує його у буквеному форматі в файл output.txt.
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 *
 * Використання штучного інтелекту:
 * - Довідка для New Input/Output
 * - Генерація документації для власних методів
 */

package labs.lab2_3;

import java.io.IOException;

public class Lab2_3 {
    public static void main(String[] args) throws IOException {
        String inputFile = "src/labs/lab2_3/data/input.txt";
        String outputFile = "src/labs/lab2_3/data/output.txt";

        NumberToWords number = new NumberToWords();

        // Read data from input.txt
        number.readNumberFromFile(inputFile);

        // Verbalization number
        number.verbalizationOfNumber();

        // Write data to output.txt
        number.writeWordsToFile(outputFile);
    }
}