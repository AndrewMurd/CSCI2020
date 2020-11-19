import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;

public class main extends Application {

    TableView<StudentRecord> table = new TableView();
    Group group = new Group(table);

    TextField sidF = new TextField("SID");
    TextField assignmentF = new TextField("Assignments/100");
    TextField midtermF = new TextField("Midterm/100");
    TextField finalF = new TextField("Final Exam/100");

    ObservableList<StudentRecord> marks = DataSource.getAllMarks();

    Desktop desktop = Desktop.getDesktop();

    String currentFile = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        table.setLayoutY(26);
        Text userNameLbl = new Text("Username: ");
        TextField userNameFld = new TextField();
        Text messageLbl = new Text("Message: ");
        TextField messageFld = new TextField();
        Button sendBtn = new Button("Send");

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

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("file");

        MenuItem NewM = new MenuItem("New");
        MenuItem OpenM = new MenuItem("Open");
        MenuItem SaveM = new MenuItem("Save");
        MenuItem SaveAsM = new MenuItem("Save As");
        MenuItem ExitM = new MenuItem("Exit");

        FileChooser fileChooser = new FileChooser();

        NewM.setOnAction(e -> {
            table.getItems().clear();
        });

        OpenM.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            currentFile = file.getPath();
            if (file != null) {
                loadCSV(file);
            }
        });

        SaveM.setOnAction(e -> {
            try {
                writeCSV(currentFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        SaveAsM.setOnAction(e -> {
            try {
                File file = fileChooser.showSaveDialog(stage);
                currentFile = file.getPath();
                writeCSV(currentFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ExitM.setOnAction(e -> {
            stage.close();
        });

        Button add = new Button("Add");
        add.setPrefWidth(50);
        add.setLayoutX(80);
        add.setLayoutY(550);

        add.setOnAction(e -> {
            String sid = sidF.getText();
            float midterm = Float.valueOf(midtermF.getText());
            float assignments = Float.valueOf(assignmentF.getText());
            float finalExam = Float.valueOf(finalF.getText());

            StudentRecord newStudent = new StudentRecord(sid, assignments, midterm, finalExam);
            marks.add(newStudent);
        });

        sidF.setLayoutX(80);
        sidF.setLayoutY(445);
        assignmentF.setLayoutX(360);
        assignmentF.setLayoutY(445);
        midtermF.setLayoutX(80);
        midtermF.setLayoutY(485);
        finalF.setLayoutX(360);
        finalF.setLayoutY(485);

        Text text = new Text("SID:");
        text.setX(15);
        text.setY(460);
        Text text1 = new Text("Assignments:");
        text1.setX(280);
        text1.setY(460);
        Text text2 = new Text("Midterm:");
        text2.setX(15);
        text2.setY(500);
        Text text3 = new Text("Final Exam:");
        text3.setX(280);
        text3.setY(500);

        menuFile.getItems().addAll(NewM, OpenM, SaveM, SaveAsM, ExitM);
        menuBar.getMenus().addAll(menuFile);

        table.getColumns().addAll(SID, Assignments, Midterm, FinalExam, FinalMark, LetterGrade);

        group.getChildren().addAll(menuBar, add, text, text1, text2, text3, sidF, assignmentF, midtermF, finalF);

        DataSource data = new DataSource();
        table.setItems(marks);

        Scene scene = new Scene(group, 530, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void loadCSV(File file) {
        String CsvFile = file.getPath();
        String FieldDelimiter = ",";

        table.getItems().clear();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(CsvFile));

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                StudentRecord newStudent = new StudentRecord(fields[0], Float.valueOf(fields[1]), Float.valueOf(fields[2]), Float.valueOf(fields[3]));
                marks.add(newStudent);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSV(String file) throws Exception {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (StudentRecord student : marks) {

                String text = student.getId() + "," + student.getAssignments() + "," + student.getMidterm() + "," + student.getExam() +"\n";
                writer.write(text);
            }
            System.out.println("Saved Table to:  " + currentFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }
    }
}