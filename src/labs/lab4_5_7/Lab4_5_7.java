/*
 * Виконав:       Савченко Максим
 * Група:         ІН-22-1
 * Дисципліна:    Об'єктно-орієнтоване програмування (Java)
 * Лабораторна:   №7
 * Дата:          2025-04-015 14:00 (UTC+2)
 * Час виконання: 1.5 година
 *
 * Опис:
 * Для класу Phone додати дочірні класи та об`єкти цих класів додати в один ArrayList<Phone>.
 *
 * Це моя власна лабораторна робота та виконана вона без недозволеної допомоги
 */

package labs.lab4_5_7;

import labs.lab4_5_7.Entities.KeypadPhone;
import labs.lab4_5_7.Entities.Phone;
import labs.lab4_5_7.Entities.SmartPhone;
import labs.lab4_5_7.Enums.ScreenResolution;
import labs.lab4_5_7.Exceptions.NoPhonesAvailableException;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab4_5_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Phone> phones = new ArrayList<Phone>();
        int action;

        do{
            System.out.println("\nMENU");
            System.out.println("1. Add new phone");
            System.out.println("2. Show all phones");
            System.out.println("3. Exit");
            System.out.print("\nChoose action: ");
            action = scanner.nextInt();

            switch (action){
                case 1 -> {
                    phones.add(addPhone());
                    System.out.print("\nNext obj was been added: ");
                    System.out.println(phones.getLast());
                }
                case 2 -> {
                    try{
                        System.out.println("List of phones: ");
                        showAll(phones);
                    }
                    catch (Exception e){
                        System.out.println("Exception: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
            }
        } while (true);
    }

    public static Phone addPhone() {
        String type = setTypeWithValidation();
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

        if (type.equals("SmartPhone")) {
            return addSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
        } else if (type.equals("KeypadPhone")) {
            return addKeypadPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
        } else{
            try {
                return new Phone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
                return null;
            }
        }
    }

    private static SmartPhone addSmartPhone( String type ,String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
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

    private static KeypadPhone addKeypadPhone(String type ,String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
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
        System.out.print("Enter type (Phone/SmartPhone/KeypadPhone): ");
        String input = setStringWithValidation();
        input = input.toLowerCase().replaceAll("\\s+", "");

        while (!(input.equals("phone") || input.equals("smartphone") || input.equals("keypadphone"))) {
            System.out.println("Type must be Phone/SmartPhone/KeypadPhone");
            System.out.print("Enter type: ");
            input = setStringWithValidation();
            input = input.toLowerCase().replaceAll("\\s+", "");
        }

        switch (input) {
            case "phone": return "Phone";
            case "smartphone": return "SmartPhone";
            case "keypadphone": return "KeypadPhone";
            default:
                throw new IllegalStateException("Unexpected value: " + input);
        }
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