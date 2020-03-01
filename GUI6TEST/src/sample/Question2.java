import javafx.application.Application;
import javafx.css.converter.StringConverter;
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
import java.util.*;


public class Question2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //i took a layout from my first lab and changed it to be faster

        Text l1 = new Text("Number 1:");

        Text l2 = new Text("Number 2:");

        Text l3 = new Text("Result:");
//
//        Text l4 = new Text("E-Mail:");
//        //phone number
//        Text l5 = new Text("Phone #:");
//        //dob
//        Text l6 = new Text("Date of Birth:");

//        //tile pane
//        TilePane tp = new TilePane();

        //date picker
//        DatePicker dp = new DatePicker();

        //text field
        //username
        TextField t1 = new TextField(); //num1
        //password
        TextField t2 = new TextField(); //num2

        //full name
        TextField t3 = new TextField(); //result





        //buttons
        Button b1 = new Button("Add");
        Button b2 = new Button("Subtract");
        Button b3 = new Button("Multiply");
        Button b4 = new Button("Divide");

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
        t3.setPromptText("Dont Input here");
        //arranging everything in grid
        gridpane.add(l1, 0, 0);
        gridpane.add(t1, 1, 0);

        gridpane.add(l2, 0, 1);
        gridpane.add(t2, 1, 1);

        gridpane.add(l3, 0, 2);
        gridpane.add(t3, 1, 2);

        gridpane.add(b1, 0, 3);
        gridpane.add(b2, 1, 3);
        gridpane.add(b3, 2, 3);
        gridpane.add(b4, 3, 3);

        b1.setOnAction(e-> {
            int n1 = Integer.parseInt(t1.getText());
            int n2 = Integer.parseInt(t2.getText());
            double res = n1+n2;
            String displayRes = String.valueOf(res);
            t3.setText(displayRes);
        });

        b2.setOnAction(e-> {
            int n1 = Integer.parseInt(t1.getText());
            int n2 = Integer.parseInt(t2.getText());
            double res = n1-n2;
            String displayRes = String.valueOf(res);
            t3.setText(displayRes);
        });

        b3.setOnAction(e-> {
            float n1 = Float.parseFloat(t1.getText());
            float n2 = Float.parseFloat(t2.getText());
            float res = n1*n2;
            String displayRes = String.valueOf(res);
            t3.setText(displayRes);
        });

        b4.setOnAction(e-> {
            int n1 = Integer.parseInt(t1.getText());
            int n2 = Integer.parseInt(t2.getText());
            float res = (float) n1/ (float) n2;
//            System.out.println(res);
            String displayRes = String.valueOf(res);
            t3.setText(displayRes);
        });

        Scene scene = new Scene(gridpane);

        //title
        primaryStage.setTitle("Question2");

        //adding scene to the primaryStage
        primaryStage.setScene(scene);

        //displaying contents
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}
