
public record Score(int totalPoints, int prediction) {
    public Score accumulatePoints(int additionalPoints) {
        return new Score(additionalPoints + totalPoints(), prediction());
    }
}
