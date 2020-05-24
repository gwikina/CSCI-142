package pokerGame;

import playingCards.Deck;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A 2-card poker game played between a human and a computer player
 *
 * @author RIT CS
 */

public class Poker {

    /**
     * a constant for the maximum number of players that can play
     */
    public static final int MAX_PLAYERS = 6;

    /**
     * Main method -- plays multiple hands of poker, rebuilding and
     * shuffling the deck before each one.
     * Each time around the user is asked if they want to play again.
     * This method also keeps track of the number of games
     * played/won by the user and prints the results at the end.
     *
     * @param args command line arguments
     */
    public static void main( String[] args ) {
        try ( Scanner in = new Scanner( System.in ) ) {
            // The above line is equivalent to the Python "with" statement.

            int whoGoesFirst = 0;

            ArrayList< Player > playerList = setUpGame( in );

            Player whoWon;
            int numGames = 0;
            int numTies = 0;
            int playerCount = playerList.size();

            Random randNumGen =
                    args.length == 1
                            ? new Random( Long.parseLong( args[ 0 ] ) )
                            : new Random();

            Deck theDeck;

            boolean more;
            do {
                numGames = numGames + 1;
                theDeck = new Deck( randNumGen );
                // To get ties, add more card sets!
                // (0.025% chance of tie with 2 decks, 2 players)

                System.out.println();
                System.out.println( "#####################################" );
                System.out.println( "###       NEW, SHUFFLED DECK      ###" );
                System.out.println( "#####################################" );

                whoWon = playHand( playerList, theDeck, whoGoesFirst );

                System.out.println( "\n-------------  RESULTS --------------" );

                if ( whoWon == null ) {
                    System.out.println(
                            "Game " + numGames +
                            " was a tie among several players."
                    );
                    numTies = numTies + 1;
                }
                else {
                    System.out.println( "\n" + whoWon + " won.\n" );
                    whoWon.incrWins();
                }

                char continueChar;
                do {
                    System.out.print( "Another round (y/n)? " );
                    continueChar = in.nextLine().toLowerCase().charAt( 0 );
                } while ( continueChar != 'y' && continueChar != 'n' );
                more = continueChar == 'y';

                if ( more ) {
                    // Players clear out their cards.
                    for ( Player p : playerList ) {
                        p.newHand();
                    }
                    // Advance who will go first in the next hand.
                    whoGoesFirst = ( whoGoesFirst + 1 ) % playerCount;
                }
            } while ( more );

            System.out.println( "========== Results ==========" );
            System.out.println( "Games Played:\t" + numGames );
            for ( Player p : playerList ) {
                System.out.println( p + " won " + p.getWins() + " games." );
            }
            System.out.println( "Ties:\t" + numTies );
        }
    }

    /**
     * This method is called but once by {@link Poker#main(String[])}.
     * It gets directions from the user about the number of players
     * and how many are &quot;Computers&quot;.
     * The collection of players is set up.
     * @return the list of players created
     */
    private static ArrayList< Player > setUpGame( Scanner in ) {
        ArrayList< Player > playerList = new ArrayList<>();
        int numPlayers;
        boolean err;
        do {
            err = false;
            System.out.print( "How many players for this poker game: " );
            numPlayers = in.nextInt();
            in.nextLine();

            if ( numPlayers < 2 || numPlayers > MAX_PLAYERS ) {
                System.out.print( "number of players must be >1 and <" +
                                  (MAX_PLAYERS+1) + "\n Try again: " );
                err = true;
            }
        } while ( err );

        char playerType;
        for ( int i = 0; i < numPlayers; i++ ) {
            do {
                err = false;
                System.out.print( "Is player " + i +
                                  " a human(h) or a computer(c)? " );
                playerType = in.nextLine().toLowerCase().charAt( 0 );

                if ( playerType != 'c' && playerType != 'h' ) {
                    System.out.print( "Legal player types are human/computer " +
                                      "(h/c).\n Try again. " );
                    err = true;
                }
            } while ( err );
            if ( playerType == 'h' ) {
                playerList.add( new Human( in,i) );
            }
            else {
                playerList.add( new Computer(i) );
            }
        }
        return playerList;
    }

    /**
     * Play a single hand of poker.
     *
     * @param allPlayers the collection of players (with empty hands)
     * @param d the deck (already shuffled)
     * @param firstPlayer the first player in the round
     * @return An int telling if the user lost/tied/won (neg/0/pos)
     */
    public static Player playHand( ArrayList< Player > allPlayers, Deck d,
                                   int firstPlayer ) {
        ArrayList< Player > standList = new ArrayList<>();
        Player currentP;
        int numPlayers = allPlayers.size();

        System.out.println( "== Dealing Cards\n" );
        for ( int j = 0; j < 2; j++ ) {
            for ( Player p : allPlayers ) {
                p.addCard( d.dealCard() );
            }
        }

        for ( int i = 0; i < numPlayers; ++i ) {
            // First player to go changes on each round.
            currentP = allPlayers.get( ( firstPlayer + i ) % numPlayers );
            if ( i != numPlayers - 1 || standList.size() > 0 ) {
                System.out.println( "====== " + currentP + " cards  ===" );
                currentP.printHand();

                if ( currentP.stand() ) {
                    System.out.println( currentP + " stands." );
                    standList.add( currentP );
                }
                else {
                    System.out.println( currentP + " folds." );
                }
                System.out.println();
            }
            else  {
                System.out.println( currentP + " stands alone." );
                standList.add( currentP );
            }
        }

        System.out.println( standList.size() + " players left standing." );
        Player winnerSoFar = standList.get( 0 );
        boolean tie = false;
        for ( int i = 1; i < standList.size(); ++i ) {
            currentP = standList.get( i );
            int comparisonNum = winnerSoFar.compareTo( currentP );
            if ( comparisonNum == 0 ) {
                tie = true; // A tie with the winner so far
            }
            else if ( comparisonNum < 0 ) {
                tie = false;
                winnerSoFar = currentP;
            }
        }
        if ( tie ) {
            winnerSoFar = null;
        }
        return winnerSoFar;
    }

}
