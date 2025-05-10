/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №10
 * Час виконання: 3 години
 *
 * Опис:
 * Додати збереження елементів доданих у файл та їх читання перед початком роботи програми
 *
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 */
/*
type:SmartPhone;brand:Samsung;model:Galaxy S24;price:1299.99;ramAmount:12;romAmount:512;screenResolution:QUAD_HD;cpuCores:8;frontCameraMP:50;
type:KeypadPhone;brand:Nokia;model:3310;price:49.99;ramAmount:0;romAmount:0;screenResolution:WVGA;buttonCount:20;supportedBandCount:2;
type:FoldablePhone;brand:Huawei;model:Mate X3;price:1999.0;ramAmount:12;romAmount:512;screenResolution:QUAD_HD;cpuCores:8;frontCameraMP:40;foldableScreens:2;
type:GamingPhone;brand:ASUS;model:ROG Phone 7;price:1099.0;ramAmount:16;romAmount:1024;screenResolution:FULL_HD;cpuCores:8;frontCameraMP:32;coolingSystem:true;
*/
package labs.lab9;

import labs.lab9.Entities.PhoneFactory;
import labs.lab9.Entities.Phones.*;
import labs.lab9.Entities.InitialFile;

import java.io.IOException;

import labs.lab9.Entities.PhoneRepository;

import static labs.lab9.Entities.PhoneFactory.setIntWithValidation;

public class Lab9 {
    public static void main(String[] args) {
        PhoneRepository repository = new PhoneRepository();
        PhoneFactory factory = new PhoneFactory();
        int action;
        String fileName = "src/labs/lab9/Data/input.txt";

        try {
            InitialFile file = new InitialFile(fileName);
            for (Phone phone : factory.createPhoneFromAttributes(file.readAllData())) {
                repository.add(phone);
            }
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }

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
                        System.out.println("List of phones:\n");
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