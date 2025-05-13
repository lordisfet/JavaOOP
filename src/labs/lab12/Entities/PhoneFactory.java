package labs.lab12.Entities;

import labs.lab12.Entities.Phones.*;
import labs.lab12.Enums.ScreenResolution;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static labs.lab12.Entities.InitialFile.getValueSeparator;
import static labs.lab12.Entities.InitialFile.getfieldSeparator;

/**
 * The {@code PhoneFactory} class provides methods for dynamically creating {@link Phone} objects.
 * It supports interactive user input as well as structured data parsing.
 */
public class PhoneFactory {

    /**
     * Creates a {@link Phone} instance based on user input.
     * Users specify phone attributes interactively, including brand, model, price, RAM, ROM, and screen resolution.
     * Throws an exception if the provided phone type is invalid.
     *
     * @return a new {@code Phone} object based on user input, or {@code null} if input is empty
     * @throws IllegalArgumentException if the phone type does not exist
     */
    public Phone createPhoneFromInput() {
        String type = setTypeWithValidation();
        if (type.isEmpty()) return null; // Return to the main menu

        System.out.println("Cannot use '" + getfieldSeparator() + "' or '" + getValueSeparator() + "' in data");
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

        System.out.println("List of screen resolutions: ");
        int i = 1;
        for (ScreenResolution scrRes : ScreenResolution.values()) {
            System.out.println("\t" + i + ". " + scrRes);
            i++;
        }
        System.out.print("Enter screen resolution number (1-8): ");
        ScreenResolution screenResolution = setScrResWithValidation();

        return switch (type) {
            case "Phone" -> new Phone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "SmartPhone" -> createSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "KeypadPhone" -> createKeypadPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "GamingPhone" -> createGamingPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "FoldablePhone" ->
                    createFoldablePhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            default -> throw new IllegalArgumentException("Type of phone does not exist");
        };
    }

    /**
     * Creates a list of {@code Phone} objects from structured attribute mappings.
     * Parses key-value attribute pairs and constructs appropriate phone instances dynamically.
     *
     * @param attrs a list of maps containing phone attributes
     * @return a list of {@code Phone} objects based on the provided attributes
     * @throws IllegalArgumentException if an unexpected phone type is encountered
     */
    public ArrayList<Phone> createPhoneFromAttributes(ArrayList<Map<String, String>> attrs) {
        ArrayList<Phone> listOfPhones = new ArrayList<>();
        for (Map<String, String> phoneAttrs : attrs) {
            String type = phoneAttrs.get("type");
            String brand = phoneAttrs.get("brand");
            String model = phoneAttrs.get("model");
            double price = Double.parseDouble(phoneAttrs.get("price"));
            int ram = Integer.parseInt(phoneAttrs.get("ramAmount"));
            int rom = Integer.parseInt(phoneAttrs.get("romAmount"));
            ScreenResolution resolution = ScreenResolution.valueOf(phoneAttrs.get("screenResolution"));

            switch (type) {
                case "Phone" -> listOfPhones.add(new Phone(type, brand, model, price, ram, rom, resolution));
                case "SmartPhone" -> {
                    int cpuCores = Integer.parseInt(phoneAttrs.get("cpuCores"));
                    int frontCameraMP = Integer.parseInt(phoneAttrs.get("frontCameraMP"));
                    listOfPhones.add(new SmartPhone(type, brand, model, price, ram, rom, resolution, cpuCores, frontCameraMP));
                }
                case "KeypadPhone" -> {
                    int buttonCount = Integer.parseInt(phoneAttrs.get("buttonCount"));
                    int supportedBandCount = Integer.parseInt(phoneAttrs.get("supportedBandCount"));
                    listOfPhones.add(new KeypadPhone(type, brand, model, price, ram, rom, resolution, buttonCount, supportedBandCount));
                }
                case "FoldablePhone" -> {
                    int cpuCores = Integer.parseInt(phoneAttrs.get("cpuCores"));
                    int frontCameraMP = Integer.parseInt(phoneAttrs.get("frontCameraMP"));
                    int foldableScreens = Integer.parseInt(phoneAttrs.get("foldableScreens"));
                    listOfPhones.add(new FoldablePhone(type, brand, model, price, ram, rom, resolution, cpuCores, frontCameraMP, foldableScreens));
                }
                case "GamingPhone" -> {
                    int cpuCores = Integer.parseInt(phoneAttrs.get("cpuCores"));
                    int frontCameraMP = Integer.parseInt(phoneAttrs.get("frontCameraMP"));
                    boolean activeCooling = Boolean.parseBoolean(phoneAttrs.get("activeCooling"));
                    listOfPhones.add(new GamingPhone(type, brand, model, price, ram, rom, resolution, cpuCores, frontCameraMP, activeCooling));
                }
                default -> throw new IllegalArgumentException("Unexpected type: " + type);
            }
        }

        return listOfPhones;
    }

