
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application {

    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Lab 10 Client");
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Text userNameLbl = new Text("Username: ");
        TextField userNameFld = new TextField();
        Text messageLbl = new Text("Message: ");
        TextField messageFld = new TextField();
        Button sendBtn = new Button("Send");

        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String myMessage = userNameFld.getText() + ": " + messageFld.getText();
                System.out.println("Text being sent is " + myMessage);
                sendMessage(myMessage);
                messageFld.setText("");
            }
        });
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(evt -> System.exit(0));

        gridPane.add(userNameLbl, 0, 0);
        gridPane.add(userNameFld, 1, 0);
        gridPane.add(messageLbl, 0, 1);
        gridPane.add(messageFld, 1, 1);
        gridPane.add(sendBtn, 0, 2);
        gridPane.add(exitBtn, 0, 3);


        root.getChildren().addAll(gridPane);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage(String message) {
        try {
            Socket socket = new Socket("localhost", 6000);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println(message);
            out.flush();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
