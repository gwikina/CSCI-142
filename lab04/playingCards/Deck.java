package playingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A class to represent a Deck of playing cards.
 * This class is provided to you so the way a deck is created,
 * shuffled, and dealt is precisely controlled.
 *
 * @author RIT CS
 */
public class Deck {

    private ArrayList< Card > contents = new ArrayList<>();

    /**
     * Create and initialize a new deck.
     * @rit.post cards are shuffled
     * @param numSets how many complete sets of playing cards to use
     * @param randNumGen the random number generator used when shuffling
     * @see Collections#shuffle(List, Random)
     */
    public Deck( int numSets, Random randNumGen ) {
        for ( int k = 0; k < numSets; k++ ) {
            for ( Suit s : Suit.values() ) {
                for ( Rank r : Rank.values() ) {
                    contents.add( new Card( r, s ) );
                }
            }
        }
        Collections.shuffle( contents, randNumGen );
    }

    /**
     * Create and initialize a new deck from one
     * complete set of playing cards.
     * @param randNumGen the random number generator used when shuffling
     * @rit.post cards are shuffled
     */
    public Deck( Random randNumGen ) {
        this( 1, randNumGen );
    }

    /**
     * Deals the next card from the deck
     * @rit.pre deck cannot be empty
     * @return the next card off the deck
     */
    public Card dealCard() {
        return contents.remove( contents.size() - 1 );
    }

}
