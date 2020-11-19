import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();

        Button add = new Button("add");
        Button subtract = new Button("subtract");
        Button multiply = new Button("mutiply");
        Button divide =  new Button("divide");
        add.setPrefWidth(60);
        subtract.setPrefWidth(100);
        multiply.setPrefWidth(100);
        divide.setPrefWidth(100);

        Text text1 = new Text(10,50, "num1: ");
        Text text2 = new Text(10,50, "num2: ");
        Text text = new Text(10,50, "result: ");

        grid.add(text1, 1, 1);
        grid.add(text2, 4, 1);
        grid.add(text, 7, 1);

        TextField number1 = new TextField();
        TextField number2 = new TextField();
        TextField result = new TextField();
        result.setPrefWidth(50);
        number1.setPrefWidth(50);
        number2.setPrefWidth(50);


        grid.add(number1, 3, 1);
        grid.add(number2, 5, 1);
        grid.add(result, 8, 1);
        grid.add(add, 2, 4);
        grid.add(subtract, 4, 4);
        grid.add(multiply, 6, 4);
        grid.add(divide, 8, 4);

        grid.setHgap(10);
        grid.setVgap(10);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float num1 = Float.valueOf(number1.getText());
                float num2 = Float.valueOf(number2.getText());

                result.setText(String.valueOf(num1+num2));
            }
        });
        subtract.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float num1 = Float.valueOf(number1.getText());
                float num2 = Float.valueOf(number2.getText());

                result.setText(String.valueOf(num1-num2));
            }
        });
        multiply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float num1 = Float.valueOf(number1.getText());
                float num2 = Float.valueOf(number2.getText());

                result.setText(String.valueOf(num1*num2));
            }
        });
        divide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                float num1 = Float.valueOf(number1.getText());
                float num2 = Float.valueOf(number2.getText());

                result.setText(String.valueOf(num1/num2));
            }
        });


        Scene scene = new Scene(grid, 500, 300);
        stage.setScene(scene);
        stage.show();
    }


}