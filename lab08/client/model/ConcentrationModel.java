package client.model;

import common.ConcentrationException;

import java.util.LinkedList;
import java.util.List;

/**
 * The model represents the current state of the game board.  It receives
 * changes from the controller, and notifies the observer/s when it is modified.
 *
 * @author RIT CS
 */
public class ConcentrationModel {
    public static final int BOARD_SIZE = 4;

    /** The three statuses the game can be in */
    public enum Status {
        OK,             /* everything is okay */
        GAME_OVER,      /* the game successfully ended */
        ERROR           /* an error occurred during game play */
    }

    /** Details of a card being updated */
    public class CardUpdate {
        /** the row */
        private final int row;
        /** the columm */
        private final int col;
        /** the revealed card's value */
        private final String val;
        /** is the card revealed or hidden? */
        private final boolean reveal;

        /**
         * Create a new update card.
         *
         * @param row the row
         * @param col the column
         * @param val the value
         * @param reveal is it revealed or not
         */
        public CardUpdate(int row, int col, String val, boolean reveal) {
            this.row = row;
            this.col = col;
            this.val = val;
            this.reveal = reveal;
        }

        /**
         * Get the row.
         *
         * @return the row
         */
        public int getRow() { return this.row; }

        /**
         * Get the column.
         *
         * @return the column
         */
        public int getCol() { return this.col; }

        /**
         * Get the value.
         *
         * @return the value
         */
        public String getVal() { return this.val; }

        /**
         * Is the card revealed?
         *
         * @return is the card revealed (if not it is hidden)
         */
        public boolean isRevealed() { return this.reveal; }
    }

    /** the hidden card value */
    public final static String HIDDEN = ".";

    /** the game board */
    private String[][] board;
    /** the game status */
    private Status status;
    /** the square dimensions of the board */
    private int DIM;
    /** how many matches have been made */
    private int numMatches;
    /** how many moves (card reveals) have been made */
    private int numMoves;
    /** the observers who are registered with this model */
    private List<Observer<ConcentrationModel, CardUpdate>> observers;

    /**
     * Construct the model.  It is important to note at this point we do not
     * know how big the board is because the controller has not been fully set
     * up to receive that initial message from the server.
     */
    public ConcentrationModel() {
        this.board = null;
        this.status = Status.OK;
        this.DIM = 0;
        this.numMatches = 0;
        this.numMoves = 0;
        this.observers = new LinkedList<>();
    }

    /**
     * Add a new observer.
     *
     * @param observer the new observer
     */
    public void addObserver(Observer<ConcentrationModel, CardUpdate > observer) {
        this.observers.add(observer);
    }

    /**
     * Notify observers the model has changed.
     * 
     * @param card information about the card being updated
     */
    private void notifyObservers(CardUpdate card){
        for (Observer<ConcentrationModel, CardUpdate> observer: observers) {
            // we pass the game status as client data
            observer.update(this, card);
        }
    }

    /**
     * Get the game status.
     *
     * @return status current game status
     */
    public Status getStatus() { return this.status; }

    /**
     * Get the board square dimension.
     *
     * @return  dimension
     */
    public int getDIM() { return this.DIM; }

    /**
     * Get the card at a coordinate.
     *
     * @param row the row
     * @param col the column
     * @return the card value
     * @rit.pre the coordinates are valid
     */
    public String getCard(int row, int col) {
        return this.board[row][col];
    }

    /**
     * Change the game status.
     *
     * @param status new status
     */
    public void setStatus(Status status) {
        this.status = status;
        notifyObservers(null);
    }

    /**
     * Create the board once the square dimension is known from the controller.
     *
     * @param DIM the board's square dimension
     */
    public void createBoard(int DIM) {
        this.DIM = DIM;

        // create the board and set all the "cards" to be hidden
        this.board = new String[DIM][DIM];
        for (int row=0; row<this.DIM; ++row) {
            for (int col=0; col<this.DIM; ++col) {
                this.board[row][col] = HIDDEN;
            }
        }
        // let the view know it can start displaying the board
        notifyObservers(null);
    }

