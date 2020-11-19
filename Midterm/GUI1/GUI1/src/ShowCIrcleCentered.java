import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShowCIrcleCentered extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Circle circle = new Circle();
        circle.centerXProperty().bind(pane.widthProperty().divide(3));
        circle.centerYProperty().bind(pane.heightProperty().divide(3));
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.BLUE);
        pane.getChildren().add(circle);

        Scene scene = new Scene(pane , 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShowCircleCentered");
        primaryStage.show();

    }
}
