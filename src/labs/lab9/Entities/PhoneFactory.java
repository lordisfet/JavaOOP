package labs.lab9.Entities;

import labs.lab9.Entities.Phones.*;
import labs.lab9.Enums.ScreenResolution;

import java.util.Scanner;

import static labs.lab9.Entities.Validator.*;
import static labs.lab9.Entities.Validator.setIntWithValidation;

public class PhoneFactory {
    private final Scanner scanner = new Scanner(System.in);

    public Phone createPhoneFromInput() {
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
            case "Phone" -> new Phone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "SmartPhone" -> createSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "KeypadPhone" -> createKeypadPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "GamingPhone" -> createGamingPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "FoldablePhone" -> createFoldablePhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            default -> throw new IllegalArgumentException("Type of phone not exists");
        };
    }

    private SmartPhone createSmartPhone(String type, String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
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

    private KeypadPhone createKeypadPhone(String type, String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
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

    private GamingPhone createGamingPhone(String type, String brand, String model, double price, int ram, int rom, ScreenResolution res) {
        SmartPhone basePhone = createSmartPhone(type, brand, model, price, ram, rom, res);

        if (basePhone == null) return null;

        System.out.print("Has active cooling (true/false): ");
        boolean activeCooling = Boolean.parseBoolean(setStringWithValidation());

        return new GamingPhone(type, brand, model, price, ram, rom, res,
                basePhone.getCpuCores(), basePhone.getFrontCameraMP(), activeCooling);
    }

    private FoldablePhone createFoldablePhone(String type, String brand, String model, double price,
                                          int ramAmount, int romAmount, ScreenResolution screenResolution) {
        SmartPhone basePhone = createSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);

        if (basePhone == null) return null;

        System.out.print("Enter count of foldable screens: ");
        int foldableScreens = setIntWithValidation();

        return new FoldablePhone(type, brand, model, price, ramAmount, romAmount, screenResolution,
                basePhone.getCpuCores(), basePhone.getFrontCameraMP(), foldableScreens
        );
    }
}
