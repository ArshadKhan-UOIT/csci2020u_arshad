/*
 * Tashdiq Ahmed 100701752
 * Arshad Khan 100695721
 * Problem Description: Develop a program that displays a histogram to show the
 * occurrences of each letter in a text area. The histogram should show the occurrences
 *  of each letter in a text file, as shown in the following figure.
 * Assume that the letters are not case sensitive.
 *
 *
 *
 *
 * */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.*;
public class Q4 extends Application {
    final static String alphabet[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; /*Array to keep track of all
                         the categories on the X axis. Also used for a convenient loop later to add data to the chart.*/
    private static String filename=""; //A string to hold the name of the file to-be. Used to check if it exists.
    private static int[] freqs = new int[26]; //Int array to hold the amount of times each letter appears.
    public  int[] countLetters(String TEXT) throws IOException{ /*Function to count the amount of letters in the file.
                                                                Takes the name of the file as a parameter.*/
        BufferedReader in = new BufferedReader(new FileReader(TEXT)); //Input stream.
        String line;
        while((line = in.readLine()) != null){ //As long as there is a line to read...
            line = line.toUpperCase(); //Case doesn't matter, so we can force everything to upper case for convenience
            for(char ch:line.toCharArray()){
                if(Character.isLetter(ch)){
                    freqs[ch - 'A']++;
                    /*Function loops for each letter in the array/former line. Increments the counter of whatever letter
                      in the alphabet array that it's currently on.*/
                }
            }
        }
        in.close(); //Closing the input stream.
        return freqs;
    }

    public void start(Stage stage) throws Exception {
        TextField t1 = new TextField(); //Creating the text field for the user-input filepath
        t1.setPromptText("Filename");
        BorderPane borderpane = new BorderPane(); //Borderpane for later use
        stage.setTitle("Q4 Solution");
        final CategoryAxis xAxis = new CategoryAxis(); //Creating axises for the chart
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> chart = new BarChart<String,Number>(xAxis,yAxis); //Creating the empty chart
        chart.setCategoryGap(0);
        chart.setTitle("Letter Frequency");
        xAxis.setLabel("Letters");
        yAxis.setLabel("Frequency");
        XYChart.Series series1 = new XYChart.Series(); //Empty set of data to eventually put in the chart
        series1.setName("Frequency");
        t1.setOnKeyPressed(e-> { //Key press event
            if (e.getCode() == KeyCode.ENTER) //If the key is enter
            {
                File inputFile = new File(t1.getText());
                if (!inputFile.exists()) {
                    System.out.println("File not found. Is the path correct?");
                    return; //If the file is not found, the code is "cancelled"
                }
                else {
                    System.out.println("File found. Creating chart.");
                }
                try {
                    for (int i=0; i<26; i++) {
                        freqs[i] = 0;/*Sets the frequency values to 0 whenever the enter key is pressed, since each
                                        enter press is assumedly a new text file to chart*/
                    }
                    countLetters(t1.getText()); //Function call to count the letters
                    for (int x=0; x<alphabet.length; x++) {
                        series1.getData().add(new XYChart.Data<>(alphabet[x], freqs[x]));
                        //Adds data to the series for each letter in the alphabet.
                    }
                    chart.getData().addAll(series1); //Adds the data to the series.
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        borderpane.setCenter(chart); //Sets the chart to the middle of the gui
        borderpane.setBottom(t1); //Sets the text to the bottom of the gui
        Scene scene  = new Scene(borderpane, 600, 400); //Sets minimum dimensions for the window
        stage.setScene(scene); //Creating and displaying the scene
        stage.show();
    }
    public static void main(String[] args) throws IOException
    {
        launch(args);
    }
}