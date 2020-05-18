/**
 * Gideon Wikina
 * CS2 Mini Poker
 */
package miniPoker;
import playingCards.Rank;
import playingCards.Suit;

public class Card {
    // class for cards
    private Rank rank;
    private Suit suit;
    public Card(Rank rank, Suit suit){
        /**
         * Constructor that that sets the cards rank value to the defined rank and the suit value to the defined suit
         *
         * @rank the new rank value
         *
         * @suit the new suit value
         */
        this.rank = rank;
        this.suit = suit;
    }
    public Rank getRank(){
        /**
         * Accessor for the rank
         */
        return rank;
    }
    public String getShortName(){
        /**
         * Returns a short, three char, name for the card, ie " 3C", "10S" or " QH"
         */
        return rank.getShortName() + suit.getShortName();
    }
    public String toString(){
        /**
         * Returns a long name for the card, ie "THREE of CLUBS
         */
        return rank.name() + " of " + suit.name();
    }
    public Suit getSuit(){
        /**
         * accessor for the suit
         */
        return suit;
    }
    public int value(){
        /**
         * Return the numerical value of the card
         */
        return rank.getValue();
    }
}