    /**
     * Is the (row, col) coordinate in the range of the board?
     *
     * @param row the row
     * @param col the column
     *
     * @return whether the coordinate is in a valid range or not
     */
    public boolean isCoordValid(int row, int col) {
        return (row >= 0) && (row < this.DIM) && (col >= 0) && (col < this.DIM);
    }

    /**
     * Is the card at a coordinate hidden or not?
     *
     * @param row the row
     * @param col the column
     *
     * @return whether the card is hidden or not
     * @rit.pre the coordinates are valid
     */
    public boolean isHidden(int row, int col) {
        return this.board[row][col].equals(HIDDEN);
    }

    /**
     * The total number of correct matches that have been made.
     *
     * @return number of matches
     */
    public int getNumMatches() {
        return this.numMatches;
    }

    /**
     * The total number of user moves (card reveals) that have been made.
     *
     * @return number of moves
     */
    public int getNumMoves() {
        return this.numMoves;
    }

    /**
     * Set the card in the board to a value that is specified by the controller.
     *
     * @param row the row
     * @param col the column
     * @param val the value
     *
     * @throws ConcentrationException if the coordinate is invalid
     */
    public void setCard(int row, int col, String val) throws ConcentrationException {
        if (!isCoordValid(row, col)) {
            throw new ConcentrationException("Invalid coordinate: (" + row + ", " + col + ")");
        } else {
            this.board[row][col] = val;
            ++this.numMoves;
            notifyObservers(new CardUpdate(row, col, val, true));
        }
    }

    /**
     * Set a match for the last two cards revealed.  This is specified by the controller.
     *
     * @param row1 first card row
     * @param col1 first card column
     * @param row2 second card row
     * @param col2 second card column
     *
     * @throws ConcentrationException if the coordinate is invalid
     */
    public void setMatch(int row1, int col1, int row2, int col2) throws ConcentrationException {
        if (!isCoordValid(row1, col2)) {
            throw new ConcentrationException("Invalid coordinate: (" + row1 + ", " + col1 + ")");
        } else if (!isCoordValid(row1, col2)) {
            throw new ConcentrationException("Invalid coordinate: (" + row2 + ", " + col2 + ")");
        } else {
            // cards are already shown
            ++this.numMatches;
            notifyObservers(null);
        }
    }

    /**
     * Set a mismatch for the last two cards revealed.  This is specified by the controller.
     *
     * @param row1 first card row
     * @param col1 first card column
     * @param row2 second card row
     * @param col2 second card column
     *
     * @throws ConcentrationException if the coordinate is invalid
     */
    public void setMismatch(int row1, int col1, int row2, int col2) throws ConcentrationException {
        if (!isCoordValid(row1, col2)) {
            throw new ConcentrationException("Invalid coordinate: (" + row1 + ", " + col1 + ")");
        } else if (!isCoordValid(row1, col2)) {
            throw new ConcentrationException("Invalid coordinate: (" + row2 + ", " + col2 + ")");
        } else {
            // cards must be hidden
            this.board[row1][col1] = HIDDEN;
            notifyObservers(new CardUpdate(row1, col1, HIDDEN, false));
            this.board[row2][col2] = HIDDEN;
            notifyObservers(new CardUpdate(row2, col2, HIDDEN,false));
        }
    }

    /**
     * Return a string representation of the board, e.g.:
     *
     *   01
     * 0|.A
     * 1|..
     * Moves: 1, Matches: 0
     *
     * @return the board string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("  ");
        // build the column numbers
        for (int col=0; col<this.DIM; ++col) {
            str.append(col);
        }
        str.append("\n");

        // build the rows with number and values
        for (int row=0; row<this.DIM; ++row) {
            str.append(row).append("|");
            for (int col=0; col<this.DIM; ++col) {
                str.append(this.board[row][col]);
            }
            str.append("\n");
        }

        // number of moves and matches for posterity
        str.append("Moves: ").append(this.numMoves).append(", Matches: ").append(this.numMatches);
        return str.toString();
    }
}
