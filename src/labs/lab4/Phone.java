package labs.lab4;

import java.util.Objects;

/**
 * The {@code Phone} class represents a mobile phone with various specifications like type, brand, model,
 * price, RAM amount, ROM amount, and screen resolution.
 * It includes getters, setters, and overrides for {@code toString}, {@code equals}, and {@code hashCode} methods.
 */
public class Phone {
    private String type;  // The type of the phone (e.g., smartphone, feature phone)
    private String brand;  // The brand of the phone (e.g., Apple, Samsung)
    private String model;  // The model name or number of the phone (e.g., iPhone 12)
    private double price;  // The price of the phone in USD
    private int ramAmount;  // The amount of RAM in the phone in GB
    private int romAmount;  // The amount of ROM (internal storage) in the phone in GB
    private ScreenResolution screenResolution;  // The screen resolution of the phone

    /**
     * Constructs a new {@code Phone} object with the specified parameters.
     *
     * @param type the type of the phone
     * @param brand the brand of the phone
     * @param model the model of the phone
     * @param price the price of the phone in USD
     * @param ramAmount the amount of RAM in the phone in GB
     * @param romAmount the amount of ROM in the phone in GB
     * @param screenResolution the screen resolution of the phone
     */
    public Phone(String type, String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.ramAmount = ramAmount;
        this.romAmount = romAmount;
        this.screenResolution = screenResolution;
    }

    // Getters and setters for the fields

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getRamAmount() {
        return ramAmount;
    }

    public int getRomAmount() {
        return romAmount;
    }

    public ScreenResolution getScreenResolution() {
        return screenResolution;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRamAmount(int ramAmount) {
        this.ramAmount = ramAmount;
    }

    public void setRomAmount(int romAmount) {
        this.romAmount = romAmount;
    }

    public void setScreenResolution(ScreenResolution screenResolution) {
        this.screenResolution = screenResolution;
    }

    /**
     * Returns a string representation of the {@code Phone} object in JSON format.
     *
     * @return a string representation of the phone's details in JSON format
     */
    @Override
    public String toString() {
        return "{\n" +
                "  \"type\": \"" + type + "\",\n" +
                "  \"brand\": \"" + brand + "\",\n" +
                "  \"model\": \"" + model + "\",\n" +
                "  \"price\": " + price + " USD\",\n" +
                "  \"ramAmount\": " + ramAmount + ",\n" +
                "  \"romAmount\": " + romAmount + ",\n" +
                "  \"screenResolution\": \"" + screenResolution + "\"\n" +
                "}";
    }

    /**
     * Compares the current {@code Phone} object with another object for equality.
     * The comparison is based on the values of all the fields.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Phone phone = (Phone) obj;

        return Double.compare(phone.price, price) == 0 &&
                ramAmount == phone.ramAmount &&
                romAmount == phone.romAmount &&
                Objects.equals(type, phone.type) &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(model, phone.model) &&
                Objects.equals(screenResolution, phone.screenResolution);
    }

    /**
     * Returns the hash code for the {@code Phone} object.
     * The hash code is generated based on the fields of the object.
     *
     * @return the hash code of the phone object
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, brand, model, price, ramAmount, romAmount, screenResolution);
    }
}