package hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.ArrayList;

// Scene Builder removes this from hellofx.fxml
// fx:controller="hellofx.Controller"

public class Controller {

    @FXML private ImageView img;  // Game Board
    @FXML private Text text1;     // Prompt
    @FXML private Text text2;     // Game State

    @FXML private ChoiceBox<Integer> numChoiceBox;
    private int numOfPlayers;

    private ArrayList<ImageView> playerImageViews = new ArrayList<>();
    // private ArrayList<Pane> cardPanes = new ArrayList<>();

    @FXML private Button startButton;
    @FXML private Button moveButton;
    @FXML private Button takeButton;
    @FXML private Button rehearseButton;
    @FXML private Button actButton;
    @FXML private Button endButton;

    // Player locations 
    @FXML private Pane trailerPane;
    @FXML private Pane mainStreetPane;
    @FXML private Pane saloonPane;
    @FXML private Pane bankPane;
    @FXML private Pane churchPane;
    @FXML private Pane hotelPane;
    @FXML private Pane secretHideoutPane;
    @FXML private Pane ranchPane;
    @FXML private Pane generalStorePane;
    @FXML private Pane jailPane;
    @FXML private Pane trainStationPane;
    @FXML private Pane castingOfficePane;

    // Scene card locations and shot numbers
    @FXML private Pane mainStreetCardPane;
        @FXML private ImageView mainStreetShot1;
        @FXML private ImageView mainStreetShot2;
        @FXML private ImageView mainStreetShot3;
        @FXML private Pane mainStreetRole1;
        @FXML private Pane mainStreetRole2;
        @FXML private Pane mainStreetRole3;
        @FXML private Pane mainStreetRole4;
    @FXML private Pane saloonCardPane;
        @FXML private ImageView saloonCardShot1;
        @FXML private ImageView saloonCardShot2;
        @FXML private Pane saloonRole1;
        @FXML private Pane saloonRole2;
    @FXML private Pane bankCardPane;
        @FXML private ImageView bankShot1;
        @FXML private Pane bankRole1;
        @FXML private Pane bankRole2;
    @FXML private Pane churchCardPane;
        @FXML private ImageView churchShot1;
        @FXML private ImageView churchShot2;
        @FXML private Pane churchRole1;
        @FXML private Pane churchRole2;
    @FXML private Pane hotelCardPane;
        @FXML private ImageView hotelShot1;
        @FXML private ImageView hotelShot2;
        @FXML private ImageView hotelShot3;
        @FXML private Pane hotelRole1;
        @FXML private Pane hotelRole2;
        @FXML private Pane hotelRole3;
        @FXML private Pane hotelRole4;
    @FXML private Pane secretHideoutCardPane;
        @FXML private ImageView secretHideoutShot1;
        @FXML private ImageView secretHideoutShot2;
        @FXML private ImageView secretHideoutShot3;
        @FXML private Pane secretHideoutRole1;
        @FXML private Pane secretHideoutRole2;
        @FXML private Pane secretHideoutRole3;
        @FXML private Pane secretHideoutRole4;
    @FXML private Pane ranchCardPane;
        @FXML private ImageView ranchShot1;
        @FXML private ImageView ranchShot2;
        @FXML private Pane ranchRole1;
        @FXML private Pane ranchRole2;
        @FXML private Pane ranchRole3;
    @FXML private Pane generalStoreCardPane;
        @FXML private ImageView generalStore1;
        @FXML private ImageView generalStore2;
        @FXML private Pane generalStoreRole1;
        @FXML private Pane generalStoreRole2;
    @FXML private Pane jailCardPane;
        @FXML private ImageView jailShot1;
        @FXML private Pane jailRole1;
        @FXML private Pane jailRole2;
    @FXML private Pane trainStationCardPane;
        @FXML private ImageView trainStationShot1;
        @FXML private ImageView trainStationShot2;
        @FXML private ImageView trainStationShot3;
        @FXML private Pane trainStationRole1;
        @FXML private Pane trainStationRole2;
        @FXML private Pane trainStationRole3;
        @FXML private Pane trainStationRole4;


    public DiceManager dice = new DiceManager();
    public SceneCardManager card = new SceneCardManager();


