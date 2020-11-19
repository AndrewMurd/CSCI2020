import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class main extends Application {

    TableView<StudentRecord> table = new TableView();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        TableColumn<StudentRecord, String> SID = new TableColumn("SID");
        SID.setCellValueFactory(new PropertyValueFactory("Id"));
        SID.setMinWidth(130);

        TableColumn<StudentRecord, Float> Assignments = new TableColumn("Assignments");
        Assignments.setCellValueFactory(new PropertyValueFactory("Assignments"));

        TableColumn<StudentRecord, Float> Midterm = new TableColumn("Midterm");
        Midterm.setCellValueFactory(new PropertyValueFactory("Midterm"));

        TableColumn<StudentRecord, Float> FinalExam = new TableColumn("Final Exam");
        FinalExam.setCellValueFactory(new PropertyValueFactory("Exam"));

        TableColumn<StudentRecord, Float> FinalMark = new TableColumn("Final Mark");
        FinalMark.setCellValueFactory(new PropertyValueFactory("FinalMark"));

        TableColumn<StudentRecord, Float> LetterGrade = new TableColumn("Letter Grade");
        LetterGrade.setCellValueFactory(new PropertyValueFactory("LetterGrade"));


        table.getColumns().addAll(SID, Assignments, Midterm, FinalExam, FinalMark, LetterGrade);

        DataSource data = new DataSource();

        table.setItems(DataSource.getAllMarks());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
}