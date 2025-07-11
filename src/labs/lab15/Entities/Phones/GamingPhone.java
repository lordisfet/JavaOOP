package labs.lab15.Entities.Phones;

import labs.lab15.Enums.ScreenResolution;

import java.util.Objects;

/**
 * Class representing a gaming phone with active cooling.
 * It extends the {@link SmartPhone} class and adds the feature of active cooling.
 */
public class GamingPhone extends SmartPhone {

    // Indicates whether the phone has active cooling (true - has, false - does not have)
    private boolean activeCooling;

    /**
     * Constructor for initializing a new gaming phone.
     *
     * @param type The type of the phone (e.g., smartphone, tablet, etc.).
     * @param brand The brand of the phone.
     * @param model The model of the phone.
     * @param price The price of the phone.
     * @param ram The amount of RAM in gigabytes.
     * @param rom The amount of internal storage in gigabytes.
     * @param res The screen resolution.
     * @param cpuCores The number of CPU cores.
     * @param frontCam The resolution of the front camera.
     * @param activeCooling Whether the phone has active cooling (true/false).
     */
    public GamingPhone(String type, String brand, String model, double price, int ram, int rom,
                       ScreenResolution res, int cpuCores, int frontCam, boolean activeCooling) {
        super(type, brand, model, price, ram, rom, res, cpuCores, frontCam);
        this.activeCooling = activeCooling;
    }

    /**
     * Constructs a new {@code GamingPhone} instance by copying the attributes of an existing {@code GamingPhone} object.
     * This copy constructor ensures a new object retains all properties from the provided instance.
     *
     * @param other the {@code GamingPhone} object to duplicate
     */
    public GamingPhone(GamingPhone other) {
        super(other);
        this.activeCooling = other.activeCooling;
    }

    /**
     * Override of the toString() method to output detailed information about the gaming phone.
     *
     * @return A string containing the main information about the phone and active cooling status.
     */
    @Override
    public String toString() {
        return super.toString() + "gamingCooling: " +
                (activeCooling ? "Yes" : "No") + "\n";
    }

    /**
     * Generates a formatted string representation of the {@code GamingPhone} object for file storage.
     * The output includes all inherited attributes from {@link SmartPhone}, along with gaming-specific cooling details.
     *
     * @return a structured string suitable for file storage, with gaming cooling information
     */
    @Override
    public String toStringToFile() {
        return super.toStringToFile() + "GamingCooling:" +
                (activeCooling ? "Yes" : "No") + ';';
    }

    /**
     * Compares this {@code GamingPhone} object to another object for equality.
     * The comparison is based on the values of all fields, including those inherited from {@link SmartPhone}.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GamingPhone that = (GamingPhone) o;
        return activeCooling == that.activeCooling;
    }

    /**
     * Returns a hash code value for this {@code GamingPhone} object.
     * The hash code is calculated based on the values of all fields, including those inherited from {@link SmartPhone}.
     *
     * @return a hash code value for this {@code GamingPhone} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), activeCooling);
    }

    /**
     * Creates a clone of this {@code GamingPhone} instance.
     * <p>
     * This method performs a shallow copy of the {@code GamingPhone} object using {@code super.clone()},
     * ensuring that all inherited attributes are duplicated. Additionally, the {@code activeCooling} field
     * is manually copied to preserve its value in the cloned instance.
     * </p>
     * <p>
     * Since {@code super.clone()} is used, this method assumes that the parent class properly implements
     * {@link Cloneable}. If cloning fails, an {@link AssertionError} is thrown to indicate that cloning
     * should be supported.
     * </p>
     *
     * @return a cloned {@code GamingPhone} instance with identical attributes
     */
    @Override
    public GamingPhone clone() {
        GamingPhone cloned = (GamingPhone) super.clone();
        cloned.activeCooling = this.activeCooling;
        return cloned;
    }

}
