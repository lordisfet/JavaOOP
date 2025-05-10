package labs.lab9.Entities;

import labs.lab9.Entities.Phones.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code InitialFile} class manages file-based storage and retrieval of {@link Phone} objects.
 * It provides functionality for reading and writing structured data to a file.
 */
public class InitialFile {
    private String fileName;
    private File file;
    private static final String fieldSeparator = ";";
    private static final String valueSeparator = ":";

    /**
     * Constructs an {@code InitialFile} object with the specified file name.
     * Throws an exception if the file name is empty or null.
     *
     * @param fileName the name of the file to manage
     * @throws FileNotFoundException if the file cannot be found
     * @throws IllegalArgumentException if the file name is empty or null
     */
    public InitialFile(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can`t be empty or null");
        }
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    /**
     * Copy constructor for {@code InitialFile}.
     * Creates a new {@code InitialFile} object by duplicating the attributes of another instance.
     *
     * @param other the {@code InitialFile} object to copy
     * @throws IllegalArgumentException if the provided object is {@code null}
     */
    public InitialFile(InitialFile other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InitialFile object");
        }

        this.fileName = other.fileName;
        this.file = other.file;
    }

    /**
     * Retrieves the file name associated with this {@code InitialFile} instance.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets a new file name for this {@code InitialFile} instance.
     *
     * @param fileName the new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Retrieves the file object.
     *
     * @return the file associated with this {@code InitialFile} instance
     */
    public File getfile() {
        return file;
    }

    /**
     * Sets a new file for this {@code InitialFile} instance.
     *
     * @param file the new file to assign
     */
    public void setfile(File file) {
        this.file = file;
    }

    /**
     * Retrieves the field separator used in file parsing.
     *
     * @return the field separator character
     */
    public static String getfieldSeparator() { return fieldSeparator; }

    /**
     * Retrieves the value separator used in file parsing.
     *
     * @return the value separator character
     */
    public static String getValueSeparator() { return valueSeparator; }

    /**
     * Generates a string representation of the {@code InitialFile} instance.
     *
     * @return a formatted string containing file details
     */
    @Override
    public String toString() {
        return "InitialFile{" +
                "fileName='" + fileName + '\'' +
                ", file=" + file +
                '}';
    }

    /**
     * Compares this {@code InitialFile} instance with another object for equality.
     *
     * @param o the object to compare
     * @return {@code true} if both objects are equivalent, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InitialFile that = (InitialFile) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(file, that.file);
    }

    /**
     * Generates a hash code for this {@code InitialFile} instance.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(fileName, file);
    }

    /**
     * Reads all data from the file and returns a list of parsed attributes.
     * Parses each line in the file using {@link #parsePhoneData(String)}.
     *
     * @return a list containing maps of phone attributes
     * @throws IOException if an error occurs while reading the file
     */
    public ArrayList<Map<String, String>> readAllData() throws IOException {
        ArrayList<Map<String, String>> listOfAttributes;
        try (FileReader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            listOfAttributes = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listOfAttributes.add(parsePhoneData(line));
            }
        }

        return listOfAttributes;
    }

    /**
     * Writes an array of {@code Phone} objects to the file in a structured format.
     *
     * @param phones the list of {@code Phone} objects to write
     * @throws IOException if an error occurs while writing to the file
     */
    public void writeData(ArrayList<Phone> phones) throws IOException {
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (Phone phone : phones) {
                bufferedWriter.write("");
                bufferedWriter.append(phone.toStringToFile()).append('\n');
            }
        }
    }

    /**
     * Parses a single line of phone data and extracts key-value attributes.
     *
     * @param line the line of text containing phone details
     * @return a map of phone attributes parsed from the line
     */
    private Map<String, String> parsePhoneData(String line) {
        Map<String, String> attributes = new HashMap<>();
        String[] pairs = line.split(fieldSeparator);
        for (String pair : pairs) {
            String[] keyValue = pair.split(valueSeparator);
            if (keyValue.length == 2) {
                attributes.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }

        return attributes;
    }
}

