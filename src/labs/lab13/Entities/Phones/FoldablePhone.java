package labs.lab13.Entities.Phones;

import labs.lab13.Enums.ScreenResolution;

import java.util.Objects;

/**
 * Represents a foldable smartphone that extends the functionality of {@link SmartPhone}.
 * <p>
 * This class introduces an additional feature: foldable screens. It inherits all attributes and behaviors from
 * {@link SmartPhone} while adding a dedicated field to track the number of foldable screens.
 * </p>
 */
public class FoldablePhone extends SmartPhone {
    /**
     * The number of foldable screens available in the phone.
     */
    private int foldableScreens;

    /**
     * Constructs a new {@code FoldablePhone} instance with the specified parameters.
     * <p>
     * Initializes the phone with details including brand, model, price, RAM, ROM, screen resolution, CPU cores,
     * front camera specifications, and the number of foldable screens.
     * </p>
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
     * Constructs a new {@code FoldablePhone} instance by copying attributes from an existing instance.
     *
     * @param other the {@code FoldablePhone} instance to duplicate
     * @throws IllegalArgumentException if {@code other} is {@code null}
     */
    public FoldablePhone(FoldablePhone other) {
        super(other);
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null FoldablePhone object");
        }
        this.foldableScreens = other.foldableScreens;
    }

    /**
     * Returns the number of foldable screens in the phone.
     *
     * @return the foldable screen count
     */
    public int getFoldableScreens() {
        return foldableScreens;
    }

    /**
     * Sets the number of foldable screens in the phone.
     *
     * @param foldableScreens the new foldable screen count
     */
    public void setFoldableScreens(int foldableScreens) {
        this.foldableScreens = foldableScreens;
    }

    /**
     * Returns a formatted string representation of this {@code FoldablePhone}.
     * <p>
     * Extends {@link SmartPhone#toString} by appending foldable screen details.
     * </p>
     *
     * @return a descriptive string containing the phone's information
     */
    @Override
    public String toString() {
        return super.toString() + "Foldable screens: " + foldableScreens + "\n";
    }

    /**
     * Generates a structured string representation of this {@code FoldablePhone} object for file storage.
     * <p>
     * Includes inherited attributes from {@link SmartPhone}, along with foldable screen details.
     * </p>
     *
     * @return a formatted string suitable for file storage
     */
    @Override
    public String toStringToFile() {
        return super.toStringToFile() + "foldableScreens:" + foldableScreens + ';';
    }

    /**
     * Compares this {@code FoldablePhone} instance with another object for equality.
     * <p>
     * The comparison considers all attributes, including inherited ones from {@link SmartPhone}.
     * </p>
     *
     * @param o the object to compare with
     * @return {@code true} if both instances are equivalent, {@code false} otherwise
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
     * <p>
     * The hash code includes inherited attributes along with the foldable screen count.
     * </p>
     *
     * @return a hash code representing this {@code FoldablePhone}
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foldableScreens);
    }

    /**
     * Creates a clone of this {@code FoldablePhone} instance.
     * <p>
     * Uses {@link SmartPhone#clone()} to inherit attributes while manually copying foldable screen details.
     * </p>
     *
     * @return a cloned {@code FoldablePhone} instance
     */
    @Override
    public FoldablePhone clone() {
        FoldablePhone cloned = (FoldablePhone) super.clone();
        cloned.foldableScreens = this.foldableScreens;
        return cloned;
    }
}
