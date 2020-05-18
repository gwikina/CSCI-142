package miniPoker;

public class Card_List {
        // run operations on the card
        private Card [] Cards;
        private int size;
        public int val;

        public Card_List (){
            /**
             * makes a deck of cards
             *
             */
            Cards = new Card[3];
            size = 0;
            val = 0;
        }
        public void addCard( Card card_name ){
            /**
             * adds card to the card deck
             *
             * @card_name card object to card array
             *
             */
            //adds a card
            Cards[size] = card_name;
            size ++;
        }
        public void printHand(){
            /**
             *prints out hand of card
             *
             */
            for (int i = size-1; i >= 0; i--){
                System.out.print(Cards[i].getShortName());
            }
        }
        public int value(){
            /**
             * computations for the value
             *
             */
            int val = 0;

            if( Cards[0].getRank() == Cards[1].getRank() ){
                val = 1000; //pair
            } else if( Cards[0].getSuit() == Cards[1].getSuit() ){
                val = 500; //flush
            }
            if( Cards[0].value() > Cards[1].value() ){
                val =  val + Cards[0].value() * 14 +  Cards[1].value();
            } else {
                val =  val + Cards[1].value() * 14 +  Cards[0].value();
            }
            return val;
        }


    }

