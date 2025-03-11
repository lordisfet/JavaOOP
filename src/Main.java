import java.util.Scanner;
import labs.lab1.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of lab: ");
        int labNumber = scanner.nextInt();

        switch (labNumber) {
            case 1 -> Lab1.main(null);
        }
    }
}