    /*
     * ask user for number of players
     * set up the board
     */
    public void initialize() {     
        ObservableList<Integer> playerOptions = FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8);
        numChoiceBox.setItems(playerOptions);

        numOfPlayers = 2;  // a default value and update numOfPlayers
        numChoiceBox.setValue(numOfPlayers);
        
        numChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            numOfPlayers = newValue;
        });

        card.placeCardBack(bankCardPane);
        card.placeCardBack(jailCardPane);
        card.placeCardBack(hotelCardPane);
        card.placeCardBack(ranchCardPane);
        card.placeCardBack(churchCardPane);
        card.placeCardBack(saloonCardPane);
        card.placeCardBack(mainStreetCardPane);
        card.placeCardBack(generalStoreCardPane);
        card.placeCardBack(trainStationCardPane);
        card.placeCardBack(secretHideoutCardPane);
       
    }


    /*
     * Start Game Button handler 
     * PROBLEM: sets rank to 1 no matter the size of players
     */
    @FXML public void handleStart() {
        text1.setText(numOfPlayers + " players selected");
        numChoiceBox.setVisible(false);
        startButton.setVisible(false);
        showButtons();  // display hidden player option buttons
        
        // generate all the images for the players
        playerImageViews = dice.getDiceImageView(numOfPlayers, 1);  // fix the rank 

        //move all players into start position
        movePlayerToPane(-1, trailerPane);
        movePlayerToPane(0, jailRole2);
        movePlayerToPane(1, jailRole1);
        movePlayerToPane(2, saloonRole1);
        dice.updateDiceRank(playerImageViews, 0, 6);
        dice.updateDiceRank(playerImageViews, 1, 6);
        dice.updateDiceRank(playerImageViews, 2, 3);

        
    }

    
    /* 
     * Add all players to a pane
     * index negative -> for all players, index positive -> specific player
     * PROBLEM: assumes all Panes are empty!!
     */
    private void movePlayerToPane(int index, Pane destPane) {
        double paneWidth = destPane.getPrefWidth();
        double imageWidth = 18;
        double imageHeight = 18;
        double spacing = 2;
    
        // Calculate the number of images that can fit horizontally
        int maxCols = (int) ((paneWidth + spacing) / (imageWidth + spacing));
    
        if (index < 0) {  // case for all players
            for (int i = 0; i < playerImageViews.size(); i++) {
                ImageView imageView = playerImageViews.get(i);
                imageView.setFitWidth(imageWidth);
                imageView.setFitHeight(imageHeight);
                
                int row = i / maxCols;  // Calculate row and column for each image
                int col = i % maxCols;
                double x = col * (imageWidth + spacing);
                double y = row * (imageHeight + spacing);
                imageView.setLayoutX(x);  // Set the position of the ImageView
                imageView.setLayoutY(y);
        
                destPane.getChildren().add(imageView);
            }
        } else {  // case for a single player
            ImageView playerIM = playerImageViews.get(index);

            // Calculate row and column for the specific player based on the current pane size
            int row = destPane.getChildren().size() / maxCols;
            int col = destPane.getChildren().size() % maxCols;
            double x = col * (imageWidth + spacing);
            double y = row * (imageHeight + spacing);
            playerIM.setLayoutX(x);
            playerIM.setLayoutY(y);

            destPane.getChildren().add(playerIM);
        }
    }
    

    /*
     * Display player move options
     */
    public void showButtons() {
        moveButton.setVisible(true);
        takeButton.setVisible(true);
        rehearseButton.setVisible(true);
        actButton.setVisible(true);
        endButton.setVisible(true);
    }


    /*
     * handle moveButton
     */
    @FXML public void handleMove() {
        // do we even need this? drag and drop dice instead?
        //movePlayerToPane(index, location);
    }


    /*
     * handle takeButton (take role)
     */
    @FXML public void handleTake() {
        // move plater to a role
    }


    /*
     * handle rehearseButton
     */
    @FXML public void handleRehearse() {
        
    }


    /*
     * handle actButton
     */
    @FXML public void handleAct() {
        // Remove shot marker
    }


    /*
     * handle endButton (end player turn)
     */
    @FXML public void handleEnd() {
        
    }



    
}