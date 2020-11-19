import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        Circle circle = new Circle(200, 200, 100);

        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        Circle[] circlePoints = new Circle[3];

        Line[] line = new Line[3];
        Text[] texts = new Text[3];

        for (int i = 0; i < circlePoints.length; i++){
            texts[i] = new Text();
            circlePoints[i] = new Circle(0, 0, 5);
            getRandLoc(circlePoints[i], circle);

            final int index = i;

            circlePoints[i].setOnMouseDragged(e ->{
                double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

                double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
                double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

                circlePoints[index].setCenterX(x_cod);
                circlePoints[index].setCenterY(y_cod);
                lineLocation(line, circlePoints, texts);
            });

            circlePoints[i].setOnMouseDragged(e ->{
                double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());

                double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
                double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);

                circlePoints[index].setCenterX(x_cod);
                circlePoints[index].setCenterY(y_cod);
                lineLocation(line, circlePoints, texts);
            });
        }

        for (int i = 0; i < line.length; i++){
            int circleIndex = (i + 1 >= circlePoints.length)? 0:i + 1;
            line[i] = new Line(circlePoints[i].getCenterX(), circlePoints[i].getCenterY(), circlePoints[circleIndex].getCenterX(), circlePoints[circleIndex].getCenterY());
        }

        lineLocation(line, circlePoints, texts);
        pane.getChildren().addAll(line);
        pane.getChildren().addAll(texts);
        pane.getChildren().addAll(circlePoints);

        pane.getChildren().addAll(circle);
        Scene scene = new Scene(pane, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void lineLocation(Line[] line, Circle[] circle, Text[] angles){
        for (int i = 0; i < line.length; i++){
            int circleIndex = (i + 1 >= circle.length)? 0:i + 1;
            line[i].setStartX(circle[i].getCenterX());
            line[i].setStartY(circle[i].getCenterY());
            line[i].setEndX(circle[i].getCenterX());
            line[i].setEndY(circle[i].getCenterY());

            angles[i].setX(circle[i].getCenterX() + 5);
            angles[i].setY(circle[i].getCenterY() - 5);
        }

        double a = distanceCalc(line[0]);
        double b = distanceCalc(line[1]);
        double c = distanceCalc(line[2]);

        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c)/ (-2 * b * c)));
        angles[2].setText(String.format("%.2f", A));

        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c)/ (-2 * a * c)));
        angles[0].setText(String.format("%.2f", B));

        double C = Math.toDegrees(Math.acos((c * c - b * b - a * a)/ (-2 * a * b)));
        angles[1].setText(String.format("%.2f", C));
    }

    public static double distanceCalc(Line line){
        double x0 = line.getStartX();
        double y0 = line.getStartY();
        double x1 = line.getEndX();
        double y1 = line.getEndX();

        return Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
    }

    public void getRandLoc(Circle point, Circle circle){
        double angle = Math.random() * 360;
        double x_cod = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
        double y_cod = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));
        point.setCenterX(x_cod);
        point.setCenterY(y_cod);
    }
}