    /**
     * Creates a {@link SmartPhone} instance with user-provided specifications.
     * Prompts the user to enter CPU core count and front camera resolution.
     * Handles invalid input by catching exceptions and returning {@code null}.
     *
     * @param type             the type of phone
     * @param brand            the brand name of the phone
     * @param model            the model name of the phone
     * @param price            the price of the phone
     * @param ramAmount        the amount of RAM in GB
     * @param romAmount        the amount of ROM in GB
     * @param screenResolution the screen resolution of the phone
     * @return a new {@link SmartPhone} instance or {@code null} if input validation fails
     */
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

    /**
     * Creates a {@link KeypadPhone} instance with user-provided specifications.
     * Requests the user to input the number of physical buttons and supported frequency bands.
     *
     * @param type             the type of phone
     * @param brand            the brand name of the phone
     * @param model            the model name of the phone
     * @param price            the price of the phone
     * @param ramAmount        the amount of RAM in GB
     * @param romAmount        the amount of ROM in GB
     * @param screenResolution the screen resolution of the phone
     * @return a new {@link KeypadPhone} instance or {@code null} if input validation fails
     */
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

    /**
     * Creates a {@link GamingPhone} instance by first constructing a {@link SmartPhone} base phone.
     * Adds gaming-specific attributes such as active cooling.
     *
     * @param type  the type of phone
     * @param brand the brand name of the phone
     * @param model the model name of the phone
     * @param price the price of the phone
     * @param ram   the amount of RAM in GB
     * @param rom   the amount of ROM in GB
     * @param res   the screen resolution of the phone
     * @return a new {@link GamingPhone} instance or {@code null} if input validation fails
     */
    private GamingPhone createGamingPhone(String type, String brand, String model, double price, int ram, int rom, ScreenResolution res) {
        SmartPhone basePhone = createSmartPhone(type, brand, model, price, ram, rom, res);

        if (basePhone == null) return null;

        System.out.print("Has active cooling (true/false): ");
        boolean activeCooling = Boolean.parseBoolean(setStringWithValidation());

        return new GamingPhone(type, brand, model, price, ram, rom, res,
                basePhone.getCpuCores(), basePhone.getFrontCameraMP(), activeCooling);
    }

    /**
     * Creates a {@link FoldablePhone} instance based on user input.
     * First, a {@link SmartPhone} object is created as a base phone, then foldable screen details are added.
     * If base phone creation fails, the method returns {@code null}.
     *
     * @param type the type of the phone
     * @param brand the brand name of the phone
     * @param model the model name of the phone
     * @param price the price of the phone
     * @param ramAmount the amount of RAM in GB
     * @param romAmount the amount of ROM in GB
     * @param screenResolution the screen resolution of the phone
     * @return a new {@link FoldablePhone} instance or {@code null} if base phone creation fails
     */
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

    /**
     * Validates and returns an integer input from the user.
     * Continually prompts until a valid integer is entered.
     *
     * @return a validated integer
     */
    public static int setIntWithValidation() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("You need enter integer: ");
        }
        return scanner.nextInt();
    }

    /**
     * Validates and returns a decimal (double) input from the user.
     * Continually prompts until a valid double is entered.
     *
     * @return a validated decimal number
     */
    public static double setDoubleWithValidation() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Invalid input. Enter a decimal number: ");
        }

        return scanner.nextDouble();
    }

    /**
     * Reads a user input string from the console, ensuring it is not empty.
     * Keeps prompting until a valid, non-empty input is entered.
     *
     * @return a validated, non-empty string entered by the user
     */
    public static String setStringWithValidation() {
        Scanner scanner = new Scanner(System.in);
        String input;

        input = scanner.nextLine().trim();

        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. Enter text again: ");
            input = scanner.nextLine().trim();
        }

        return input;
    }

    /**
     * Validates user input for screen resolution selection.
     * Ensures the entered number corresponds to a valid {@link ScreenResolution} option.
     *
     * @return a {@link ScreenResolution} object based on validated user input
     */
    public static ScreenResolution setScrResWithValidation() {
        int scrResNum = setIntWithValidation();
        while (scrResNum < 1 || scrResNum > ScreenResolution.values().length) {
            System.out.print("Number must be in range 1 to 8: ");
            scrResNum = setIntWithValidation();
        }

        return ScreenResolution.values()[scrResNum - 1];
    }

    /**
     * Prompts the user to select a phone type from a predefined list.
     * Ensures the input falls within the valid range of options.
     *
     * @return a string representing the selected phone type, or an empty string if the user exits
     * @throws IllegalStateException if an unexpected type is selected
     */
    public static String setTypeWithValidation() {
        System.out.print("""
                \t1. Phone\s
                \t2. SmartPhone \
                
                \t3. KeypadPhone\s
                \t4. GamingPhone \
                
                \t5. FoldablePhone\s
                \t6. Exit
                Choose type:\s""");
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
}
