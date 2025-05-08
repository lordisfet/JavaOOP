package labs.lab9.Entities;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class InitialFile {
    private String fileName;
    private File inputFile;
    private static final char fieldSeparator = ';';
    private static final char valueSeparator = ':';

    public InitialFile(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can`t be empty or null");
        }
        this.fileName = fileName;
        this.inputFile = new File(fileName);
    }

    public InitialFile(InitialFile other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InitialFile object");
        }

        this.fileName = other.fileName;
        this.inputFile = other.inputFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public String toString() {
        return "InitialFile{" +
                "fileName='" + fileName + '\'' +
                ", inputFile=" + inputFile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InitialFile that = (InitialFile) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(inputFile, that.inputFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, inputFile);
    }

    public ArrayList<String> readData() throws IOException {
        try (FileReader reader = new FileReader(inputFile);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            ArrayList<String> data = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }

            return data;
        }
    }

    /*public ArrayList<Phone> ReadDataFromFile() {

    }*/
}
