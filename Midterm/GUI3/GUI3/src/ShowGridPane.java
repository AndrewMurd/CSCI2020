import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowGridPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        pane.add(new Label("First Name"),0,0);
        pane.add(new TextField(), 1,0);
        pane.add(new Label("MI:"), 0,1);
        pane.add(new TextField(), 1,1);
        pane.add(new Label("Last Name:"), 0 , 2);
        pane.add(new TextField(), 1 ,2);

        Button btAdd = new Button("Add Name");
        pane.add(btAdd, 1,3);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShowGridPane");
        primaryStage.show();
    }
}
