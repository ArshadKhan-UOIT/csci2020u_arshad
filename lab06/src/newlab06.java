//Arshad Khan 100695721
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import java.nio.file.Path;
import java.util.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;


public class newlab06 extends Application {
    Rectangle[] housing;        //initializes the housing prices restangle array
    Rectangle[] commerical; //initializes the commerical prices restangle array
    //    HBox hbox = new HBox();
    Arc[] purchases;    //initializes the purchases arc array
    Pane pane = new Pane(); //initializes the pane
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };
    public void start(Stage primaryStage) throws Exception {
//        Rectangle rect1 = new Rectangle(0, 0, 10, 648);
//        rect1.setFill(Color.BLUE);
//        Rectangle rect2 = new Rectangle(10, 0, 10, 1024);
//        rect2.setFill(Color.RED);
        int SIZE = avgCommercialPricesByYear.length;
        housing = new Rectangle[SIZE];
        commerical = new Rectangle[SIZE];
        purchases = new Arc[SIZE];
        generateBarGraph();
        generatePieGraph();

//        root.getChildren().addAll(rect1, rect2);
        Scene scene = new Scene(pane, 1000, 600, Color.WHITE);
        primaryStage.setTitle("Lab06 Solution");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    private void generateBarGraph() {
        double maxBar = getMaxBarSize();    //this gets to max bar size to help with bar scaling
        int barOffset = 15;
        int heightOffset = (int) (600-50); //600 is the height of my window, gets the heightOffset for helping with the bar scaling
//        System.out.println(heightOffset);
        int scaling, y, initialX=50, x=initialX, barWidth=15;
        for (int i=0;i<housing.length;i++) {

            scaling = (int) ((avgHousingPricesByYear[i] / maxBar)*(heightOffset-barOffset));    //calculates the scaling for the housing prices array to help with the y position of the rectange
            y = (int) (heightOffset-scaling);   //sets the y position of the rectangle for housing
//            System.out.println(scaling);
//            System.out.println("housing"+y);
            housing[i] = rect(x,y,barWidth,scaling,Color.BLUE); //sets the rectangle conditions for housing

            scaling = (int) ((avgCommercialPricesByYear[i] / maxBar)*(heightOffset-barOffset)); //calculates the scaling for the commerical prices array to help with the y position of the rectange
            y = (int) (heightOffset-scaling);   //sets the y position of the rectangle for commerical
//            System.out.println("commerical"+y);
            commerical[i] = rect(x+barWidth,y,barWidth,scaling,Color.RED);  //sets the rectangle conditions for commerical

            x+=(2*barOffset);   //helps with the x position offset
            pane.getChildren().addAll(housing[i],commerical[i]);    //adds it to the pane
        }
//        System.out.println();

    }

    private void generatePieGraph() {
        int total = 0; for(int i : purchasesByAgeGroup) total += i; //gets the total
        double begin = 0, angle = 0, angleTotal = 0;
        for(int i=0; i<purchasesByAgeGroup.length-1; i++) {     //-1 because i want to add the last element in manually
            angle = (purchasesByAgeGroup[i] / (double)total) * 360; //gets percentage based on the purchase of each age group divided by to total calculated above
            angleTotal += angle;
            purchases[i] = arc(begin,angle,pieColours[i]);  //sets the colur
            pane.getChildren().addAll(purchases[i]);    //adds all but the last to the pane
            begin += angle; //gets the begginin angle of each purchase by each age group to then set the colours
        }
        purchases[purchasesByAgeGroup.length-1] = arc(begin,360-angleTotal, pieColours[purchasesByAgeGroup.length-1]);  //for the last purchase
        pane.getChildren().add(purchases[purchasesByAgeGroup.length-1]);    //add it to the pane
    }

    private Arc arc(double begin, double angle, Color color) {  //arc method that just initializes everything
        Arc arc = new Arc();
        arc.setCenterX(675.0f);
        arc.setCenterY(275.0f);
        arc.setRadiusX(150.0f);
        arc.setRadiusY(150.0f);
        arc.setStartAngle(begin);
        arc.setLength(angle);
        arc.setType(ArcType.ROUND);
        arc.setFill(color);
        return arc;     //and then returns the arc
    }


    private double getMaxBarSize() {
        double max = avgHousingPricesByYear[0]; //sets it to the first element in the array
        for(double d : avgHousingPricesByYear)  //goes through all of the elements
            if(d > max) max = d;    //if the elemets is greater, then it sets the max to the greatest
        for(double d : avgCommercialPricesByYear)   //does the same with the other array
            if(d > max) max = d;
        return max;     //then returns it
    }

    private Rectangle rect(int xPos, int yPos, double rectWidth, double rectHeight, Color fill) {   ////rectange method that just initializes everything
        Rectangle rect = new Rectangle();
//        System.out.println(yPos);
        rect.setX(xPos);
        rect.setY(yPos);
        rect.setWidth(rectWidth);
        rect.setHeight(rectHeight);
        rect.setFill(fill);
        return rect;    //returns all the conditions set for the rectangle
    }

}

