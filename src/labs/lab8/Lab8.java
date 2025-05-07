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

package labs.lab8;

import labs.lab8.Entities.*;
import labs.lab8.Enums.ScreenResolution;
import labs.lab8.Exceptions.NoPhonesAvailableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lab8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> phones = new ArrayList<Phone>();
        int action;

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

    public static SmartPhone addSmartPhone( String type ,String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
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

    public static KeypadPhone addKeypadPhone(String type ,String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
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

    public static String setTypeWithValidation() {
        System.out.print("\t1. Phone \n\t2. SmartPhone " +
                "\n\t3. KeypadPhone \n\t4. GamingPhone " +
                "\n\t5. FoldablePhone \n\t6. Exit from create phone\nChoose type: ");
        int input = setIntWithValidation();

        while (input < 1 || input > 6) {
            System.out.print("Type must be one of the following: \nPhone/SmartPhone/KeypadPhone/GamingPhone/FoldablePhone: ");
            input = setIntWithValidation();
        }

        return switch (input) {
            case 1 -> "Phone";
            case 2 -> "SmartPhone";
            case 3 -> "KeypadPhone";
            case 4 -> "GamingPhone";
            case 5 -> "FoldablePhone";
            case 6 -> "";
            default -> throw new IllegalStateException("Unexpected type: " + input);
        };
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

    public static int setIntWithValidation() {
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter number: ");

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("You need enter integer: ");
        }
        return scanner.nextInt();
    }

    public static double setDoubleWithValidation() {
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a decimal number: ");

        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Invalid input. Enter a decimal number: ");
        }

        return scanner.nextDouble();
    }

    public static String setStringWithValidation() {
        Scanner scanner = new Scanner(System.in);
        String input;

//        System.out.print("Enter text: ");
        input = scanner.nextLine().trim();

        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. Enter text again: ");
            input = scanner.nextLine().trim();
        }

        return input;
    }

    public static ScreenResolution setScrResWithValidation() {
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter number of screen resolution(1-8): ");
        int scrResNum = setIntWithValidation();
        while (scrResNum < 1 || scrResNum > ScreenResolution.values().length) {
            System.out.print("Number must be in range 1 to 8: ");
            scrResNum = setIntWithValidation();
        }

        return ScreenResolution.values()[scrResNum - 1];
    }

    public static void showAll(ArrayList<Phone> list) {
        if (list.isEmpty()){
            throw new NoPhonesAvailableException("list of phones is empty");
        }
        for (Phone phone : list) {
            System.out.printf(phone + "\n");
        }
    }
}