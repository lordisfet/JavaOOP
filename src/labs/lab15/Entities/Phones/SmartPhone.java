package labs.lab15.Entities.Phones;

import labs.lab15.Enums.ScreenResolution;

/**
 * Represents a smartphone, which is a specific type of {@link Phone}.
 * Contains additional characteristics such as the number of CPU cores and the resolution of the front camera.
 */
public class SmartPhone extends Phone {
    private int cpuCores;
    private int frontCameraMP;

    /**
     * Constructs a new {@code SmartPhone} object with the specified parameters.
     *
     * @param type             the type of the phone (e.g., "Smartphone")
     * @param brand            the brand of the phone (e.g., "Samsung", "Apple")
     * @param model            the model of the phone (e.g., "Galaxy S23")
     * @param price            the price of the phone in USD; must be non-negative
     * @param ramAmount        the amount of RAM in GB; must be non-negative
     * @param romAmount        the amount of ROM in GB; must be non-negative
     * @param screenResolution the screen resolution (e.g., FULL_HD, HD)
     * @param cpuCores         the number of CPU cores; must be non-negative
     * @param frontCameraMP    the resolution of the front camera in megapixels; must be non-negative
     * @throws IllegalArgumentException if any numeric value is negative
     */
    public SmartPhone(String type, String brand, String model, double price, int ramAmount, int romAmount,
                      ScreenResolution screenResolution, int cpuCores, int frontCameraMP) {
        super(type, brand, model, price, ramAmount, romAmount, screenResolution);
        if (cpuCores < 0) {
            throw new IllegalArgumentException("Amount of CPU cores can't be less than 0");
        }
        if (frontCameraMP < 0) {
            throw new IllegalArgumentException("Amount of megapixels can't be less than 0");
        }
        this.cpuCores = cpuCores;
        this.frontCameraMP = frontCameraMP;
    }

    /**
     * Constructs a new {@code SmartPhone} by copying another one.
     *
     * @param other the SmartPhone to copy
     */
    public SmartPhone(SmartPhone other) {
        super(other);
        this.cpuCores = other.cpuCores;
        this.frontCameraMP = other.frontCameraMP;
    }

    /**
     * Returns the number of CPU cores in this smartphone.
     *
     * @return the number of CPU cores
     */
    public int getCpuCores() {
        return cpuCores;
    }

    /**
     * Sets the number of CPU cores.
     *
     * @param cpuCores the number of CPU cores; must be non-negative
     * @throws IllegalArgumentException if {@code cpuCores} is negative
     */
    public void setCpuCores(int cpuCores) {
        if (cpuCores < 0) {
            throw new IllegalArgumentException("Amount of CPU cores can't be less than 0");
        }
        this.cpuCores = cpuCores;
    }

    /**
     * Returns the front camera resolution in megapixels.
     *
     * @return the front camera resolution
     */
    public int getFrontCameraMP() {
        return frontCameraMP;
    }

    /**
     * Sets the resolution of the front camera.
     *
     * @param frontCameraMP the camera resolution in megapixels; must be non-negative
     * @throws IllegalArgumentException if {@code frontCameraMP} is negative
     */
    public void setFrontCameraMP(int frontCameraMP) {
        if (frontCameraMP < 0) {
            throw new IllegalArgumentException("Amount of megapixels can't be less than 0");
        }
        this.frontCameraMP = frontCameraMP;
    }

    /**
     * Returns a string representation of this smartphone in JSON-like format.
     *
     * @return a string with all the smartphone's fields
     */
    @Override
    public String toString() {
        return super.toString() + "cpuCores: " + cpuCores + "\n"
                + "frontCameraMP: " + frontCameraMP + "\n";
    }

    /**
     * Generates a structured string representation of the {@code SmartPhone} object for file storage.
     * The output includes inherited attributes from {@link Phone}, along with CPU core count and front camera resolution.
     * Each field is separated by a semicolon (';') for easy parsing when stored in a file.
     *
     * @return a formatted string containing smartphone details, ready for file storage
     */
    @Override
    public String toStringToFile() {
        return super.toStringToFile() + "cpuCores:" + cpuCores + ';'
                + "frontCameraMP:" + frontCameraMP + ';';
    }

    /**
     * Compares this smartphone to the specified object. The result is true if and only if
     * the argument is not null, is a {@code SmartPhone} object, and all fields are equal.
     *
     * @param o the object to compare with
     * @return {@code true} if this object is equal to {@code o}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartPhone that = (SmartPhone) o;
        return cpuCores == that.cpuCores && frontCameraMP == that.frontCameraMP;
    }

    /**
     * Creates a clone of this {@code SmartPhone} instance.
     * <p>
     * This method performs a shallow copy of the {@code SmartPhone} object using {@code super.clone()},
     * ensuring that all inherited attributes are duplicated. Additionally, the {@code cpuCores} and
     * {@code frontCameraMP} fields are manually copied to preserve their values in the cloned instance.
     * </p>
     * <p>
     * Since {@code super.clone()} is used, this method assumes that the parent class properly implements
     * {@link Cloneable}. If cloning fails, an exception will be thrown.
     * </p>
     *
     * @return a cloned {@code SmartPhone} instance with identical attributes
     */
    @Override
    public SmartPhone clone() {
        SmartPhone cloned = (SmartPhone) super.clone();
        cloned.cpuCores = this.cpuCores; // Manually copying CPU core count
        cloned.frontCameraMP = this.frontCameraMP; // Manually copying front camera resolution
        return cloned;
    }

}
