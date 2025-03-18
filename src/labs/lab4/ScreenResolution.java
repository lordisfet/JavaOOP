package labs.lab4;

public enum ScreenResolution {
    WVGA("800x480"),
    FWVGA("854x480"),
    QHD("960x540"),
    HD("1280x720"),
    FULL_HD("1920x1080"),
    QUAD_HD("2560x1440"),
    UHD_4K("3840x2160"),
    UHD_8K("7680x4320");

    private final String resolution;

    ScreenResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return name() + " (" + resolution + ")";
    }
}
