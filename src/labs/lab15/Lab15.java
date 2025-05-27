/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №14
 * Час виконання: 30 хвилин
 *
 * Опис:
 * Додати до меню можливість виведення даних в відсортованому варіанті по деяким критеріям.
 * Для цього використати анонімний внутрішній клас та реалізувати інтерфейс Comparator.
 *
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 */

package labs.lab15;

import labs.lab15.Entities.InventoryEntry;
import labs.lab15.Entities.PhoneFactory;
import labs.lab15.Entities.InitialFile;

import java.io.IOException;
import java.util.*;

import labs.lab15.Entities.Store;

import static labs.lab15.Entities.PhoneFactory.*;

/*
What i need fix:
1. Delete "type" from Phone
2. Change str.isEmpty to str.isBlank
3. Transfer validations methods to separate class
4. Add check for parameters to Store constructor
5. Change InventoryEntry to Phone and Integer amount
6. Add copy constructor to all classes
 */

public class Lab15 {
    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();
        Store store = null;
        int action;
        String fileName = "src/labs/lab14/Data/input.txt";

        try {
            InitialFile file = new InitialFile(fileName);
            Map<String, String> storageSetting = file.readStoreData();
            store = new Store(storageSetting.get("name"), storageSetting.get("address"),
                    factory.createPhoneFromAttributes(file.readPhonesData()));
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }

        do {
            System.out.println("\n" + store);
            System.out.println("MENU");
            System.out.println("1. Search phone by");
            System.out.println("2. Create new phone");
            System.out.println("3. Show all phones");
            System.out.println("4. Show all phones sort by");
            System.out.println("5. Exit");
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
                                for (InventoryEntry phone : store.getPhonesByType(valueOfCriteria)) {
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
                                for (InventoryEntry phone : store.getPhonesByBrand(valueOfCriteria)) {
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
                                for (InventoryEntry phone : store.getPhonesByRamCount(valueOfCriteria)) {
                                    System.out.println(phone);
                                }
                            } catch (NullPointerException e) {
                                System.out.println("The list is empty. Elements with RAM count \"" +
                                        valueOfCriteria + "\" not exist");
                            }
                        }
                        case 4 -> System.out.println("Returning to menu...");
                    }
                }
                case 2 -> {
                    try {
                        InventoryEntry newPhone = factory.createPhoneFromInput();
                        if (newPhone != null) {
                            store.addNewPhone(newPhone);
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
                        for (InventoryEntry phone : store.getAll()) {
                            System.out.println(phone);
                        }
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("\t1. Price");
                    System.out.println("\t2. Brand");
                    System.out.println("\t3. RAM count");
                    System.out.println("\t4. Exit");

                    System.out.print("\nCriteria for sort: ");
                    int criteria = setIntWithValidation();
                    while (criteria < 1 || criteria > 4) {
                        System.out.println("Pls enter 1-4 for sort parameter");
                        System.out.print("\nCriteria for sort: ");
                        criteria = setIntWithValidation();
                    }

                    System.out.println("\nList of phones sorted:\n");
                    switch (criteria) {
                        case  1 -> store.sortByPrice().forEach(System.out::println);
                        case  2 -> store.sortByBrand().forEach(System.out::println);
                        case  3 -> store.sortByRAMCount().forEach(System.out::println);
                        case  4 -> System.out.println("Returning to menu...");
                        default -> throw new IllegalStateException("Unexpected value: " + criteria);
                    }
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    try {
                        InitialFile file = new InitialFile(fileName);
                        file.writeData(Objects.requireNonNull(store), store.getAll());
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