package numberToLatters;

public enum Hundreds {
    ZERO(""),
    HUNDRED("сто"),
    TWO_HUNDRED("двісті"),
    THREE_HUNDRED("триста"),
    FOUR_HUNDRED("чотириста"),
    FIVE_HUNDRED("п’ятсот"),
    SIX_HUNDRED("шістсот"),
    SEVEN_HUNDRED("сімсот"),
    EIGHT_HUNDRED("вісімсот"),
    NINE_HUNDRED("дев’ятсот");

    private final String word;

    Hundreds(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static String getNumberWord(int num) {
        return Hundreds.values()[num / 100].getWord();
    }
}
