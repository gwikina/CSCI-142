package client.controller;

import client.model.ConcentrationModel;
import client.model.Observer;
import common.ConcentrationException;
import common.ConcentrationProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static common.ConcentrationProtocol.*;

/**
 * The networked controller that is created by the view, communicates with
 * the server, and notifies the model of the details of the game play.
 */
public class ConcentrationController implements AutoCloseable {
    /** the client socket */
    private Socket socket;
    /** network out */
    private PrintWriter networkOut;
    /** network in */
    private BufferedReader networkIn;
    /** the server.model */
    private ConcentrationModel model;

    /**
     * Create the client's connection to the server.  The client will login,
     * and if successful will get the board and then run a listener thread
     * for incoming tile changes.
     *
     * @param host hostname
     * @param port post number
     * @param model the server.model
     * @throws ConcentrationException if there is any error with the connection
     */
    public ConcentrationController(String host, int port, ConcentrationModel model)
            throws ConcentrationException {
        try {
            // get everything connected
            this.socket = new Socket(host, port);
            this.networkOut = new PrintWriter(socket.getOutputStream(), true);
            this.networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.model = model;

            // get the BOARD message from server and set in server.model
            this.model.createBoard(getBoardDIM());

            // start the network listener
            startListener();
        } catch(IOException ioe) {
            throw new ConcentrationException(ioe);
        }
    }

    /**
     * Get the square size of the board from the server.  This is the first message
     * the server communicates to the client.
     *
     * @return the board dimensions
     *
     * @throws IOException a network I/O problem occured
     * @throws ConcentrationException a bad message or board dimension was received
     */
    private int getBoardDIM() throws IOException, ConcentrationException {
        String msg = this.networkIn.readLine();
        String[] cmd = msg.split("\\s+", 2);
        if (cmd.length != 2 || !cmd[0].equals(ConcentrationProtocol.BOARD_DIM)) {
            throw new ConcentrationException("Expected BOARD_DIM message, got: " + msg);
        }
        try {
            return Integer.parseInt(cmd[1]);
        } catch (NumberFormatException nfe) {
            throw new ConcentrationException("Bad board dimension: " + cmd[1]);
        }
    }

    /**
     * Called from the view when it is ready to start receiving messages from
     * the server.
     */
    public void startListener() {
        new Thread(this::run).start();
    }

    /**
     * The thread that listens for incoming messages from the server.
     */
    private void run() {
        try {
            // go until the game is either over or an error occurred
            while (this.model.getStatus() == ConcentrationModel.Status.OK) {
                // wait for a message from the server to come in
                String msg = this.networkIn.readLine();
                String[] cmd = msg.split("\\s+");
                switch (cmd[0]) {
                    case CARD:
                        // handle a revealed card message from the server
                        if (cmd.length != 4) {
                            throw new ConcentrationException("Bad CARD msg from server: " + msg);
                        }
                        int row = Integer.parseInt(cmd[1]);
                        int col = Integer.parseInt(cmd[2]);
                        String card = cmd[3];
                        this.model.setCard(row, col, card);
                        break;

                    case MATCH:
                    case MISMATCH:
                        // handle a match/mismatch message from the server
                        if (cmd.length != 5) {
                            throw new ConcentrationException("Bad " + cmd[0] + " msg from server: " + msg);
                        }
                        int row1 = Integer.parseInt(cmd[1]);
                        int col1 = Integer.parseInt(cmd[2]);
                        int row2 = Integer.parseInt(cmd[3]);
                        int col2 = Integer.parseInt(cmd[4]);
                        if (cmd[0].equals(MATCH)) {
                            this.model.setMatch(row1, col1, row2, col2);
                        } else {
                            this.model.setMismatch(row1, col1, row2, col2);
                        }
                        break;
                    case GAME_OVER:
                        // game over man, GAME OVER!
                        this.model.setStatus(ConcentrationModel.Status.GAME_OVER);
                        break;
                    case ERROR:
                        throw new ConcentrationException("Received ERROR from server");
                    default:
                        throw new ConcentrationException("Unknown message from server: " + msg);
                }
            }
        } catch (ConcentrationException | IOException | NumberFormatException e) {
            // when something bad happens we need to tell the model the status
            // is an error now and close our connection to the server
            this.model.setStatus(ConcentrationModel.Status.ERROR);
            System.err.println(e.getMessage());
            close();
        }
    }

    /**
     * Request the server reveal a hidden card.  This is called by the view.
     *
     * @param row the row
     * @param col the column
     */
    public void revealCard(int row, int col) {
        String msg = String.format(REVEAL_MSG, row, col);
        this.networkOut.println(msg);
    }

    @Override
    public void close() {
        try {
            this.networkOut.close();
            this.networkIn.close();
            this.socket.close();
        } catch (IOException ioe) {
            // squash
        }
    }

}
