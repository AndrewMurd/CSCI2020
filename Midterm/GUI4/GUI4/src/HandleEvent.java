import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HandleEvent extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox pane = new HBox(20);
        pane.setMinSize(200,100);
        pane.setAlignment(Pos.CENTER);

        Button btOK = new Button("OK");
        Button btCancel = new Button("Cancel");
        pane.getChildren().addAll(btOK , btCancel);

        OKHandler handler1 = new OKHandler();
        btOK.setOnAction(handler1);



        CancelHandler handler2 = new CancelHandler();
        btCancel.setOnAction(handler2);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HandleEvent");
        primaryStage.show();
    }
}

class OKHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Ok button clicked");
    }
}

class CancelHandler implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Cancel button clickec");
    }
}
