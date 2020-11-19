import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ControlCircle extends Application {
    CirclePane circlePane = new CirclePane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);

        Button btEnlarge = new Button("Enlarge");
        Button btShrink = new Button("Shrink");
        hbox.getChildren().addAll(btEnlarge, btShrink);


        // create and register the handler
        btEnlarge.setOnAction(new EnlargeHandler());
        btShrink.setOnAction(new ShrinkHandler());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER);

        //create a scene and place it in the stage
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class EnlargeHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            circlePane.enlarge();
        }
    }

    class ShrinkHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            circlePane.shrink();
        }
    }
}

class CirclePane extends StackPane{
    private Circle circle = new Circle(30);

    public CirclePane(){
        getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.GREEN);
    }

    public void enlarge(){
        circle.setRadius(circle.getRadius() + 2);
    }


    public void shrink(){
        circle.setRadius(circle.getRadius() - 2);
    }

}
