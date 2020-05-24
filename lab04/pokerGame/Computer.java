package pokerGame;

import playingCards.Card;
import playingCards.Rank;
import playingCards.Suit;

/**
 * A computer player for 2-card poker
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */
public class Computer extends Player {

    /**
     * Define the hand that the Computer player must beat in order
     * for it to decide to stand.
     */
    private static final PokerHand MIDLAND_HAND;

    static {
        MIDLAND_HAND = new PokerHand();
        MIDLAND_HAND.addCard( new Card( Rank.QUEEN, Suit.HEARTS ) );
        MIDLAND_HAND.addCard( new Card( Rank.JACK, Suit.CLUBS ) );
    }

    // TODO Add state as needed.
    private int wins;
    private int number = 0;
    protected PokerHand deckOfCards;
    /**
     * Initialize a computer player for 2-card poker
     */
    public Computer(int number) {
        super(number);
    }

    /**
     * Determine if the computer should stand instead of fold.
     * The computer should stand if it has 50% or better chance of winning
     * We will approximate this by saying that the computer player will
     * stand if it has a flush or pair, or otherwise if it beats a hand
     * containing a Queen of Hearts and a Jack of Clubs.
     *
     * @return true iff the computer wants to stand
     */
    public boolean stand() {
        if (super.getType() == HandType.TWO_OF_KIND) {
            return true;
        }
        else {
            return super.getHand().compareTo(MIDLAND_HAND) >= 1;
        }
    }
    /**
     * Get a string representing this computerized player
     * @return all the information in {@link Player#toString()}, plus "Computer"
     */
    @Override
    public String toString() {
        return "Computer Player " + super.toString();  // TODO
    }
}
