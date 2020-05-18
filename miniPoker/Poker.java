package miniPoker;

import playingCards.Deck;

import java.util.Scanner;

/**
 * A 2-card poker game played between a human and a computer player
 *
 * @author paw: Phil White
 */
public class Poker {
    /**
     * a boolean toggle that tells the order of making the stand/fold
     * decision.  This flips after each hand
     */
    private static boolean playerGoesFirst = true;

    /**
     * Plays a single hand of poker
     *
     * @param person the human player
     * @param comp   the computer player
     * @param deck   the deck
     * @return an int telling if the user lost/tied/won (neg/0/pos)
     */
    public static int playHand( Human person, Computer comp, Deck deck ) {
        boolean personStand = true, dealerStand = true;
        int playerWon;

        System.out.println( "== Dealing Cards\n" );
        //give initial cards
        for ( int j = 0; j < 2; j++ ) {
            person.addCard( deck.dealCard() );
            comp.addCard( deck.dealCard() );
        }

        System.out.println( "==============  Your Cards  ========" );
        person.printHand();

        // ask both players if they want to stand
        if ( playerGoesFirst ) {
            personStand = person.stand();
        }

        if ( personStand ) {
            dealerStand = comp.stand();
            if ( dealerStand ) {
                System.out.println( "Computer Stands" );
            }
            else {
                System.out.println( "Computer Folds" );
            }
        }

        if ( !playerGoesFirst && dealerStand ) {
            personStand = person.stand();
        }

        System.out.println( "==============  House Cards ========" );
        comp.printHand();

        playerGoesFirst = !playerGoesFirst;
        if ( personStand && dealerStand ) {
            //check player win vs. computer
            playerWon = person.compareTo( comp );
        }
        else if ( personStand ) {
            playerWon = 1;    // player won by default
        }
        else {
            playerWon = -1;    // House won by default
        }

        // have everyone throw in their cards
        person.newHand();
        comp.newHand();

        return playerWon;
    }

    /**
     * Main method -- plays multiple hands of poker, after each hand
     * ask the user if they want to play again.  We also keep trak of
     * the number of games played/won by the user and print the results
     * at the end.
     *
     * @param args command line arguments
     */
    public static void main( String args[] ) {
        Scanner in = new Scanner( System.in );
        String again;
        char c;
        int playerWon;
        int numGames = 0;
        int numWon = 0;
        int numTie = 0;

        Deck theDeck = new Deck();
        Computer theComputer = new Computer();
        Human theHuman = new Human( in );

        do {
            numGames = numGames + 1;

            System.out.println();
            System.out.println( "##########################################" );
            System.out.println( "##########       NEW HAND      ###########" );
            System.out.println( "##########################################" );
            System.out.println( "\n== Shuffling" );
            theDeck.shuffle();

            playerWon = playHand( theHuman, theComputer, theDeck );
            if ( playerWon > 0 ) {
                System.out.println( "\n  **** Human Won ****\n" );
                numWon = numWon + 1;
            }
            else if ( playerWon == 0 ) {
                System.out.println( "       Tie Game" );
                numTie = numTie + 1;
            }
            else {
                System.out.println( "\n  ---- House Won ----\n" );
            }

            do {
                System.out.print( "Do you wish to play " +
                                  "another hand (y/n):" );
                again = in.nextLine();
                again = again.toLowerCase();
                c = again.charAt( 0 );
            } while ( c != 'y' && c != 'n' );

        } while ( c == 'y' );

        System.out.println( "========== Results ==========" );
        System.out.println( "Games:\t" + numGames );
        System.out.println( "Wins:\t" + numWon );
        System.out.println( "Ties:\t" + numTie );

        in.close();  // <3 Jim
    }
}
