package hellofx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class DiceManager {
    
    /*
     * creates a path for die images 
     */
    public static String getDiceImagePath(int playerIndex, int rank) {             
        // Validate rank
        if (rank < 1 || rank > 6) {
            throw new IllegalArgumentException("Invalid rank");
        }
        String allColors = "bcgoprvwy";
        char color = allColors.charAt(playerIndex);
        String filePath = "/images/dice/" + color + rank + ".png";
        
        System.out.println("PATH=" + filePath);
        return filePath;
    }
    

    /*
     * return image arraylist that contains num player images
     */
    public ArrayList<ImageView> getDiceImageView(int num, int rank) {
        ArrayList<ImageView> playerImageView = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            String path = getDiceImagePath(i, rank);// get unique player colors 
            Image temp = new Image(getClass().getResourceAsStream(path));
            if (temp.isError()) {
                System.err.println("Error loading image: " + path);
            }
            ImageView imageView = new ImageView(temp);

            playerImageView.add(imageView);
        }
        return playerImageView;
    }


    /*
     * Update player dice image rank
     */
    public void updateDiceRank(ArrayList<ImageView> playerImageViews, int playerIndex, int rank) {
        if (playerIndex < 0 && playerIndex >= playerImageViews.size()) {
            throw new IllegalArgumentException("Invalid player index");
        }
        String newDicePath = getDiceImagePath(playerIndex, rank);

        ImageView imageView = playerImageViews.get(playerIndex);
        Image newImage = new Image(getClass().getResourceAsStream(newDicePath));
        imageView.setImage(newImage);
    }

}
