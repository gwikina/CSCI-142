package client.gui;

import client.controller.ConcentrationController;
import client.model.ConcentrationModel;
import client.model.Observer;
import common.ConcentrationException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;


/**
 * A JavaFX GUI client for the network concentration game.  It represent the "View"
 * component of the MVC architecture in use here.
 *
 * @author RIT CS
 * @author Gideon Wikina (glw3325)
 */
public class ConcentrationGUI extends Application implements Observer<ConcentrationModel, ConcentrationModel.CardUpdate> {
    //declares instance variables
    private ConcentrationController concentrationController;
    private ConcentrationModel model;
    HashMap<String, Image> pics = new HashMap<String, Image>();
    private Image abra = new Image(getClass().getResourceAsStream("images/abra.png"));
    private Image bulbasaur = new Image(getClass().getResourceAsStream("images/bulbasaur.png"));
    private Image charizard = new Image(getClass().getResourceAsStream("images/charizard.png"));
    private Image diglett = new Image(getClass().getResourceAsStream("images/diglett.png"));
    private Image golbat = new Image(getClass().getResourceAsStream("images/golbat.png"));
    private Image golem = new Image(getClass().getResourceAsStream("images/golem.png"));
    private Image jigglypuff = new Image(getClass().getResourceAsStream("images/jigglypuff.png"));
    private Image magikarp = new Image(getClass().getResourceAsStream("images/magikarp.png"));
    private Image meowth = new Image(getClass().getResourceAsStream("images/meowth.png"));
    private Image mewtwo = new Image(getClass().getResourceAsStream("images/mewtwo.png"));
    private Image natu = new Image(getClass().getResourceAsStream("images/natu.png"));
    private Image pidgey = new Image(getClass().getResourceAsStream("images/pidgey.png"));
    private Image pikachu = new Image(getClass().getResourceAsStream("images/pikachu.png"));
    private Image pokeball = new Image(getClass().getResourceAsStream("images/pokeball.png"));
    private Image poliwag = new Image(getClass().getResourceAsStream("images/poliwag.png"));
    private Image psyduck = new Image(getClass().getResourceAsStream("images/psyduck.png"));
    private Image rattata = new Image(getClass().getResourceAsStream("images/rattata.png"));
    private Image slowpoke = new Image(getClass().getResourceAsStream("images/slowpoke.png"));
    private Image snorlak = new Image(getClass().getResourceAsStream("images/snorlak.png"));
    private Image squirtle = new Image(getClass().getResourceAsStream("images/squirtle.png"));
    Image image;
    GridPane gridPane;
    Label label1;
    Label label2;
    Label label3;

    public ConcentrationGUI(){
        /**
         * Initializes the components of the hashmap
         *
         * @param none description
         *
         * @return none
         */
        this.pics.put(".", pokeball);
        this.pics.put("A", abra);
        this.pics.put("B", bulbasaur);
        this.pics.put("C", charizard);
        this.pics.put("D", diglett);
        this.pics.put("E", golbat);
        this.pics.put("F", golem);
        this.pics.put("G", jigglypuff);
        this.pics.put("H", magikarp);
        this.pics.put("I", meowth);
        this.pics.put("J", mewtwo);
        this.pics.put("K", natu);
        this.pics.put("L", pidgey);
        this.pics.put("M", pikachu);
        this.pics.put("N", poliwag);
        this.pics.put("O", psyduck);
        this.pics.put("P", rattata);
        this.pics.put("Q", slowpoke);
        this.pics.put("R", snorlak);
        this.pics.put("S", squirtle);
    }

