package miniPoker;
import playingCards.Rank;
public class Computer {
    protected Card_List deckOfcards;
    private static final int val = (Rank.QUEEN.getValue()) * 14 + (Rank.JACK.getValue());

    public Computer (){
        /**
         * initializes a deck of cards for the computer
         *
         */
        deckOfcards =  new Card_List();
    }

    public void addCard(Card card_name){
        /**
         * adds card to the computer deck
         *
         * @card_name the card to be added
         *
         */
        //adds cards
        deckOfcards.addCard(card_name);
    }
    public void newHand(){
        /**
         * adds a new deck of cards, or hands
         *
         */
        //gets new hand
        deckOfcards = new Card_List();
    }
    public void printHand(){
        /**
         * prints hand in optimal fashion
         *
         */
        //prints hand & total
        deckOfcards.printHand();
        System.out.println(" \n Card Value: " + deckOfcards.value());
    }
    public boolean stand(){
        /**
         * computer chooses to stand or quit
         *
         */
        return (deckOfcards.value() >= val );
    }

    public int value(){
        /**
         * returns value of deck of cards
         *
         */
        //returns the value
        return deckOfcards.value();
    }


}
