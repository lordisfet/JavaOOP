/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №13
 * Час виконання: 20 хвилин
 *
 * Опис:
 * Зробити батьківський клас абстрактним, реалізувати в ньому інтерфейс Comparable.
 * Додати меню до головного меню для виводу відсортованих даних.
 *
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 */

package labs.lab13;

import labs.lab13.Entities.InventoryEntry;
import labs.lab13.Entities.PhoneFactory;
import labs.lab13.Entities.Phones.*;
import labs.lab13.Entities.InitialFile;

import java.io.IOException;
import java.util.*;

import labs.lab13.Entities.Store;

import static labs.lab13.Entities.PhoneFactory.*;

/*
What i need fix:
1. Delete "type" from Phone
2. Change str.isEmpty to str.isBlank
3. Transfer validations methods to separate class
4. Add check for parameters to Store constructor
5. Change InventoryEntry to Phone and Integer amount
6. Add copy constructor to all classes
 */

public class Lab13 {
    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();
        Store store = null;
        int action;
        String fileName = "src/labs/lab13/Data/input.txt";

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
            System.out.println("4. Show all phones sort by price");
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
                        case 4 -> {
                            System.out.println("Returning to menu...");
                        }
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
                    ArrayList<Phone> listOfPhones = new ArrayList<>();
                    System.out.println("\nList of phones sorted:\n");
                    try {
                        for (InventoryEntry phone : store.getAll()) {
                            listOfPhones.add(phone.getPhone());
                        }
                        Collections.sort(listOfPhones);
                    } catch (Exception e) {
                        System.out.println("Exception" + e.getMessage());
                    }

                    listOfPhones.forEach(System.out::println);
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