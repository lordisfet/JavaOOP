package labs.lab9.Entities.Phones;

import labs.lab9.Enums.ScreenResolution;

import java.util.Objects;

/**
 * The {@code FoldablePhone} class represents a foldable phone that extends the functionality of a {@link SmartPhone}.
 * It includes additional features such as foldable screens.
 */
public class FoldablePhone extends SmartPhone {
    private int foldableScreens; // The number of foldable screens in the phone

    /**
     * Constructs a new {@code FoldablePhone} object with the specified parameters.
     * It initializes the phone with the given type, brand, model, price, RAM, ROM, screen resolution, CPU cores,
     * front camera, and the number of foldable screens.
     *
     * @param type the type of the phone (e.g., smartphone, feature phone)
     * @param brand the brand of the phone (e.g., Apple, Samsung)
     * @param model the model of the phone (e.g., iPhone 12)
     * @param price the price of the phone in USD
     * @param ram the amount of RAM in the phone in GB
     * @param rom the amount of ROM in the phone in GB
     * @param res the screen resolution of the phone
     * @param cpuCores the number of CPU cores in the phone
     * @param frontCam the number of megapixels in the front camera
     * @param foldableScreens the number of foldable screens in the phone
     */
    public FoldablePhone(String type, String brand, String model, double price, int ram, int rom,
                         ScreenResolution res, int cpuCores, int frontCam, int foldableScreens) {
        super(type, brand, model, price, ram, rom, res, cpuCores, frontCam);
        this.foldableScreens = foldableScreens;
    }

    /**
     * Returns a string representation of the {@code FoldablePhone} object, including the foldable screens.
     * This method overrides the {@link SmartPhone#toString} method.
     *
     * @return a string representation of the foldable phone details
     */
    @Override
    public String toString() {
        return super.toString() + "foldableScreens: " + foldableScreens + "\n";
    }

    public String toStringToFile() {
        return super.toString() + "foldableScreens: " + foldableScreens + ';';
    }

    /**
     * Compares the current {@code FoldablePhone} object with another object for equality.
     * The comparison is based on the values of all fields, including those from the superclass {@link SmartPhone}.
     * This method overrides the {@link SmartPhone#equals} method.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoldablePhone that = (FoldablePhone) o;
        return foldableScreens == that.foldableScreens;
    }

    /**
     * Returns a hash code value for the {@code FoldablePhone} object.
     * The hash code is calculated based on the values of all fields, including those from the superclass {@link SmartPhone}.
     * This method overrides the {@link SmartPhone#hashCode} method.
     *
     * @return a hash code value for this {@code FoldablePhone} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foldableScreens);
    }
}
