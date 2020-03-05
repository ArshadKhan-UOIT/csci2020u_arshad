/*
 * Tashdiq Ahmed 100701752
 * Arshad Khan 100695721
 * Problem Description:
 * Problem Description:
 * Draw a circle with three random points on the circle.
 * Connect the points to form a triangle. Display the angles
 * in the triangle. Use the mouse to drag a point along the perimeter
 * of the circle. As you drag it, the triangle and angles are redisplayed
 * dynamically.
 *
 *
 *
 *
 *
 * */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.text.Text;

import java.util.Random;

import static java.lang.Math.*;



public class Q3 extends Application {
    Group root = new Group();
    Circle MainCircle = new Circle();   //this is the main circle that the three points have to osscatite around
    Circle[] points = {new Circle(), new Circle(), new Circle()};      //these are the three different points, they are just much smaller circles (circle array)
    Line[] lines = {new Line(points[0].getCenterX(),points[0].getCenterY(),points[1].getCenterX(),points[1].getCenterY()),  //this creates the 3 different lines for connecting the points (line array)
            new Line(points[1].getCenterX(),points[1].getCenterY(),points[2].getCenterX(),points[2].getCenterY()),
            new Line(points[2].getCenterX(),points[2].getCenterY(),points[0].getCenterX(),points[0].getCenterY())
    };
    double[] angles = getAngles();  //initializing the angles
    Text[] anglesTxt = {new Text(points[0].getCenterX(),points[0].getCenterY()-15,String.format("%.1f", toDegrees(angles[0]))),     //just creating the texts for the angles to be displayed
            new Text(points[1].getCenterX(),points[1].getCenterY()-15,String.format("%.1f", toDegrees(angles[1]))),
            new Text(points[2].getCenterX(),points[2].getCenterY()-15,String.format("%.1f", toDegrees(angles[2])))
    };
    public void start(Stage primaryStage) throws Exception {
        MainCircle.setCenterX(300.0f);  //conditions for the main circle that the point for making the triangle go around
        MainCircle.setCenterY(300.0f);
        MainCircle.setRadius(100.0f);
        MainCircle.setFill(Color.TRANSPARENT);
        MainCircle.setStroke(Color.BLACK);
        MainCircle.setStrokeWidth(1.5);

        displayRandomPoints(MainCircle, points);    //this generates the random position for the points (circle "points" array)

        Pane pane = new Pane();

        root.getChildren().add(MainCircle); //just adds it to the Group for displaying

        for (int i = 0; i < points.length; i++) {
            points[i].setFill(Color.RED);   //just making the fill color red like in the doc
            points[i].setStroke(Color.BLACK);   //and the border of the points black
            final int index=i;
            points[i].setOnMouseDragged((e) -> {    //lambda function for setting the mouse dragging of the points
                Point2D Center = new Point2D(MainCircle.getCenterX(), MainCircle.getCenterY()); //gets the center of the main circle
                Point2D mouse = new Point2D(e.getX(), e.getY());    //gets x and y position of the mouse
                Point2D centerToMouse = mouse.subtract(Center); //getting the different between the mouse and the center of the circle
                Point2D centerToNewPoint = centerToMouse.normalize().multiply(MainCircle.getRadius());  //helps to get the points position to be limited to the circle
                Point2D newPoint = centerToNewPoint.add(Center);    //creating that new point
                points[index].setCenterX(newPoint.getX());  //sets it to the circle "points" array position in x
                points[index].setCenterY(newPoint.getY());  //sets it to the circle "points" array position in y
//                System.out.println("(x = " + newPoint.getX() + " , y = " + newPoint.getY() + ")");
                update(lines, points);  //calls the update which helps to update the lines for a new "point" and it also helps to get the angles
//                getAngles();
                anglesTxt[index].setX(points[index].getCenterX());  //this just sets the position of the angle text for the x position
                anglesTxt[index].setY(points[index].getCenterY()-15);   //this just sets the position of the angle text for the y position
            });
            pane.getChildren().add(points[i]);  //adds the points to the Pane
        }
        update(lines, points);  //calls the update to add the lines to the pane as well as the angle texts
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(anglesTxt);
        root.getChildren().add(pane);   //adds the pane to the group for displaying
        //Creating a scene object
        Scene scene = new Scene(root, 600, 600);
        //Setting title to the Stage
        primaryStage.setTitle("Q3 Solution");
        primaryStage.centerOnScreen();
        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.setResizable(false);   //so you cant resize the window to mess up the centering
        primaryStage.show();
    }

