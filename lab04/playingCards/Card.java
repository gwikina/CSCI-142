package playingCards;

/**
 * A class to represent a playing card with a rank and suit
 *
 * @author RIT CS
 */
public class Card implements Comparable< Card >{

    private Rank rank;
    private Suit suit;

    /**
     * Create a spesific card
     *
     * @param r the Rank of the card to create
     * @param s the Suit of the card to create
     */
    public Card( Rank r, Suit s ) {
        rank = r;
        suit = s;
    }

    /**
     * Return a numerical value related to this Card's rank
     * @see playingCards.Rank
     * @return the integer value of the rank of the card
     */
    public int value() {
        return rank.getValue();
    }

    /**
     * Accessor for the rank
     *
     * @return the Rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Accessor for the suit
     *
     * @return the Suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Return a long name for the card.
     * Example: "THREE of CLUBS"
     *
     * @return the long name of the card
     */
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Return a short, three char, name for the card.
     * Examples: " 3C", "10S" or " QH"
     *
     * @return the short name of the card
     */
    public String shortName() {
        return rank.getShortName() + suit.getShortName();
    }

    /**
     * Compare this card to another card by first checking the rank.
     * If ranks are the same, the suits are compared.
     * See {@link Rank} and {@link Suit} for ordering details.
     * @param other the card to compare against this one
     * @return a positive number if this card "wins",
     *         a negative number if the other card "wins",
     *         and 0 if both cards have the same rank and suit
     */
    @Override
    public int compareTo( Card other ) {
        if ( this.rank == other.rank ) {
            return this.suit.compareTo( other.suit );
        }
        else {
            return this.rank.compareTo( other.rank );
        }
    }
}
