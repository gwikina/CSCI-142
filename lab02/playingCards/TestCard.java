package playingCards;

import miniPoker.Card;

/**
 * A test class for the Card class
 *
 * @author paw: Phil White
 * @author sps: Sean Strout
 */
public class TestCard {
    /**
     * The main method runs the tests
     *
     * @param args command line arguments
     */
    public static void main( String args[] ) {
        Card c;
        boolean err = false;

        // create all 52 unique cards in the deck and test the accessors
        for ( Rank r : Rank.values() ) {
            for ( Suit s : Suit.values() ) {
                c = new Card( r, s );
                if ( r != c.getRank() ) {
                    System.err.println( "ERROR getRank " + r + " of " + s +
                                        " was incorrect" );
                    err = true;
                }
                if ( s != c.getSuit() ) {
                    System.err.println( "ERROR getSuit " + r + " of " + s +
                                        " was incorrect" );
                    err = true;
                }
                if ( c.value() != r.getValue() ) {
                    System.err.println( "ERROR value " + r + " of " + s +
                                        r.getValue() + " was incorrect" );
                    err = true;
                }
            }
        }

        // create an Ace of Clubs and make sure its short name is " AC"
        c = new Card( Rank.ACE, Suit.CLUBS );
        if ( !c.getShortName().equals( " AC" ) ) {
            System.err.println( "ERROR getShortname " + c.getShortName() +
                                " was incorrect" );
            err = true;
        }

        // create a Three of Spades and make sure its toString is
        // "THREE of SPADES"
        c = new Card( Rank.THREE, Suit.SPADES );
        if ( !c.toString().equals( "THREE of SPADES" ) ) {
            System.err.println( "ERROR toString " + c +
                                " was incorrect" );
            err = true;
        }

        // if no errors were encountered, indicate so
        if ( !err ) {
            System.out.println( "No errors!" );
        }
    }
}
