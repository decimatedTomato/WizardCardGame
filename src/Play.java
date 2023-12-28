public record Play(Player player, Card card) {
    static final int WIZARD_VALUE = +999_999_999;
    static final int JESTER_VALUE = -999_999_999;
    static final int TRUMP_ADVANTAGE = CardValue.values().length * 2;
    static final int STARTING_COLOR_ADVANTAGE = CardValue.values().length;
    
    public int getEvaluation(CardColor trumpColor, CardColor startingColor) {
        if (card.value() == CardValue.WIZARD) {
            return WIZARD_VALUE;
        } else if (card.value() == CardValue.JESTER) {
            return JESTER_VALUE;
        }
        return card.value().ordinal()
            + ((card.color() == startingColor) ? STARTING_COLOR_ADVANTAGE : 0)
            + ((card.color() == trumpColor) ? TRUMP_ADVANTAGE : 0);
    }
}
