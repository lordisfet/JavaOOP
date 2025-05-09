package labs.lab9.Entities;

import labs.lab9.Enums.ScreenResolution;

import java.util.Scanner;

public class Validator {
    public static int setIntWithValidation() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("You need enter integer: ");
        }
        return scanner.nextInt();
    }

    public static double setDoubleWithValidation() {
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Invalid input. Enter a decimal number: ");
        }

        return scanner.nextDouble();
    }

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

    public static ScreenResolution setScrResWithValidation() {
        int scrResNum = setIntWithValidation();

        while (scrResNum < 1 || scrResNum > ScreenResolution.values().length) {
            System.out.print("Number must be in range 1 to 8: ");
            scrResNum = setIntWithValidation();
        }

        return ScreenResolution.values()[scrResNum - 1];
    }

    public static String setTypeWithValidation(int input) {
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
