package labs.lab9.Entities;

import labs.lab9.Entities.Phones.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InitialFile {
    private String fileName;
    private File file;
    private static final String fieldSeparator = ";";
    private static final String valueSeparator = ":";

    public InitialFile(String fileName) throws FileNotFoundException {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can`t be empty or null");
        }
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public InitialFile(InitialFile other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InitialFile object");
        }

        this.fileName = other.fileName;
        this.file = other.file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getfile() {
        return file;
    }

    public void setfile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "InitialFile{" +
                "fileName='" + fileName + '\'' +
                ", file=" + file +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InitialFile that = (InitialFile) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, file);
    }

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

    public void writeData(ArrayList<Phone> phones) throws IOException {
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (Phone phone : phones) {
                bufferedWriter.write("");
                bufferedWriter.append(phone.toStringToFile() + '\n');
            }
        }
    }

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
