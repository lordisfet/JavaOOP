package labs.lab13.Entities.Phones;

import labs.lab13.Enums.ScreenResolution;

/**
 * Represents a keypad phone, which is a subclass of {@link Phone}.
 * This type of phone typically features physical buttons and limited band support
 * compared to modern smartphones.
 *
 * <p>This class includes specific attributes such as the number of physical buttons
 * and the count of supported communication bands.</p>
 */
public class KeypadPhone extends Phone {
    private int buttonCount;
    private int supportedBandCount;

    /**
     * Constructs a new {@code KeypadPhone} object with the specified parameters.
     *
     * @param type               the type of the phone (e.g., "feature phone")
     * @param brand              the brand of the phone (e.g., Nokia, Samsung)
     * @param model              the model of the phone (e.g., 3310)
     * @param price              the price of the phone in USD
     * @param ramAmount          the amount of RAM in GB
     * @param romAmount          the amount of ROM in GB
     * @param screenResolution   the screen resolution of the phone
     * @param buttonCount        the number of physical buttons on the phone
     * @param supportedBandCount the number of supported cellular bands
     * @throws IllegalArgumentException if {@code buttonCount} or {@code supportedBandCount} is negative
     */
    public KeypadPhone(String type, String brand, String model, double price, int ramAmount, int romAmount,
                       ScreenResolution screenResolution, int buttonCount, int supportedBandCount) {
        super(type, brand, model, price, ramAmount, romAmount, screenResolution);
        if (buttonCount < 0) {
            throw new IllegalArgumentException("Count of buttons can't be less than 0");
        }
        if (supportedBandCount < 0) {
            throw new IllegalArgumentException("Count of supported bands can't be less than 0");
        }
        this.buttonCount = buttonCount;
        this.supportedBandCount = supportedBandCount;
    }

    /**
     * Copy constructor for {@code KeypadPhone}.
     *
     * @param other the keypad phone to copy from
     */
    public KeypadPhone(KeypadPhone other) {
        super(other);
        this.buttonCount = other.buttonCount;
        this.supportedBandCount = other.supportedBandCount;
    }

    /**
     * Returns the number of physical buttons.
     *
     * @return the button count
     */
    public int getButtonCount() {
        return buttonCount;
    }

    /**
     * Sets the number of physical buttons.
     *
     * @param buttonCount the new button count
     * @throws IllegalArgumentException if {@code buttonCount} is negative
     */
    public void setButtonCount(int buttonCount) {
        if (buttonCount < 0) {
            throw new IllegalArgumentException("Count of buttons can't be less than 0");
        }
        this.buttonCount = buttonCount;
    }

    /**
     * Returns the number of supported communication bands.
     *
     * @return the supported band count
     */
    public int getSupportedBandCount() {
        return supportedBandCount;
    }

    /**
     * Sets the number of supported communication bands.
     *
     * @param supportedBandCount the new supported band count
     * @throws IllegalArgumentException if {@code supportedBandCount} is negative
     */
    public void setSupportedBandCount(int supportedBandCount) {
        if (supportedBandCount < 0) {
            throw new IllegalArgumentException("Count of supported bands can't be less than 0");
        }
        this.supportedBandCount = supportedBandCount;
    }

    /**
     * Returns a string representation of the keypad phone in JSON-like format.
     *
     * @return a formatted string with phone details
     */
    @Override
    public String toString() {
        return super.toString() + "buttonCount: " + buttonCount + "\n" +
                "supportedBandCount: " + supportedBandCount + "\n";
    }

    /**
     * Generates a structured string representation of the {@code Phone} object for file storage.
     * This output includes inherited attributes from {@link SmartPhone}, along with button count and supported band count details.
     *
     * @return a formatted string suitable for file storage, containing button and supported band information
     */
    @Override
    public String toStringToFile() {
        return super.toStringToFile() + "buttonCount:" + buttonCount + ';' +
                "supportedBandCount:" + supportedBandCount + ';';
    }

    /**
     * Compares this keypad phone to another object for equality.
     * Two keypad phones are considered equal if all their fields, including inherited ones, are equal.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        KeypadPhone that = (KeypadPhone) o;
        return buttonCount == that.buttonCount && supportedBandCount == that.supportedBandCount;
    }

    /**
     * Creates a clone of this {@code KeypadPhone} instance.
     * <p>
     * This method performs a shallow copy of the {@code KeypadPhone} object using {@code super.clone()},
     * ensuring that all inherited attributes are duplicated. Additionally, the {@code buttonCount} and
     * {@code supportedBandCount} fields are manually copied to preserve their values in the cloned instance.
     * </p>
     * <p>
     * Since {@code super.clone()} is used, this method assumes that the parent class properly implements
     * {@link Cloneable}. If cloning fails, an exception will be thrown.
     * </p>
     *
     * @return a cloned {@code KeypadPhone} instance with identical attributes
     */
    @Override
    public KeypadPhone clone() {
        KeypadPhone cloned = (KeypadPhone) super.clone();
        cloned.buttonCount = this.buttonCount;
        cloned.supportedBandCount = this.supportedBandCount;
        return cloned;
    }

}
