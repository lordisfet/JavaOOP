import java.util.Scanner;
import labs.lab1.*;
import labs.lab2_3.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of lab: ");
        int labNumber = scanner.nextInt();

        switch (labNumber) {
            case 1 -> lab1.main(null);
            case 2 -> lab2.main(null);
        }
    }
}