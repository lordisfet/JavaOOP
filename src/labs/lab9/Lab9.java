/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №8
 * Дата:          2025-04-22 14:00 (UTC+2)
 * Час виконання: 1 година
 *
 * Опис:
 Додати ще два об`єкти до ієрархії класів. Оновити меню з можливості виходу з пункту 1.
 * Оновити драйвер для того, щоб використовувати нові класи.
 *
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 */

package labs.lab9;

import labs.lab9.Entities.PhoneFactory;
import labs.lab9.Entities.Phones.*;
import labs.lab9.Entities.InitialFile;

import java.io.IOException;
import java.util.Scanner;

import labs.lab9.Entities.PhoneRepository;
import labs.lab9.Entities.PhoneFactory;

import static labs.lab9.Entities.PhoneFactory.setIntWithValidation;

public class Lab9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneRepository repository = new PhoneRepository();
        PhoneFactory factory = new PhoneFactory();
        int action;
        String fileName = "src/labs/lab9/Data/input.txt";

        do {
            System.out.println("\nMENU");
            System.out.println("1. Create new phone");
            System.out.println("2. Show all phones");
            System.out.println("3. Exit");
            System.out.print("\nChoose action: ");
            action = setIntWithValidation();

            switch (action) {
                case 1 -> {
                    try {
                        Phone newPhone = factory.createPhoneFromInput();
                        if (newPhone != null) {
                            repository.add(newPhone);
                            System.out.println("\nObj was been added");
                        } else {
                            System.out.println("Returning to menu...");
                        }
                    } catch (Exception e) {
                        System.out.println("Obj creating error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        System.out.println("List of phones: ");
                        for (Phone phone : repository.getAll()) {
                            System.out.println(phone);
                        }
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    try {
                        InitialFile file = new InitialFile(fileName);
                        file.writeData(repository.getAll());
                    } catch (IOException e) {
                        System.out.println("File error: " + e.getMessage());
                    }
                    return;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        } while (true);
    }
}