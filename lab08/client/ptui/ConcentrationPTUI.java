package client.ptui;

import client.controller.ConcentrationController;
import client.model.ConcentrationModel;
import client.model.Observer;
import common.ConcentrationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * The plain text UI (client) for the concentration game.  This is written in MVC
 * where the UI is the view.
 *
 * $ java ConcentrationPTUI host port
 *
 * @author RIT CS
 */
public class ConcentrationPTUI implements Observer<ConcentrationModel, ConcentrationModel.CardUpdate>, AutoCloseable {
    /** how long to wait for cards to stay revealed and be hidden again. */
    private final static int CARD_HIDE_TIME_MS = 500;

    /** a way for the client to quit the program prematurely */
    private final static String QUIT = "q";
    /** the model */
    private ConcentrationModel model;
    /** the controller */
    private ConcentrationController controller;
    /** user output messages use this */
    private PrintWriter userOut;
    /** user input messages are read with this */
    private BufferedReader userIn;

    /**
     * Create the connections with the controller and model.
     *
     * @param args command line arguments (host and port)
     * @throws ConcentrationException if a problem occurs
     */
    public ConcentrationPTUI(String[] args) throws ConcentrationException {
        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);

            // create the model and add ourselves as an observer
            this.model = new ConcentrationModel();
            this.model.addObserver(this);

            // initiate the controller
            this.controller = new ConcentrationController(host, port, this.model);
        } catch (ConcentrationException |
                ArrayIndexOutOfBoundsException |
                NumberFormatException e) {
            throw new ConcentrationException(e);
        }
    }

    /** The "main loop" that handles user input and output while the game is active. */
    public void go() {
        try {
            // create our input and output streams
            this.userIn = new BufferedReader(new InputStreamReader(System.in));
            this.userOut = new PrintWriter(System.out, true);
            while (this.model.getStatus() == ConcentrationModel.Status.OK) {
                // get the coordinates from the user
                String cmd = this.userIn.readLine();
                if (cmd == null) {
                    break;
                }
                if (cmd.equals(QUIT)) {
                    throw new ConcentrationException("Client exiting");
                }

                // all this stuff is error handling.  we don't want the client to
                // blow up or send something erroneous to the server.

                String[] coords = cmd.split("\\s+");
                if (coords.length != 2) {
                    this.userOut.println("Enter two digits: row col");
                    continue;
                }

                int row = 0;
                int col = 0;
                try {
                    row = Integer.parseInt(coords[0]);
                    col = Integer.parseInt(coords[1]);
                } catch (NumberFormatException nfe) {
                    this.userOut.println("Enter two digits: row col");
                }

                if (!this.model.isCoordValid(row, col)) {
                    this.userOut.println("Coordinate is out of bounds");
                    continue;
                } else if (!this.model.isHidden(row, col)) {
                    this.userOut.println("Coordinate is already revealed");
                    continue;
                }

                // if we got here the coordinates are okay and we can tell
                // the controller we want to have this card revealed.
                this.controller.revealCard(row, col);
            }
        } catch (ConcentrationException | IOException e) {
            System.err.println(e.getMessage());
            this.controller.close();
            this.model.setStatus(ConcentrationModel.Status.ERROR);
        } finally {
            close();
        }
    }

    /** Called by the model when it changes state. */
    @Override
    public void update(ConcentrationModel model, ConcentrationModel.CardUpdate card) {
        if (model.getStatus() == ConcentrationModel.Status.OK) {
            if (card != null && !card.isRevealed()) {
                try {
                    Thread.sleep(CARD_HIDE_TIME_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // if everything is okay we just print out the current board
            System.out.println(this.model);
        } else {
            if (model.getStatus() == ConcentrationModel.Status.GAME_OVER) {
                System.out.println("You won!");
            } else {
                System.out.println("An error occurred!");
            }
            close();
        }
    }

    @Override
    public void close() {
        try {
            System.in.close();
            this.userIn.close();
            this.userOut.close();
        } catch (IOException e) {
            // squash
        }
    }

    /**
     * The main method initializes the client and runs its side of the game play.
     *
     * @param args command line arguments (host and port)
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ConcentrationClient host port");
        } else {
            try {
                ConcentrationPTUI ptui = new ConcentrationPTUI(args);
                ptui.go();
            } catch (ConcentrationException ce) {
                System.out.println(ce);
            }
        }
    }
}
