package labs.lab12.Entities.Phones;

import labs.lab12.Enums.ScreenResolution;

import java.util.Objects;

/**
 * The {@code FoldablePhone} class represents a foldable smartphone that extends the functionality of a {@link SmartPhone}.
 * It introduces additional features, specifically foldable screens.
 */
public class FoldablePhone extends SmartPhone {
    private int foldableScreens; // Number of foldable screens in the phone

    /**
     * Constructs a new {@code FoldablePhone} instance with the specified parameters.
     * Initializes the phone with details including brand, model, price, RAM, ROM, screen resolution, CPU cores,
     * front camera specifications, and the number of foldable screens.
     *
     * @param type            the category of the phone (e.g., smartphone, feature phone)
     * @param brand           the manufacturer of the phone (e.g., Apple, Samsung)
     * @param model           the specific model name (e.g., iPhone 12)
     * @param price           the price of the phone in USD
     * @param ram             the amount of RAM (in GB)
     * @param rom             the storage capacity (in GB)
     * @param res             the screen resolution
     * @param cpuCores        the number of CPU cores
     * @param frontCam        the front camera resolution (in megapixels)
     * @param foldableScreens the number of foldable display panels
     */
    public FoldablePhone(String type, String brand, String model, double price, int ram, int rom,
                         ScreenResolution res, int cpuCores, int frontCam, int foldableScreens) {
        super(type, brand, model, price, ram, rom, res, cpuCores, frontCam);
        this.foldableScreens = foldableScreens;
    }

    /**
     * Copy constructor that creates a new {@code FoldablePhone} instance
     * by duplicating the attributes of an existing {@code FoldablePhone} object.
     *
     * @param other the {@code FoldablePhone} instance to copy
     */
    public FoldablePhone(FoldablePhone other) {
        super(other);
        this.foldableScreens = other.foldableScreens;
    }

    /**
     * Returns a formatted string representing the details of the {@code FoldablePhone}.
     * This method extends {@link SmartPhone#toString} by including foldable screen details.
     *
     * @return a descriptive string of the foldable phone
     */
    @Override
    public String toString() {
        return super.toString() + "Foldable screens: " + foldableScreens + "\n";
    }

    /**
     * Generates a structured string representation of the {@code FoldablePhone} object for file storage.
     * This output includes inherited attributes from {@link SmartPhone}, along with foldable screen details.
     *
     * @return a formatted string suitable for file storage, containing foldable screen information
     */
    @Override
    public String toStringToFile() {
        return super.toStringToFile() + "foldableScreens:" + foldableScreens + ';';
    }


    /**
     * Compares this {@code FoldablePhone} instance with another object for equality.
     * The comparison considers all attributes, including inherited ones from {@link SmartPhone}.
     *
     * @param o the object to compare with
     * @return {@code true} if the instances are equivalent, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoldablePhone that = (FoldablePhone) o;
        return foldableScreens == that.foldableScreens;
    }

    /**
     * Generates a hash code for this {@code FoldablePhone} instance.
     * The hash code includes both inherited attributes and unique ones from {@link SmartPhone}.
     *
     * @return a hash code representing this {@code FoldablePhone}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foldableScreens);
    }
}
