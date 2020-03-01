//Arshad Khan 100695721
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
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

import static com.sun.glass.ui.Cursor.setVisible;


public class lab06 extends Application {
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
//    private static Color[] pieColours = {
//            Color.AQUA, Color.GOLD, Color.DARKORANGE,
//            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
//    };
    public void start(Stage primaryStage) throws Exception {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i=0;i<ageGroups.length;i++) {
            pieChartData.add(new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        }
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Avg Purchases by Age Groups");
//        pieChart.setStartAngle(0);
        pieChart.setClockwise(false);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Prices ($)");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Years (Yearly)");
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        primaryStage.setTitle("Lab 06");

        int size = avgHousingPricesByYear.length;
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Housing");
        for (int i=0;i<size;i++) {
            XYChart.Data<String, Number> data1 = new XYChart.Data(" " + (i+1), avgHousingPricesByYear[i]);
            dataSeries1.getData().add(data1);
        }

        int size2 = avgCommercialPricesByYear.length;
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Commercial");
        for (int i=0;i<size2;i++) {
            XYChart.Data<String, Number> data2 = new XYChart.Data(" " + (i+1), avgCommercialPricesByYear[i]);
            dataSeries2.getData().add(data2);
        }

        barChart.getData().addAll(dataSeries1, dataSeries2);
        barChart.setVerticalGridLinesVisible(false);
        barChart.setHorizontalGridLinesVisible(false);
//        barChart.setLegendVisible(false);
        barChart.setBarGap(0);
//        barChart.getYAxis().setTickLabelsVisible(false);
//        barChart.getYAxis().setOpacity(0);
        barChart.getXAxis().setTickLabelsVisible(false);
//        barChart.getXAxis().setOpacity(0);
//        barChart.getXAxis().lookup()
        barChart.setTitle("Avg Housing vs Commercial Prices");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(barChart, pieChart);
        Scene scene = new Scene(hbox, 800, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

