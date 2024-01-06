package main.java.org.tomato.Wizard;

public record Card(CardValue value, CardColor color) {
    @Override
    public String toString() {
        return color().toString() + " " + value().toString();
    } 
}