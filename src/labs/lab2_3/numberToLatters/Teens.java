package numberToLatters;

public enum Teens {
    TEN("десять"),
    ELEVEN("одинадцять"),
    TWELVE("дванадцять"),
    THIRTEEN("тринадцять"),
    FOURTEEN("чотирнадцять"),
    FIFTEEN("п’ятнадцять"),
    SIXTEEN("шістнадцять"),
    SEVENTEEN("сімнадцять"),
    EIGHTEEN("вісімнадцять"),
    NINETEEN("дев’ятнадцять");

    private final String word;

    Teens(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static String getNumberWord(int num) {
        return Teens.values()[(num % 10)].getWord();
    }
}

