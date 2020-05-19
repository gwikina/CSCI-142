package playingCards;

/**
 * An enumeration representing the ranks in a normal poker deck
 *
 * @author paw: Phil White
 * @author sps: Sean Strout
 */
public enum Rank {
    DEUCE( " 2", 2 ),
    THREE( " 3", 3 ),
    FOUR( " 4", 4 ),
    FIVE( " 5", 5 ),
    SIX( " 6", 6 ),
    SEVEN( " 7", 7 ),
    EIGHT( " 8", 8 ),
    NINE( " 9", 9 ),
    TEN( "10", 10 ),
    JACK( " J", 11 ),
    QUEEN( " Q", 12 ),
    KING( " K", 13 ),
    ACE( " A", 14 );

    /**
     * a constant for the total number of ranks
     */
    public static final int NUM_RANKS = Rank.values().length;

    /**
     * the short name for the rank, e.g. " A", for ace
     */
    private final String shortName;

    /**
     * the numeric value of the card
     */
    private final int value;

    /**
     * Initialize the ranks enums
     *
     * @param name  short name for the ranks
     * @param value the value of the rank
     */
    Rank( String name, int value ) {
        this.shortName = name;
        this.value = value;
    }

    /**
     * accessor for the name
     *
     * @return a string with the short name for this ranks
     */
    public String getShortName() {
        return this.shortName;
    }

    /**
     * accessor for the value
     *
     * @return an int for the value of the rank
     */
    public int getValue() {
        return this.value;
    }
}
