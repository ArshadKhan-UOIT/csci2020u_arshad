import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.util.*;

public class lab07 extends Application {
    Arc[] occurrences;
    int[] results;
    Rectangle[] lengend;
    Pane pane = new Pane();
    Text[] text;
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON
    };
    public void start(Stage primaryStage) throws Exception {
        //Get scanner instance
        String[] warnings = {"FLASH FLOOD","SEVERE THUNDERSTORM","SPECIAL MARINE","TORNADO"};
        File file = new File("/home/arshad/Desktop/csci2020u/lab07/src/weatherwarnings-2015.csv");
        if (file.exists()) { System.out.println("File Found"); }
        else { System.out.println("File not Found"); }
        Scanner scanner = new Scanner(file);
        int ff=0,st=0,sm=0,t=0;
        //Set the delimiter used in file
        scanner.useDelimiter(",");

        while (scanner.hasNext())
        {
            String text = scanner.next();
            if (text.equals(warnings[0])) {
                ff++;
            } else if (text.equals(warnings[1])) {
                st++;
            } else if (text.equals(warnings[2])) {
                sm++;
            } else if (text.equals(warnings[3])) {
                t++;
            }

        }
        text = new Text[]{new Text(), new Text(), new Text(), new Text()};
        for (int i=0;i<text.length;i++) {
            text[i].setText(warnings[i]);
        }
//        System.out.println("ff " + ff);
//        System.out.println("st " + st);
//        System.out.println("sm " + sm);
//        System.out.println("t " + t);
        occurrences = new Arc[4];
        results = new int[4];
        results[0] = ff;
        results[1] = st;
        results[2] = sm;
        results[3] = t;
        lengend = new Rectangle[4];
        generatePieGraph();
        generateLeneng();

        //Do not forget to close the scanner
        scanner.close();
        Scene scene = new Scene(pane, 800, 600, Color.WHITE);
        primaryStage.setTitle("Lab07 Solution");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void generateLeneng() {
        int y=200, x=100;
        int recWidth = 50;
        int recLength = 25;
        for (int i=0;i<lengend.length;i++) {
            lengend[i] = rect(x,y,recWidth,recLength,pieColours[i]);
            text[i].setX(x+58);
            text[i].setY(y+18);
            y+=50;
            pane.getChildren().addAll(lengend[i], text[i]);
        }
    }

    private void generatePieGraph() {
        int total = 0; for(int i : results) total += i;
        double begin = 0, angle = 0, angleTotal = 0;
        for(int i=0; i<results.length-1; i++) {
            angle = (results[i] / (double)total) * 360;
            angleTotal += angle;
            occurrences[i] = arc(begin,angle,pieColours[i]);
            pane.getChildren().addAll(occurrences[i]);
            begin += angle;
        }
        occurrences[results.length-1] = arc(begin,360-angleTotal, pieColours[results.length-1]);
        pane.getChildren().add(occurrences[results.length-1]);
    }

    private Arc arc(double begin, double angle, Color color) {  //arc method that just initializes everything
        Arc arc = new Arc();
        arc.setCenterX(500.0f);
        arc.setCenterY(275.0f);
        arc.setRadiusX(150.0f);
        arc.setRadiusY(150.0f);
        arc.setStartAngle(begin);
        arc.setLength(angle);
        arc.setType(ArcType.ROUND);
        arc.setStroke(Color.BLACK);
        arc.setFill(color);
        return arc;     //and then returns the arc
    }
    private Rectangle rect(int xPos, int yPos, double rectWidth, double rectHeight, Color fill) {   ////rectange method that just initializes everything
        Rectangle rect = new Rectangle();
//        System.out.println(yPos);
        rect.setX(xPos);
        rect.setY(yPos);
        rect.setWidth(rectWidth);
        rect.setHeight(rectHeight);
        rect.setFill(fill);
        rect.setStroke(Color.BLACK);
        return rect;    //returns all the conditions set for the rectangle
    }
}
