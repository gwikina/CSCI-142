package playingCards;

/**
 * An enum representing the suits in a normal poker deck
 *
 * @author RIT CS
 */

public enum Suit {
	CLUBS ( 'C' ),
	DIAMONDS ( 'D' ) ,
	HEARTS ( 'H' ),
	SPADES ( 'S' );

   /**
    * a constant for the total number of suits
    */
    public static final int NUM_SUITS = 4;
    private final char shortName;

   /**
    * initialize the suit enums,
    *
    * @param    n       short name for the suit
    */
    Suit( char n ){
	shortName = n;
    }

   /**
    * accessor for the name
    *
    * @return   a char with the short name for this suit
    */
    public char getShortName(){
	return shortName;
    }
}

