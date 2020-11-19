import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LambdaHandlerDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        Button btNew = new Button("New");
        Button btOpen = new Button("Open");
        Button btSave = new Button("Save");
        Button btPrint = new Button("Print");

        hBox.getChildren().addAll(btNew,btOpen,btSave, btPrint);

        btNew.setOnAction((ActionEvent e) -> {
            System.out.println("Proces New");
        });

        btSave.setOnAction((e) -> {
            System.out.println("Process Save");
        });

        btOpen.setOnAction(e ->{
            System.out.println("Process Open");
        });

        btPrint.setOnAction(e -> System.out.println("Process Print"));

        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