    @Override
    public void init() throws Exception {
        /**
         * initializes the scene by creating a model, adding an observer, and declaring a new controller.
         *
         * @param none
         *
         * @return none
         */

        List<String> args = getParameters().getRaw();
        // get host and port from command line
        String host = args.get(0);
        int port = Integer.parseInt(args.get(1));

        // create the model, and add ourselves as an observer
        // TODO
        this.model = new ConcentrationModel();
        this.model.addObserver(this);

        // initiate the controller
        // TODO
        try {
            this.concentrationController = new ConcentrationController(host, port, this.model);
        } catch (ConcentrationException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new ConcentrationException(e);
        }
    }
    @Override
    public void start(Stage stage) {
        /**
         * sets starts the GUI and sets the scene
         *
         * @param stage stage for the gui
         *
         * @return none
         */
        // TODO
        BorderPane borderPane = new BorderPane();
        this.gridPane = makeGridPane();
        FlowPane flowPane = new FlowPane();
        this.label1 = new Label("MOVES: "+this.model.getNumMoves());
        this.label2 = new Label("MATCHES: "+this.model.getNumMatches());
        this.label3 = new Label(""+ this.model.getStatus());
        flowPane.getChildren().add(this.label1);
        flowPane.getChildren().add(this.label2);
        flowPane.getChildren().add(this.label3);
        flowPane.setHgap(100);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(flowPane);
        borderPane.setMaxHeight(500);
        borderPane.setMaxWidth(500);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("Concentration Gui");
        stage.setResizable(false);
        stage.show();
    }
    private class PokemonButton extends Button{
        /**
         * class for new Button
         *
         * @return none
         */
        public PokemonButton(Image image){
            /**
             * Constructs the button as as a collection pictures
             *
             * @param image images used for GUI
             *
             * @return none
             */
            ImageView imageView = new ImageView(image);
            this.setGraphic(imageView);
        }
    }

    private GridPane makeGridPane() {
        /**
         * makes a new GridPane that is the first real "scene"
         *
         * @param none
         *
         * @return updated GridPane
         */
        GridPane gridPane = new GridPane();
        int DIM = this.model.getDIM();
        for (int row=0; row<DIM; ++row) {
            for (int col=0; col<DIM; ++col) {
                // get the next type of pokemon and create a button for it
                this.image= pics.get(".");
                PokemonButton pokemonButton = new PokemonButton(image);
                // reveal the card when pressed
                //int finalRow = row;
                int finalRow = row;
                //int finalCol = col;
                int finalCol = col;
                pokemonButton.setOnAction(event->this.concentrationController.revealCard(finalRow,finalCol));
                // JavaFX uses (x, y) pixel coordinates instead of
                // (row, col), so must invert when adding
                gridPane.add(pokemonButton, col, row);
            }
        }
        return gridPane;
    }


    @Override
    public void update(ConcentrationModel model, ConcentrationModel.CardUpdate card) {
        /**
         * The observed subject calls this method on each observer that has previously registered with it.
         * This version of the design pattern follows the "push server.model" in that typically the observer
         * must then query the subject parameter to find out what has happened. Therefore it is often the case
         * that the observed subject calls this method with an argument value of this, but this convention is by no
         * means a requirement. Similarly, if this is a simple signal with no data attached, or if it can safely be
         * assumed that the observer already has a reference to the subject, the subject parameter may be null. But as
         * always this would have to be agreed to by both sides.
         *
         * @param model the model of the GUI
         *
         * @param card the card to be updated
         *
         * @return none
         */
        // TODO
        Platform.runLater(() -> refresh(card));

    }

    private void refresh(ConcentrationModel.CardUpdate card) {
        /**
         * refreshes the gridPane so we know which card is revealed
         *
         * @param card the card to be updated
         *
         * @return none
         */
        if(card!=null) {
            this.label1.setText("MOVE: " + this.model.getNumMoves());
            this.label3.setText("" + this.model.getStatus());
            String input = card.getVal();
            Image image = this.pics.get(input);
            PokemonButton new_button = new PokemonButton(image);
            this.gridPane.add(new_button, card.getCol(), card.getRow());
            new_button.setOnAction(null);
            if (!card.isRevealed()) {
                new_button.setOnAction(event -> this.concentrationController.revealCard(card.getRow(), card.getCol()));
            }
            if (model.getStatus() == ConcentrationModel.Status.GAME_OVER) {
                this.stop();
            }
            if (model.getStatus() == ConcentrationModel.Status.ERROR) {
                this.stop();
            }
        }
        if(card==null&&this.label2!=null){
            label2.setText("MATCHES: "+this.model.getNumMatches());
        }
    }

    @Override
    public void stop(){
        /**
         * stops Gui for running
         *
         * @param none
         *
         * @return none
         */
        this.concentrationController.close();
    }

    public static void main(String[] args) {
        /**
         * runs the gui
         *
         * @param args the host and port number
         *
         * @return none
         */
        if (args.length != 2) {
            System.out.println("Usage: java ConcentrationGUI host port");
            System.exit(-1);
        } else {
            Application.launch(args);
        }
    }
}