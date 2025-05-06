import java.io.IOException;
import java.util.Scanner;

import labs.lab1.*;
import labs.lab2_3.*;
import labs.lab8.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of lab: ");
        int labNumber = scanner.nextInt();

        switch (labNumber) {
            case 1 -> Lab1.main(null);
            case 2, 3 -> Lab2_3.main(null);
            case 4, 5 -> Lab8.main(null);
        }
    }
}