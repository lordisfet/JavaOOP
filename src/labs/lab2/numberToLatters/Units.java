package numberToLatters;

public enum Units {
    ZERO(""),
    ONE("один"),
    TWO("два"),
    THREE("три"),
    FOUR("чотири"),
    FIVE("п’ять"),
    SIX("шість"),
    SEVEN("сім"),
    EIGHT("вісім"),
    NINE("дев’ять");

    private final String word;

    Units (String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static String getNumberWord(int num) {
        return Units.values()[num % 10].getWord();
    }
}

