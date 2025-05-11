package labs.lab9.Entities.Phones;

import labs.lab9.Enums.ScreenResolution;

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
     * @param type             the type of the phone (e.g., smartphone, feature phone)
     * @param brand            the brand of the phone (e.g., Apple, Samsung)
     * @param model            the model of the phone (e.g., iPhone 12)
     * @param price            the price of the phone in USD
     * @param ramAmount        the amount of RAM in the phone in GB
     * @param romAmount        the amount of ROM in the phone in GB
     * @param screenResolution the screen resolution of the phone
     * @throws IllegalArgumentException if any of the parameters are invalid (e.g., null or negative values)
     */
    public Phone(String type, String brand, String model, double price, int ramAmount, int romAmount, ScreenResolution screenResolution) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type can't be empty or null");
        }
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Brand can't be empty or null");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model can't be empty or null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than 0");
        }
        if (ramAmount < 0) {
            throw new IllegalArgumentException("RAM amount can't be less than 0");
        }
        if (romAmount < 0) {
            throw new IllegalArgumentException("ROM amount can't be less than 0");
        }
        if (screenResolution == null) {
            throw new IllegalArgumentException("ScreenResolution can't be null");
        }
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.ramAmount = ramAmount;
        this.romAmount = romAmount;
        this.screenResolution = screenResolution;
    }

    /**
     * Copy constructor for the {@code Phone} class.
     * Creates a new {@code Phone} object by duplicating the attributes of an existing {@code Phone} instance.
     * If the provided object is {@code null}, an {@link IllegalArgumentException} is thrown.
     *
     * @param other the {@code Phone} object to copy
     * @throws IllegalArgumentException if the provided {@code Phone} object is {@code null}
     */
    public Phone(Phone other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null Phone object");
        }

        this.type = other.type;
        this.brand = other.brand;
        this.model = other.model;
        this.price = other.price;
        this.ramAmount = other.ramAmount;
        this.romAmount = other.romAmount;
        this.screenResolution = other.screenResolution;
    }

    /**
     * Gets the type of the phone.
     *
     * @return the phone type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the brand of the phone.
     *
     * @return the phone brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the model of the phone.
     *
     * @return the phone model
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the price of the phone.
     *
     * @return the phone price in USD
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the amount of RAM in the phone.
     *
     * @return the RAM amount in GB
     */
    public int getRamAmount() {
        return ramAmount;
    }

    /**
     * Gets the amount of ROM (internal storage) in the phone.
     *
     * @return the ROM amount in GB
     */
    public int getRomAmount() {
        return romAmount;
    }

    /**
     * Gets the screen resolution of the phone.
     *
     * @return the screen resolution
     */
    public ScreenResolution getScreenResolution() {
        return screenResolution;
    }

    /**
     * Sets the type of the phone.
     *
     * @param type the phone type
     * @throws IllegalArgumentException if the type is null or empty
     */
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type can't be empty or null");
        }
        this.type = type;
    }

    /**
     * Sets the brand of the phone.
     *
     * @param brand the phone brand
     * @throws IllegalArgumentException if the brand is null or empty
     */
    public void setBrand(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Brand can't be empty or null");
        }
        this.brand = brand;
    }

    /**
     * Sets the model of the phone.
     *
     * @param model the phone model
     * @throws IllegalArgumentException if the model is null or empty
     */
    public void setModel(String model) {
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model can't be empty or null");
        }
        this.model = model;
    }

    /**
     * Sets the price of the phone.
     *
     * @param price the phone price in USD
     * @throws IllegalArgumentException if the price is less than 0
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than 0");
        }
        this.price = price;
    }

    /**
     * Sets the amount of RAM in the phone.
     *
     * @param ramAmount the amount of RAM in GB
     * @throws IllegalArgumentException if the RAM amount is less than 0
     */
    public void setRamAmount(int ramAmount) {
        if (ramAmount < 0) {
            throw new IllegalArgumentException("RAM amount can't be less than 0");
        }
        this.ramAmount = ramAmount;
    }

    /**
     * Sets the amount of ROM (internal storage) in the phone.
     *
     * @param romAmount the amount of ROM in GB
     * @throws IllegalArgumentException if the ROM amount is less than 0
     */
    public void setRomAmount(int romAmount) {
        if (romAmount < 0) {
            throw new IllegalArgumentException("ROM amount can't be less than 0");
        }
        this.romAmount = romAmount;
    }

    /**
     * Sets the screen resolution of the phone.
     *
     * @param screenResolution the screen resolution
     * @throws IllegalArgumentException if the screen resolution is null
     */
    public void setScreenResolution(ScreenResolution screenResolution) {
        if (screenResolution == null) {
            throw new IllegalArgumentException("Screen resolution can't be null");
        }
        this.screenResolution = screenResolution;
    }

    /**
     * Returns a string representation of the {@code Phone} object in JSON format.
     *
     * @return a string representation of the phone's details in JSON format
     */
    
    @Override
    public String toString() {
        return "type: " + type + "\n" +
                "brand: " + brand + "\n" +
                "model: " + model + "\n" +
                "price: " + price + "\n" +
                "ramAmount: " + ramAmount + "\n" +
                "romAmount: " + romAmount + "\n" +
                "screenResolution: " + screenResolution + "\n";
    }

    /**
     * Generates a structured string representation of the {@code Phone} object for file storage.
     * The output includes essential attributes such as type, brand, model, price, RAM, ROM, and screen resolution.
     * Each field is separated by a semicolon (';') for easy parsing when stored in a file.
     *
     * @return a formatted string containing phone details, ready for file storage
     */
    public String toStringToFile() {
        return "type:" + type + ';' +
                "brand:" + brand + ';' +
                "model:" + model + ';' +
                "price:" + price + ';' +
                "ramAmount:" + ramAmount + ';' +
                "romAmount:" + romAmount + ';' +
                "screenResolution:" + screenResolution + ';';
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
     * Returns a hash code value for the {@code Phone} object.
     * The hash code is calculated based on the values of all the fields.
     *
     * @return a hash code value for this {@code Phone} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, brand, model, price, ramAmount, romAmount, screenResolution);
    }
}
