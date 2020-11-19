import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowHBoxVBox extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();

        pane.setTop(getHBox());
        pane.setLeft(getVBox());

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getHBox(){
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15,15,15,15));
        hBox.setStyle("-fx-background-color: gold");
        hBox.getChildren().add(new Button("Computer Science"));
        hBox.getChildren().add(new Button("Chemistry"));
        ImageView imageView = new ImageView(new Image("image/us.png"));
        hBox.getChildren().add(imageView);

        return hBox;
    }

    private VBox getVBox(){
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15,15,15,15));
        vBox.getChildren().add(new Label("Courses"));

        Label[] courses = {new Label("CSCI 1060"), new Label("CSCI 2020") ,
                new Label("CSCI 2060"), new Label("CSCI 3070")};

        for (Label course :courses){
            VBox.setMargin(course, new Insets(0,0,0,15));
            vBox.getChildren().add(course);
        }
        return vBox;
    }
}
