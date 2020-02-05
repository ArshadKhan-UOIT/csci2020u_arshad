import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.time.DateTimeException;
import java.time.LocalDate;


public class lab04 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //labels
        //username
        Text l1 = new Text("Username:");
        //password
        Text l2 = new Text("Password:");
        //full name
        Text l3 = new Text("Full Name:");
        //email
        Text l4 = new Text("E-Mail:");
        //phone number
        Text l5 = new Text("Phone #:");
        //dob
        Text l6 = new Text("Date of Birth:");

//        //tile pane
//        TilePane tp = new TilePane();

        //date picker
        DatePicker dp = new DatePicker();

        //text field
        //username
        TextField t1 = new TextField();
        //password
//        TextField t2 = new TextField();
        PasswordField passwordField = new PasswordField();
        //full name
        TextField t3 = new TextField();
        //email
        TextField t4 = new TextField();
        //phone number
        TextField t5 = new TextField();
        //dob
//        TextField t6 = new TextField();

        //text field prompt
        t1.setPromptText("Username");
//        t2.setPromptText("Password");
        passwordField.setPromptText("Password");
        t3.setPromptText("Full Name");
        t4.setPromptText("E-Mail");
        t5.setPromptText("Phone #");




        //buttons
        Button b1 = new Button("Register");

        //grid pane
        GridPane gridpane = new GridPane();

        //size for plane
        gridpane.setMinSize(400, 220);

        //setting padding
        gridpane.setPadding(new Insets(10, 10, 10, 10));

        //vertical and horizontal gaps between columns
        gridpane.setVgap(10);
        gridpane.setHgap(10);

        //grid alignment
        gridpane.setAlignment(Pos.TOP_LEFT);

        //arranging everything in grid
        gridpane.add(l1, 0, 0);
        gridpane.add(t1, 1, 0);

        gridpane.add(l2, 0, 1);
        gridpane.add(passwordField, 1, 1);

        gridpane.add(l3, 0, 2);
        gridpane.add(t3, 1, 2);

        gridpane.add(l4, 0, 3);
        gridpane.add(t4, 1, 3);

        gridpane.add(l5, 0, 4);
        gridpane.add(t5, 1, 4);

        gridpane.add(l6, 0, 5);
//        gridpane.add(t6, 1, 5);
        gridpane.add(dp, 1, 5);

        gridpane.add(b1, 1, 6);
        b1.setOnAction(e->
                System.out.println("Username: " + t1.getText() +
                                    "\nPassword: " + passwordField.getText() +
                                    "\nFull Name: " + t3.getText() +
                                    "\nE-Mail: " + t4.getText() +
                                    "\nPhone #: " + t5.getText() +
                                    "\nDate of Birth: " + dp.getValue()));

        //creating an object scene
        Scene scene = new Scene(gridpane);

        //title
        primaryStage.setTitle("Lab 04 Solution");

        //adding scene to the primaryStage
        primaryStage.setScene(scene);

        //displaying contents
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}
