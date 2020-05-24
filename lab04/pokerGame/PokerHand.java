package pokerGame;

import playingCards.Card;

public class PokerHand {
    // run operations on the card
    private Card[] Cards= new Card[2];
    private int size;
    public int val;

    public PokerHand(){
        /**
         * makes a deck of cards
         *
         */
        Cards[0] = null;
        Cards[1] = null;

    }
    public void addCard( Card card_name ){
        /**
         * adds card to the card deck
         *
         * @card_name card object to card array
         *
         */
        //adds a card
        if (Cards[0]==null) {
            Cards[0]=card_name;

        }
        else{
            Cards[1]=card_name;
        }

    }

    public void newHand(){
        /**
         *clears card
         *
         */
        Cards[0] = null;
        Cards[1] = null;
    }

    /**
     * What kind of hand is this?
     *
     * @return one of the {@link HandType} designations
     * @rit.pre addCard has been called twice
     */
    HandType getType() {
        if (this.Cards[0].getRank().getValue()==this.Cards[1].getRank().getValue()) {
            return HandType.TWO_OF_KIND;
        }
        else if (this.Cards[0].getSuit()==this.Cards[1].getSuit()) {
            return HandType.FLUSH;
        }
        else {
            return HandType.REGULAR;
        }
    }

    /**
     * Show this hand's contents.
     *
     * @return a string containing all the cards in the hand
     */
    public String toString() {
        return Cards[0].shortName() + " " + Cards[1].shortName() + " - - a " + this.getType(); // TODO
    }

    /**
     * Does this hand beat another hand?<br>
     * Rules
     * <ul>
     *     <li>
     *         Pair beats flush, which beats two arbitrary cards.
     *     </li>
     *     <li>
     *         Otherwise compare higher card ranks.
     *     </li>
     *     <li>
     *         If higher card ranks are the same, compare lower card ranks.
     *     </li>
     *     <li>
     *         If both card ranks are the same, return 0 -- hands are identical.
     *     </li>
     * </ul>
     */
    //@Override
    public int compareTo( PokerHand other ) {
        return (Cards[0].getRank().getValue()+Cards[1].getRank().getValue()) - (other.Cards[0].getRank().getValue()+other.Cards[1].getRank().getValue()) ; // TODO
    }



    public void printHand(){
        /**
         *prints out hand of card
         *
         */
        for (int i = size-1; i >= 0; i--){
            System.out.print(Cards[i].toString());
        }
    }

    public Card getCard(int index){
        /**
         *returns a card
         *
         */
        System.out.println(this.Cards[index]);
        return this.Cards[index];
    }




}


