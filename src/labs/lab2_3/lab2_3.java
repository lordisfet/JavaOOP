package labs.lab2_3;

import java.util.Scanner;
//import numberToLatters.*;

public class lab2_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number in the range -999 to 999: ");
        int number = scanner.nextInt();

        while (Math.abs(number) > 999) {
            System.out.print("Pls enter the number IN THE RANGE -999 TO 999: ");
            number = scanner.nextInt();
        }
        int numberCopy = number;

        StringBuilder result = new StringBuilder();

        if (number == 0) {
            result.append("ноль");
        } else {
            if (number < 0) {
                result.append("мінус").append(" ");
                number = Math.abs(number);
            }

            if (number >= 100) {
                result.append(numberToLatters.Hundreds.getNumberWord(number)).append(" ");
                number %= 100;
            }

            if (number >= 10 && number <= 19) {
                result.append(numberToLatters.Teens.getNumberWord(number)).append(" ");
            } else {
                if (number >= 20) {
                    result.append(numberToLatters.Tens.getNumberWord(number)).append(" ");
                }
                if (number % 10 != 0) {
                    result.append(numberToLatters.Units.getNumberWord(number));
                }
            }
        }

        System.out.print(numberCopy + " == " + result.toString());
    }
}