import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Scanner;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Scanner in = new Scanner(System.in);
    static int sceneX = 1000;
    static int sceneY = 800;

    @Override
    public void start(Stage primaryStage) {

        //Get Circle 1
        Circle circle1 = getCircle1();
        while (true) {
            //Make sure circle is not out of bounds
            if (checkValidity(circle1)){
                circle1 = getCircle1();
            } else break;
        }
        //Get Circle 2
        Circle circle2 = getCircle2();
        while (true) {
            if (checkValidity(circle2)){
                circle2 = getCircle2();
            } else break;
        }

        String solution = getSolution(circle1, circle2);
        Text output = new Text();
        output.setText(solution);
        output.setX(340);
        output.setY(790);
        output.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Group group = new Group();
        group.getChildren().addAll(circle1, circle2, output);

        primaryStage.setTitle("Question 2");
        Scene s = new Scene(group, 1000, 800);
        primaryStage.setScene(s);
        primaryStage.show();
    }
    //Check circles
    public static String getSolution(Circle circle1, Circle circle2) {
        //Get distance between circle centers
        double distanceX = circle1.getCenterX() - circle2.getCenterX();
        double distanceY = circle1.getCenterY() - circle2.getCenterY();
        double distanceBetween = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        //Set Text to correct solution
        if (distanceBetween >= circle1.getRadius() + circle2.getRadius()) {
            return "The circles do not overlap";
        } else if (circle1.getRadius() > distanceBetween) {
            if (circle1.getRadius() > distanceBetween + circle2.getRadius()) {
                return "One circle is contained in another";
            }
            return "The circles overlap";
        } else {
            if (circle2.getRadius() > distanceBetween + circle1.getRadius()) {
                return "One circle is contained in another";
            }
            return "The circles overlap";
        }
    }

    public static Circle getCircle1(){
        System.out.println("Circle 1:");
        System.out.print("x: ");
        double x = Double.parseDouble(in.nextLine());
        System.out.print("y: ");
        double y = Double.parseDouble(in.nextLine());
        System.out.print("radius: ");
        double r = Double.parseDouble(in.nextLine());

        Circle circle = new Circle(x, y, r);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        return circle;
    }

    public static Circle getCircle2(){
        System.out.println("Circle 2:");
        System.out.print("x: ");
        double x = Double.parseDouble(in.nextLine());
        System.out.print("y: ");
        double y = Double.parseDouble(in.nextLine());
        System.out.print("radius: ");
        double r = Double.parseDouble(in.nextLine());

        Circle circle = new Circle(x, y, r);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        return circle;
    }
    //Check if circle is out of bounds
    public static boolean checkValidity(Circle circle) {
        if ((circle.getCenterX() + circle.getRadius() > sceneX || circle.getCenterX() - circle.getRadius() < 0) ||
                (circle.getCenterY() + circle.getRadius() > sceneY || circle.getCenterY() - circle.getRadius() < 0)) {
            System.out.println("Circle out of Bounds!");
            return true;
        } else {
            return false;
        }
    }
}
