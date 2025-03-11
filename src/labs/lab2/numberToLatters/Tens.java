package numberToLatters;

public enum Tens {
    ZERO(""),
    TEN("десять"),
    TWENTY("двадцять"),
    THIRTY("тридцять"),
    FORTY("сорок"),
    FIFTY("п’ятдесят"),
    SIXTY("шістдесят"),
    SEVENTY("сімдесят"),
    EIGHTY("вісімдесят"),
    NINETY("дев’яносто");

    private final String word;

    Tens(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static String getNumberWord(int num) {
        return Tens.values()[num / 10].getWord();
    }
}
