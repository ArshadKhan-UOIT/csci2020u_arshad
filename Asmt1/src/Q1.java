/*
*
* Tashdiq Ahmed 100701752
* Arshad Khan 100695721
*Display a frame that contains three labels.
* Each label displays a card, as shown in the figure below.
* The card image files are named 1.png, 2.png, ..., 54.png
* and stored in the image/card directory. All three cards are
* distinct and selected randomly
*
*
*
*
*
* */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import java.util.*;


public class Q1 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Random randomGenerator = new Random();  //declearing the random function
        int num1 = randomGenerator.nextInt(54) + 1, num2 = randomGenerator.nextInt(54) + 1, num3 = randomGenerator.nextInt(54) + 1; //generating 3 random integers for each card from 1 to 54
        int[] card = new int[3];    //for each card
        HBox hbox = new HBox();    //declearing the hbox
        while(true) {   //while loop for getting the random cards
            card[0] = num1; //initializing the first card using the random variable
            card[1] = num2; //initializing the second card using the random variable
            card[2] = num3; //initializing the third card using the random variable
            if (card[0]==card[1] || card[1]==card[2] || card[0]==card[2]) { //if there are any of the same cards
                num1 = randomGenerator.nextInt(54) + 1; //basically shuffles the deck again
                num2 = randomGenerator.nextInt(54) + 1;
                num3 = randomGenerator.nextInt(54) + 1;
            } else if (card[0]!=card[1] && card[1]!=card[2] && card[0]!=card[2]) break; //only breaks when no random number is the same. This ensures that we get 3 different and random cards
        }
        for (int i=0;i<card.length;i++) {   //this just gets each card image and add it to hbox
            hbox.getChildren().add(new ImageView(new Image("image/card/" + String.valueOf(card[i]) +  ".png")));
        }
        Scene scene = new Scene(hbox, 216, 100);    //hbox is added to scene

        primaryStage.setTitle("Q1 Solution");      //setting the title
        primaryStage.setScene(scene);       //setting the scene with the hbox
        primaryStage.show();    //shows Q1
    }
}
