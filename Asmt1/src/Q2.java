/*
 * Tashdiq Ahmed 100701752
 * Arshad Khan 100695721
 * Problem Description:
 * Write a program that calculates the future value of an investment at
 * a given interest rate for a specified number of years.
 * The formula for the calculation is as follows:
 * futureValue = investmentAmount * (1 + monthlyInterestRate)years*12
 *
 *
 *
 *
 *
 * */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.lang.Math;
import java.text.DecimalFormat;


public class Q2 extends Application {
    private static DecimalFormat df = new DecimalFormat("0.00");    //decimal formatting for money (2 decimal places
    public void start(Stage primaryStage) throws Exception {
        //texts
        Text investmentAmount = new Text("Investment Amount");  //text for the Investment Amount

        Text years = new Text("Years"); //text for the Years

        Text annualInterestRate = new Text("Annual Interest Rate");     //text for the Annual Interest Rate

        Text futureValue = new Text("Future Value");    //text for the Future Value

        //text field
        TextField t1 = new TextField();     //for entering Investment Amount

        TextField t2 = new TextField();     //for entering Years

        TextField t3 = new TextField();     //for entering Annual Interest Rate

        TextField t4 = new TextField();     //for displaying Future Value
        t4.setEditable(false);              //setting future value textfield so it cannot have stuff entered

        //button for the calculate the future value
        Button b1 = new Button("Calculate");

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
        //arranging the text and textfield of investmentAmount
        gridpane.add(investmentAmount, 0, 0);
        gridpane.add(t1, 1, 0);
        //arranging the text and textfield of years
        gridpane.add(years, 0, 1);
        gridpane.add(t2, 1, 1);
        //arranging the text and textfield of annualInterestRate
        gridpane.add(annualInterestRate, 0, 2);
        gridpane.add(t3, 1, 2);
        //arranging the text and textfield of futureValue
        gridpane.add(futureValue, 0, 3);
        gridpane.add(t4, 1, 3);
        //arranging for the calculate button
        gridpane.add(b1, 1, 4);
        //lambda function for when the user presses the "calculate" button
        b1.setOnAction(e-> {
            double eIA = Double.parseDouble(t1.getText());  //parsing the test from the first textfield to a double
            double eY = Double.parseDouble(t2.getText());   //parsing the test from the second textfield to a double
            double eAIR = Double.parseDouble(t3.getText()); //parsing the test from the third textfield to a double
            double eFV = (double) (eIA*Math.pow((1+(eAIR/1200)),(eY*12)));  //calculating the future value
            String result = df.format(eFV); //formatting the string result of the future value
//            String result = String.valueOf(eFV);
            t4.setText(result); //setting the text to display the calculated future value
        });
        Scene scene = new Scene(gridpane);  //adding gridplane to the scene

        //title
        primaryStage.setTitle("Q2 Solution");

        //adding scene to the primaryStage
        primaryStage.setScene(scene);

        //displaying contents
        primaryStage.show();
    }
}