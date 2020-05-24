package pokerGame;

import java.util.Scanner;

/**
 * A human player for 2-card poker.
 * It depends on user input to decide whether to stand or fold.
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */
public class Human extends Player {

	// TODO Add state as needed.
	private int wins;
	private int number = 0;
	protected PokerHand deckOfCards;
	private Scanner in;
    /**
     * Initialize a human player for 2-card poker
	 * @param in the Scanner object to be used to prompt user for stand/fold
     */
    public Human( Scanner in,int number) {
		super(number);
        this.in = in;
    }

    /**
     * Ask the player if they want to stand.
     * The player is prompted with a suitable message, and then
     * the players response from the Scanner is converted to
     * true (stand) or false (fold).
     *
     * @return true iff the human wants to stand
     */
    public boolean stand() {
		/**
		 * decides whether to stand or fold via a scanner
		 *
		 */

		System.out.print("Do you want to stand (y) or fold (n)?");
		String answer = in.nextLine();
		return answer.charAt(0) == 'y';
		}


	/**
	 * Get a string representing this human player
	 * @return all the information in {@link Player#toString()}, plus "Human"
	 */
	@Override
	public String toString() {
		return "Human Player "+super.toString();  // TODO
	}
}
