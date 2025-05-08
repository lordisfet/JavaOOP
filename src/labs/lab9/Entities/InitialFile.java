package labs.lab9.Entities;

import labs.lab9.Entities.Phones.Phone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InitialFile {
    private String fileName;
    private File inputFile;
    private Scanner fileReader;
    private static final char fieldSeparator = ';';
    private static final char valueSeparator = ':';

    public InitialFile(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty() || fileName == null) {
            throw new IllegalArgumentException("File name can`t be empty or null");
        }
        this.fileName = fileName;
        inputFile = new File(fileName);
        fileReader = new Scanner(inputFile);
    }

    public InitialFile(InitialFile other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InitialFile object");
        }

        this.fileName = other.fileName;
        this.inputFile = other.inputFile;
        this.fileReader = other.fileReader;
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

    public Scanner getFileReader() {
        return fileReader;
    }

    public void setFileReader(Scanner fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public String toString() {
        return "InitialFile{" +
                "fileName='" + fileName + '\'' +
                ", inputFile=" + inputFile +
                ", fileReader=" + fileReader +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InitialFile that = (InitialFile) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(inputFile, that.inputFile) && Objects.equals(fileReader, that.fileReader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, inputFile, fileReader);
    }

    /*public ArrayList<Phone> ReadDataFromFile() {

    }*/
}
