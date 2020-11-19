package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Main extends Application implements EventHandler<ActionEvent> {

    String username = new String();
    String pass = new String();
    String name = new String();
    String email = new String();
    String phone = new String();

    Date birth = new Date();

    GridPane grid = new GridPane();

    TextField usernameTxtF = new TextField();
    TextField passTxtF = new TextField();
    TextField nameTxtF = new TextField();
    TextField emailTxtF = new TextField();
    TextField phoneTxtF = new TextField();

    DatePicker datePicker = new DatePicker();

    Text usernameTxt = new Text(10, 50,"Username:");
    Text passTxt = new Text(10, 50, "Password:");
    Text nameTxt = new Text(10, 50, "Full Name:");
    Text emailTxt = new Text(10, 50, "E-Mail:");
    Text phoneTxt = new Text(10, 50, "Phone #:");
    Text birthTxt = new Text(10, 50, "Date of Birth:");

    @Override
    public void start(Stage primaryStage) throws Exception{
        usernameTxtF.setPrefWidth(200);
        datePicker.setPrefWidth(200);

        Button register = new Button("Register");

        grid.add(usernameTxtF, 2, 1);
        grid.add(passTxtF, 2, 2);
        grid.add(nameTxtF, 2, 3);
        grid.add(emailTxtF, 2, 4);
        grid.add(phoneTxtF, 2, 5);

        grid.add(datePicker, 2, 6);

        grid.add(usernameTxt, 0, 1);
        grid.add(passTxt, 0, 2);
        grid.add(nameTxt, 0, 3);
        grid.add(emailTxt, 0, 4);
        grid.add(phoneTxt, 0, 5);
        grid.add(birthTxt, 0, 6);

        grid.add(register, 2, 7);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        register.setOnAction(this);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 04 Solution");
        primaryStage.setScene(new Scene(grid, 500, 300));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        username = String.valueOf(usernameTxtF.getText());
        pass = String.valueOf(passTxtF.getText());
        name = String.valueOf(nameTxtF.getText());
        email = String.valueOf(emailTxtF.getText());
        phone = String.valueOf(phoneTxtF.getText());
        LocalDate localDate = datePicker.getValue();
        if (localDate != null){
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            birth = Date.from(instant);
        }

        System.out.println("Username: " + username +
                "\nPassword: " + pass +
                "\nFull Name: " + name +
                "\nEmail: " + email +
                "\nPhone #: " +  phone +
                "\nBirth Date: " + birth);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
