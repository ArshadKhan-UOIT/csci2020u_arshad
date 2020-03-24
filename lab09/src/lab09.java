import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import jdk.internal.org.objectweb.asm.tree.InsnList;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class lab09 extends Application {
    Canvas canvas;
    GraphicsContext g;
//    static ArrayList<Double> stock1;
//    static ArrayList<Double> stock2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 09 Solution");
        Pane pane = new Pane();
        ArrayList<Double> stock1 = downloadStockPrices("AAPL");
        ArrayList<Double> stock2 = downloadStockPrices("GOOG");
//        downloadStockPrices("AAPL");
//        downloadStockPrices("GOOG");
        canvas = new Canvas(600,600);
        g = canvas.getGraphicsContext2D();

        drawLinePlot(stock1,stock2);
        pane.getChildren().add(canvas);
        primaryStage.setScene(new Scene(pane, 600, 600));
        primaryStage.show();
    }

    public ArrayList<Double> downloadStockPrices(String symbol){
        ArrayList<Double> results = new ArrayList<>();
        String key = "Y8TVIUSPETR8HY28";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + symbol + "&apikey=" + key;
        String dataToParce = " ";
        //(last trading day of each month, monthly open, monthly high, monthly low, monthly close, monthly volume)
        try {
            URL aurl = new URL(url);
            URLConnection aconn = aurl.openConnection();
            Scanner conns = new Scanner(aconn.getInputStream());
//            String[] data;
//            conns.hasNextLine();
            while (conns.hasNextLine()){
                dataToParce += conns.nextLine();
//                data = dataToParce.split(",");
//                if(symbol.equalsIgnoreCase("AAPL")) {
//                    stock1.add(Double.parseDouble(data[2]));
//                } else if (symbol.equalsIgnoreCase("GOOG")) {
//                    stock2.add(Double.parseDouble(data[2]));
//                }
            }
            conns.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject getter1,getter2;
        JsonParser p = new JsonParser();
        getter1 = p.parse(dataToParce).getAsJsonObject();
        if(getter1 != null){
            getter2 = getter1.get("Monthly Time Series").getAsJsonObject();
            for(Map.Entry<String, JsonElement> element: getter2.entrySet()){
                results.add(element.getValue().getAsJsonObject().get("4. close").getAsDouble());
            }
        }
        return results;
    }

    public void drawLinePlot(ArrayList<Double> stockOne, ArrayList<Double> stockTwo) {
        g.strokeLine(50,50,50,550);
        g.strokeLine(50,550,550,550);

        double maxValueOne = Collections.max(stockOne);
        double maxValueTwo = Collections.max(stockTwo);
        double maxY = Math.max(maxValueOne, maxValueTwo);
        double maxX = Math.max(stockOne.size(), stockTwo.size());

        plotLine(stockOne,Color.RED,maxY,maxX);//Apple
        plotLine(stockTwo,Color.BLUE,maxY,maxX);//Google
    }

    public void plotLine(ArrayList<Double> stock, Color color, double maxY, double maxX){
        g.moveTo(50,550);
        g.beginPath();

        double stepX = 500/ maxX;
        double currentX=550;
        for(Double stocks: stock){
            g.lineTo(currentX-=stepX,550-(stocks/maxY)*500);
        }
        g.setStroke(color);
        g.stroke();
    }
}