    private double[] getAngles() {  //just returns the angles array
        double a = hypot(points[1].getCenterX()-points[2].getCenterX(),points[1].getCenterY()-points[2].getCenterY());  //calculates the hypotneus for each line
        double b = hypot(points[0].getCenterX()-points[2].getCenterX(),points[0].getCenterY()-points[2].getCenterY());
        double c = hypot(points[1].getCenterX()-points[0].getCenterX(),points[1].getCenterY()-points[0].getCenterY());

        double[] angle = new double[3];
        angle[0] = acos((a*a -b*b - c*c)/(-2*b*c)); //calculates the angles
        angle[1] = acos((b*b -a*a - c*c)/(-2*a*c));
        angle[2] = acos((c*c -b*b - a*a)/(-2*b*a));
        return angle;
    }
    private void displayRandomPoints(Circle mainCircle, Circle[] points) { //this generates random points for the circle "points" array
        double angle, xPos, yPos;
        for (int i=0;i<points.length;i++) {
            angle = (random() * 360);   //gets a random val from 0 to 360
            xPos = mainCircle.getRadius()*cos(angle)+300;   //calculates the x position with the given angle, the 300 is there to help center the x position on the main circles border
            yPos = mainCircle.getRadius()*sin(angle)+300;   //calculates the y position with the given angle, the 300 is there to help center the y position on the main circles border
            points[i].setCenterX(xPos);     //setting the x position of the "points"
            points[i].setCenterY(yPos);     //setting the y position of the "points"
            points[i].setRadius(7.0f);      //setting size of the circle "points" array
        }
    }
    //the update just updates the lines and angles
    private void update(Line[] lines, Circle[] points) {
        int pointsIndex;
        for (int i=0;i<points.length;i++) { //just initializes the start and end points of the line in x and y position when the update method is called
            if (i+1>=points.length) {
                pointsIndex = 0;
            } else {
                pointsIndex = i+1;
            }
            lines[i].setStartX(points[i].getCenterX());
            lines[i].setStartY(points[i].getCenterY());
            lines[i].setEndX(points[pointsIndex].getCenterX());
            lines[i].setEndY(points[pointsIndex].getCenterY());
        }
        getAngles();    //gets the angles, then makes sure to reset the angles when the update is called
        double a = hypot(points[1].getCenterX()-points[2].getCenterX(),points[1].getCenterY()-points[2].getCenterY());
        double b = hypot(points[0].getCenterX()-points[2].getCenterX(),points[0].getCenterY()-points[2].getCenterY());
        double c = hypot(points[1].getCenterX()-points[0].getCenterX(),points[1].getCenterY()-points[0].getCenterY());

        angles[0] = acos((a*a -b*b - c*c)/(-2*b*c));
        angles[1] = acos((b*b -a*a - c*c)/(-2*a*c));
        angles[2] = acos((c*c -b*b - a*a)/(-2*b*a));
        for (int j=0;j<3;j++) { //sets the angles using the text
            anglesTxt[j].setText(String.format("%.1f", toDegrees(angles[j])));  //format is 1 decimal place
            anglesTxt[j].setX(points[j].getCenterX());  //sets the texts x position for each "point"
            anglesTxt[j].setY(points[j].getCenterY()-15);   //sets the texts y position for each "point"
        }

    }
}
