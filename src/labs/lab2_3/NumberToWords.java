package labs.lab2_3;

import java.io.IOException;
import java.nio.file.*;

/**
 * The NumberToWords class is responsible for converting an integer number
 * into its word representation, reading a number from a file,
 * writing the verbalized number to a file, and clearing file content.
 */
public class NumberToWords {
    private int number;
    private String verbalizedNumber;

    /**
     * Default constructor initializing an empty instance of NumberToWords.
     */
    public NumberToWords() {
    }

    /**
     * Constructor that initializes the number to be verbalized.
     *
     * @param num the number to be converted into words
     */
    public NumberToWords(int num) {
        number = num;
    }

    /**
     * Converts the stored number into its word representation and stores it in verbalizedNumber.
     */
    public void verbalizationOfNumber() {
        int numberCopy = number;
        StringBuilder result = new StringBuilder();

        if (numberCopy == 0) {
            result.append("ноль");
        } else {
            if (numberCopy < 0) {
                result.append("мінус").append(" ");
                numberCopy = Math.abs(numberCopy);
            }

            if (numberCopy >= 100) {
                result.append(numberToLatters.Hundreds.getNumberWord(numberCopy)).append(" ");
                numberCopy %= 100;
            }

            if (numberCopy >= 10 && numberCopy <= 19) {
                result.append(numberToLatters.Teens.getNumberWord(numberCopy)).append(" ");
            } else {
                if (numberCopy >= 20) {
                    result.append(numberToLatters.Tens.getNumberWord(numberCopy)).append(" ");
                }
                if (numberCopy % 10 != 0) {
                    result.append(numberToLatters.Units.getNumberWord(numberCopy));
                }
            }
        }

        verbalizedNumber = result.toString();
    }

    /**
     * Reads an integer number from a specified file.
     *
     * @param path the file path from which to read the number
     * @throws IOException if an I/O error occurs while reading the file
     */
    public void readNumberFromFile(String path) throws IOException {
        Path inputPath = Paths.get(path);

        System.out.print("The read number from file is: ");
        number = Integer.parseInt(Files.readString(inputPath));
        System.out.println(number);
    }

    /**
     * Writes the verbalized number representation to a specified file.
     *
     * @param path the file path to write the output
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void writeWordsToFile(String path) throws IOException {
        Path outputPath = Paths.get(path);

        System.out.print(number + " == " + verbalizedNumber);
        Files.writeString(outputPath, number + " == " + verbalizedNumber + '\n', StandardOpenOption.APPEND);
    }

    /**
     * Clears the content of the specified file.
     *
     * @param path the file path to be cleared
     * @throws IOException if an I/O error occurs while clearing the file
     */
    public void clear(String path) throws IOException {
        Path outputPath = Paths.get(path);
        Files.writeString(outputPath, "", StandardOpenOption.TRUNCATE_EXISTING);
    }
}