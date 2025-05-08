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

import labs.lab8.Entities.*;
import labs.lab8.Enums.ScreenResolution;
import labs.lab8.Exceptions.NoPhonesAvailableException;
import labs.lab9.Entities.InitialFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static labs.lab9.Entities.Validator.*;

public class Lab9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> phones = new ArrayList<>();
        int action;
        String fileName = "src/labs/lab9/Data/input.txt";
        try {
            InitialFile file = new InitialFile(fileName);
            System.out.println(file.readData());
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }

        do {
            System.out.println("\nMENU");
            System.out.println("1. Create new phone");
            System.out.println("2. Show all phones");
            System.out.println("3. Exit");
            System.out.print("\nChoose action: ");
            action = scanner.nextInt();

            switch (action) {
                case 1 -> {
                    Phone newPhone = addPhone();
                    if (newPhone != null) {
                        phones.add(newPhone);
                        System.out.println("\nNext obj was been added: ");
                        System.out.println(phones.getLast());
                    } else {
                        System.out.println("Returning to menu...");
                    }
                }
                case 2 -> {
                    try {
                        System.out.println("List of phones: ");
                        showAll(phones);
                    } catch (Exception e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        } while (true);

    }

    public static Phone addPhone() {
        String type = setTypeWithValidation();
        if (type.isEmpty()) return null; // повернення в головне меню

        System.out.print("Enter brand: ");
        String brand = setStringWithValidation();
        System.out.print("Enter model: ");
        String model = setStringWithValidation();
        System.out.print("Enter price: ");
        double price = setDoubleWithValidation();
        while (price < 0) {
            System.out.print("Price cannot be negative. Enter price again: ");
            price = setDoubleWithValidation();
        }
        System.out.print("Enter RAM: ");
        int ramAmount = setIntWithValidation();
        while (ramAmount < 0) {
            System.out.print("RAM cannot be negative. Enter RAM again: ");
            ramAmount = setIntWithValidation();
        }
        System.out.print("Enter ROM: ");
        int romAmount = setIntWithValidation();
        while (romAmount < 0) {
            System.out.print("ROM cannot be negative. Enter ROM again: ");
            romAmount = setIntWithValidation();
        }

        int i = 1;
        System.out.println("List of screen resolution: ");
        for (ScreenResolution scrRes : ScreenResolution.values()) {
            System.out.println("\t" + i + ". " + scrRes);
            i++;
        }
        System.out.print("Enter number of screen resolution(1-8): ");
        ScreenResolution screenResolution = setScrResWithValidation();

        return switch (type) {
            case "SmartPhone" -> addSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "KeypadPhone" -> addKeypadPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "GamingPhone" -> addGamingPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "FoldablePhone" -> addFoldablePhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "Phone" -> {
                try {
                    yield new Phone(type, brand, model, price, ramAmount, romAmount, screenResolution);
                } catch (IllegalArgumentException e) {
                    System.out.println("Input error: " + e.getMessage());
                    yield null;
                }
            }
            default -> null;
        };
    }

    public static SmartPhone addSmartPhone(String type, String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
        System.out.print("Enter number of CPU cores: ");
        int cpuCores = setIntWithValidation();
        System.out.print("Enter front camera MP: ");
        int frontCameraMP = setIntWithValidation();

        try {
            return new SmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution, cpuCores, frontCameraMP);
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
            return null;
        }
    }

    public static KeypadPhone addKeypadPhone(String type, String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
        System.out.print("Enter Count of buttons: ");
        int buttonCount = setIntWithValidation();
        System.out.print("Enter Count supported bands: ");
        int supportedBandCount = setIntWithValidation();

        try {
            return new KeypadPhone(type, brand, model, price, ramAmount, romAmount, screenResolution, buttonCount, supportedBandCount);
        } catch (IllegalArgumentException e) {
            System.out.println("Input error: " + e.getMessage());
            return null;
        }
    }

    public static GamingPhone addGamingPhone(String type, String brand, String model, double price, int ram, int rom, ScreenResolution res) {
        SmartPhone basePhone = addSmartPhone(type, brand, model, price, ram, rom, res);

        if (basePhone == null) return null;

        System.out.print("Has active cooling (true/false): ");
        boolean activeCooling = Boolean.parseBoolean(setStringWithValidation());

        return new GamingPhone(type, brand, model, price, ram, rom, res,
                basePhone.getCpuCores(), basePhone.getFrontCameraMP(), activeCooling);
    }

    public static FoldablePhone addFoldablePhone(String type, String brand, String model, double price,
                                                 int ramAmount, int romAmount, ScreenResolution screenResolution) {
        SmartPhone basePhone = addSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);

        if (basePhone == null) return null;

        System.out.print("Enter count of foldable screens: ");
        int foldableScreens = setIntWithValidation();

        return new FoldablePhone(type, brand, model, price, ramAmount, romAmount, screenResolution,
                basePhone.getCpuCores(), basePhone.getFrontCameraMP(), foldableScreens
        );
    }

    public static void showAll(ArrayList<Phone> list) {
        if (list.isEmpty()) {
            throw new NoPhonesAvailableException("list of phones is empty");
        }
        for (Phone phone : list) {
            System.out.printf(phone + "\n");
        }
    }
}