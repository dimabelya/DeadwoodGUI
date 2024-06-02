package hellofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SceneCardManager {
    
    /*
     * Set up all scene cards for all locations
     * pass in the SceneDeck?
     */
    public void initSceneCards() {

    }



    /*
     * Place upside down card image
     */
    public void placeCardBack(Pane location) {
        Image card = new Image("/images/CardBack.jpg");
        ImageView cardView = new ImageView(card);
        
        cardView.fitWidthProperty().bind(location.widthProperty());
        cardView.fitHeightProperty().bind(location.heightProperty());

        location.getChildren().add(cardView);
    }


    /*
     * Set up scene card and their roles
     * need to know how many roles to create
     */
    public void createCardRoles(Pane location, int num) {
        double width = location.getWidth();
        double height = location.getHeight();

        double roleWidth = width / (num + 1); // 1 for padding
        double roleHeight = height / 2;

        // Calculate the starting X position for the first role pane
        double startX = (width - (roleWidth * num)) / 2;

        // Create and position role panes
        for (int i = 0; i < num; i++) {
            Pane rolePane = new Pane();
            rolePane.setPrefSize(roleWidth, roleHeight);
            rolePane.setLayoutX(startX + i * roleWidth);
            rolePane.setLayoutY((height - roleHeight) / 2); // Center vertically
            location.getChildren().add(rolePane);
        }
    }

}
