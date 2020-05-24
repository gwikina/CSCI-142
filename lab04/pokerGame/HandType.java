package pokerGame;

/**
 * A convenience type to represent the classification of a 2-card Poker hand
 * @author RIT CS
 */
public enum HandType {

    REGULAR, FLUSH, TWO_OF_KIND;

    /**
     * Make it so that the name of the hand type isn't quite so obnoxious.
     * @return the enum instance's name, converted to lower case
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
