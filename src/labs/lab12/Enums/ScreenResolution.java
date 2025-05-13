package labs.lab12.Enums;

/**
 * The {@code ScreenResolution} enum represents different screen resolutions commonly used in mobile phones.
 * Each resolution is associated with its corresponding width and height in pixels.
 */
public enum ScreenResolution {
    /** 800x480 pixels (Wide VGA) */
    WVGA("800x480"),
    /** 854x480 pixels (Full Wide VGA) */
    FWVGA("854x480"),
    /** 960x540 pixels (Quarter HD) */
    QHD("960x540"),
    /** 1280x720 pixels (High Definition) */
    HD("1280x720"),
    /** 1920x1080 pixels (Full High Definition) */
    FULL_HD("1920x1080"),
    /** 2560x1440 pixels (Quad High Definition) */
    QUAD_HD("2560x1440"),
    /** 3840x2160 pixels (Ultra HD 4K) */
    UHD_4K("3840x2160"),
    /** 7680x4320 pixels (Ultra HD 8K) */
    UHD_8K("7680x4320");

    private final String resolution;

    /**
     * Constructs a {@code ScreenResolution} enum constant with the specified resolution string.
     *
     * @param resolution the screen resolution in "width x height" format
     */
    ScreenResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * Returns the resolution as a string in "width x height" format.
     *
     * @return the resolution string
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Returns a string representation of the enum constant, including its name and resolution.
     *
     * @return a formatted string representation of the screen resolution
     */
    @Override
    public String toString() {
        return name();
    }
}
