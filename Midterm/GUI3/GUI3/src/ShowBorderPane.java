import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ShowBorderPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();

        StackPane pane1 = new StackPane();
        pane1.getChildren().add(new Label("Top"));
        pane1.setStyle("-fx-border-color: red");
        pane1.setPadding(new Insets(10,10,10,10));

        pane.setTop(pane1);

        pane.setRight(new CustomPane("Right"));
        pane.setBottom(new CustomPane("Bottom"));
        pane.setLeft(new CustomPane("Left"));
        pane.setCenter(new CustomPane("Center"));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShowBorderPane");
        primaryStage.show();
    }
}
class CustomPane extends StackPane{
    public CustomPane(String title){
        getChildren().add(new Label(title));
        setStyle("-fx-border-color: red");
        setPadding(new Insets(5,5,5,5));
    }
}