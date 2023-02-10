package wordStat;

class Check implements CheckToken {
    public boolean checkToken(char chr) {
        return !(Character.getType(chr) == Character.DASH_PUNCTUATION ||
                Character.isLetter(chr) ||
                chr == '\'');
    }
}