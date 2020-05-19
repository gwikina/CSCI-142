package playingCards;

/**
 * An enumeration representing the suits in a normal poker deck
 *
 * @author paw: AUTHOR_FULL_NAME_HERE
 */

public enum Suit {
    CLUBS( 'C' ),
    DIAMONDS( 'D' ),
    HEARTS( 'H' ),
    SPADES( 'S' );

    /**
     * a constant for the total number of suits
     */
    public static final int NUM_SUITS = Suit.values().length;

    /**
     * the short name for the suit, e.g. 'H' for hearts
     */
    private final char shortName;

    /**
     * initialize the suit enums,
     *
     * @param name short name for the suit
     */
    Suit( char name ) {
        this.shortName = name;
    }

    /**
     * accessor for the name
     *
     * @return a char with the short name for this suit
     */
    public char getShortName() {
        return shortName;
    }
}

