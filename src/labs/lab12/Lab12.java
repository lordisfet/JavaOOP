/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №11
 * Час виконання: 20 хвилин
 *
 * Опис:
 * Додати до програми можливість пошуку за трьома, як мінімум,
 * критеріями та вивід їх результату виконанняна екран
 *
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 */

package labs.lab12;

import labs.lab12.Entities.InventoryEntry;
import labs.lab12.Entities.PhoneFactory;
import labs.lab12.Entities.Phones.*;
import labs.lab12.Entities.InitialFile;

import java.io.IOException;

import labs.lab12.Entities.Store;

import static labs.lab12.Entities.PhoneFactory.*;

public class Lab12 {
    public static void main(String[] args) {
        Store repository = new Store();
        PhoneFactory factory = new PhoneFactory();
        int action;
        String fileName = "src/labs/lab9/Data/input.txt";

        try {
            InitialFile file = new InitialFile(fileName);
            for (InventoryEntry phone : factory.createPhoneFromAttributes(file.readAllData())) {
                repository.addNewPhone(phone);
            }
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }

        do {
            System.out.println("\nMENU");
            System.out.println("1. Search phone by");
            System.out.println("2. Create new phone");
            System.out.println("3. Show all phones");
            System.out.println("4. Exit");
            System.out.print("\nChoose action: ");
            action = setIntWithValidation();

            switch (action) {
                case 1 -> {
                    int criteria;

                    System.out.print("""
                            \t1. Type
                            \t2. Brand
                            \t3. RAM count
                            \t4. Exit from searching
                            """);
                    System.out.print("\nCriteria for search: ");
                    criteria = setIntWithValidation();
                    switch (criteria) {
                        case 1 -> {
                            System.out.print("Enter type:\n");
                            String valueOfCriteria = setTypeWithValidation();
                            if (valueOfCriteria.isEmpty()) {
                                System.out.println("Returning to menu...");
                                break;
                            }
                            System.out.println("\nList of phones by type:\n");

                            try {
                                for (InventoryEntry phone : repository.getPhonesByType(valueOfCriteria)) {
                                    System.out.println(phone);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("The list is empty. Elements with type \"" +
                                        valueOfCriteria + "\" not exist");
                            }
                        }
                        case 2 -> {
                            System.out.print("Enter brand: ");
                            String valueOfCriteria = setStringWithValidation();
                            System.out.println("\nList of phones by type:\n");

                            try {
                                for (InventoryEntry phone : repository.getPhonesByBrand(valueOfCriteria)) {
                                    System.out.println(phone);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("The list is empty. Elements with brand \"" +
                                        valueOfCriteria + "\" not exist");
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter RAM count: ");
                            int valueOfCriteria = setIntWithValidation();
                            System.out.println("\nList of phones by type:\n");

                            try {
                                for (InventoryEntry phone : repository.getPhonesByRamCount(valueOfCriteria)) {
                                    System.out.println(phone);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("The list is empty. Elements with RAM count \"" +
                                        valueOfCriteria + "\" not exist");
                            }
                        }
                        case 4 -> {
                            System.out.println("Returning to menu...");
                        }
                    }
                }
                case 2 -> {
                    try {
                        InventoryEntry newPhone = factory.createPhoneFromInput();
                        if (newPhone != null) {
                            repository.addNewPhone(newPhone);
                            System.out.println("\nObj was been added");
                        } else {
                            System.out.println("Returning to menu...");
                        }
                    } catch (Exception e) {
                        System.out.println("Obj creating error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        System.out.println("\nList of phones:\n");
                        for (InventoryEntry phone : repository.getAll()) {
                            System.out.println(phone);
                        }
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                }
                case 4 -> {
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