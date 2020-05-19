package playingCards;

import miniPoker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class to represent a Deck of playing cards
 *
 * @author paw: Phil White
 * @author sps: Sean Strout
 */
public class Deck {
    /**
     * constant that allows the Deck to be used for multi-deck games
     */
    private final int NUM_DECKS = 1;

    /**
     * a constant for the total number of cards in game
     */
    private final int TOTAL_NUM_CARDS =
            Suit.NUM_SUITS * Rank.NUM_RANKS * NUM_DECKS;

    /**
     * the cards in the Deck
     */
    private ArrayList<Card> theCards = new ArrayList<>();

    /**
     * the index into the ArrayList for the next card to deal
     */
    private int curCard;

    /**
     * Creates and initializes a new deck (unshuffled)
     */
    public Deck() {
        initDeck();
        this.curCard = 0;
    }

    /**
     * Initializes a new deck (helper function)
     */
    private void initDeck() {
        Card tmp;
        for ( int k = 0; k < NUM_DECKS; k++ ) {
            for ( Suit s : Suit.values() ) {
                for ( Rank r : Rank.values() ) {
                    tmp = new Card( r, s );
                    this.theCards.add( tmp );
                }
            }
        }
    }

    /**
     * Deals the next card from the deck
     *
     * @return the next card off the deck
     */
    public Card dealCard() {
        Card res = this.theCards.get( this.curCard );
        this.curCard = this.curCard + 1;
        return res;
    }

    /**
     * shuffles the deck, look into the
     * {@link Collections#shuffle(List)}
     * method for help with mixing up the cards.
     */
    public void shuffle() {
        Collections.shuffle( this.theCards );
        this.curCard = 0;
    }

    /**
     * Print out the contents of the deck
     */
    public void printDeck() {
        for ( int i = 0; i < TOTAL_NUM_CARDS; i++ ) {
            System.out.print( this.theCards.get( i ).getShortName() + " " );
            if ( ( i + 1 ) % Rank.NUM_RANKS == 0 ) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
