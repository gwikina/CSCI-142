package miniPoker;

import java.util.Scanner;

public class Human {
    // human class
    protected Card_List deckOfCards;
    public Human(Scanner in){
        /**
         * Initializes the deck of cards
         *
         * @in Scanner that takes an input
         *
         */
        deckOfCards = new Card_List();
    }
    public void addCard(Card c){
        /**
         * Adds a card to the deck of cards
         *
         * @c the new card to be added
         *
         */
        deckOfCards.addCard(c);
    }
    public int compareTo(Computer computer){
        /**
         * Compares the hand of the human and computer and returns a winner
         *
         * @computer allows for comparison between computer and humans
         *
         */
        int point;
        point = Integer.compare(deckOfCards.value(), computer.value());
        return point;
    }
    public void newHand(){
        /**
         * Creates a new deck of cards
         *
         */
        deckOfCards = new Card_List();
    }
    public void printHand(){
        /**
         * prints deck of cards
         *
         */
        deckOfCards.printHand();
        System.out.println();
    }
    public boolean stand(){
        /**
         * decides whether to stand or fold via a scanner
         *
         */
        String answer;
        boolean isStand = true;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println("CARD VALUE: " + deckOfCards.value());

            System.out.print("Do you want to stand (y) or fold (n)?");
            answer = in.nextLine();
        }
        while(answer.equalsIgnoreCase("y"));

            isStand = !answer.equalsIgnoreCase("n");

        return isStand;
    }



}
