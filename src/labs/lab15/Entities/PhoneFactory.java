package labs.lab15.Entities;

import labs.lab15.Entities.Phones.*;
import labs.lab15.Enums.ScreenResolution;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static labs.lab15.Entities.InitialFile.getValueSeparator;
import static labs.lab15.Entities.InitialFile.getfieldSeparator;

/**
 * The {@code PhoneFactory} class provides methods for dynamically creating {@link Phone} objects.
 * It supports interactive user input as well as structured data parsing.
 */
public class PhoneFactory {

    /**
     * Creates an {@link InventoryEntry} instance based on interactive user input.
     * <p>
     * This method prompts the user for the phone type and then for various attributes such as brand, model, price, RAM, ROM, and screen resolution.
     * It verifies that numeric inputs (price, RAM, and ROM) are non-negative and displays a list of supported screen resolutions. If the user enters an
     * empty type, the method returns {@code null} (e.g. indicating that the input process should be aborted). Based on the type provided, a specific
     * phone instance is created using a switch expression. If an unknown type is entered, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @return a new {@code InventoryEntry} containing the created {@link Phone} and its amount, or {@code null} if the user provided an empty type
     * @throws IllegalArgumentException if the phone type is unrecognized or invalid
     */
    public Phone createPhoneFromInput() {
        String type = setTypeWithValidation();
        if (type.isEmpty()) return null; // Return to the main menu if no type is provided

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

        Phone newPhone = switch (type) {
            case "SmartPhone" -> createSmartPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "KeypadPhone" -> createKeypadPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "GamingPhone" -> createGamingPhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            case "FoldablePhone" ->
                    createFoldablePhone(type, brand, model, price, ramAmount, romAmount, screenResolution);
            default -> throw new IllegalArgumentException("Type of phone does not exist");
        };

        return newPhone;
    }

    /**
     * Creates a list of {@link InventoryEntry} objects by parsing a list of attribute maps.
     * <p>
     * For each map provided in the {@code attrs} list, this method extracts key-value pairs representing phone attributes (type, brand,
     * model, price, RAM, ROM, screen resolution, and amount). Based on the "type" attribute, it dynamically constructs the corresponding phone object.
     * For specific types (e.g. "SmartPhone", "KeypadPhone", "GamingPhone", "FoldablePhone"), additional attributes such as CPU cores,
     * camera megapixels, and other features are parsed and used during phone construction. If an unexpected phone type is encountered, an
     * {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param attrs an {@link ArrayList} of maps where each map contains key-value pairs corresponding to the attributes of a phone
     * @return an {@link ArrayList} of {@link InventoryEntry} objects created based on the provided attribute mappings
     * @throws IllegalArgumentException if an attribute map contains an unrecognized phone type
     */
    public ArrayList<InventoryEntry> createPhoneFromAttributes(ArrayList<Map<String, String>> attrs) {
        ArrayList<InventoryEntry> listOfPhones = new ArrayList<>();
        for (Map<String, String> phoneAttrs : attrs) {
            String type = phoneAttrs.get("type");
            String brand = phoneAttrs.get("brand");
            String model = phoneAttrs.get("model");
            double price = Double.parseDouble(phoneAttrs.get("price"));
            int ram = Integer.parseInt(phoneAttrs.get("ramAmount"));
            int rom = Integer.parseInt(phoneAttrs.get("romAmount"));
            ScreenResolution resolution = ScreenResolution.valueOf(phoneAttrs.get("screenResolution"));
            int amount = Integer.parseInt(phoneAttrs.get("amount"));

            listOfPhones.add(new InventoryEntry(switch (type) {
                case "SmartPhone" -> {
                    int cpuCores = Integer.parseInt(phoneAttrs.get("cpuCores"));
                    int frontCameraMP = Integer.parseInt(phoneAttrs.get("frontCameraMP"));
                    yield new SmartPhone(type, brand, model, price, ram, rom, resolution, cpuCores, frontCameraMP);
                }
                case "KeypadPhone" -> {
                    int buttonCount = Integer.parseInt(phoneAttrs.get("buttonCount"));
                    int supportedBandCount = Integer.parseInt(phoneAttrs.get("supportedBandCount"));
                    yield new KeypadPhone(type, brand, model, price, ram, rom, resolution, buttonCount, supportedBandCount);
                }
                case "FoldablePhone" -> {
                    int cpuCores = Integer.parseInt(phoneAttrs.get("cpuCores"));
                    int frontCameraMP = Integer.parseInt(phoneAttrs.get("frontCameraMP"));
                    int foldableScreens = Integer.parseInt(phoneAttrs.get("foldableScreens"));
                    yield new FoldablePhone(type, brand, model, price, ram, rom, resolution, cpuCores, frontCameraMP, foldableScreens);
                }
                case "GamingPhone" -> {
                    int cpuCores = Integer.parseInt(phoneAttrs.get("cpuCores"));
                    int frontCameraMP = Integer.parseInt(phoneAttrs.get("frontCameraMP"));
                    boolean activeCooling = Boolean.parseBoolean(phoneAttrs.get("activeCooling"));
                    yield new GamingPhone(type, brand, model, price, ram, rom, resolution, cpuCores, frontCameraMP, activeCooling);
                }
                default -> throw new IllegalArgumentException("Unexpected type: " + type);
            }, amount));
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
     * @param type             the type of the phone
     * @param brand            the brand name of the phone
     * @param model            the model name of the phone
     * @param price            the price of the phone
     * @param ramAmount        the amount of RAM in GB
     * @param romAmount        the amount of ROM in GB
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

        while (input.isBlank()) {
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
                \t1. SmartPhone \
                
                \t2. KeypadPhone\s
                \t3. GamingPhone \
                
                \t4. FoldablePhone\s
                \t5. Exit
                Choose type:\s""");
        int input = setIntWithValidation();

        while (input < 1 || input > 5) {
            System.out.print("Type must be one of the following: \nPhone/SmartPhone/KeypadPhone/GamingPhone/FoldablePhone: ");
            input = setIntWithValidation();
        }

        return switch (input) {
            case 1 -> "SmartPhone";
            case 2 -> "KeypadPhone";
            case 3 -> "GamingPhone";
            case 4 -> "FoldablePhone";
            case 5 -> "";
            default -> throw new IllegalStateException("Unexpected type: " + input);
        };
    }
}
