import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static String[] warnings = {"FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO"};
    public static int[] countWarnings = new int[4];
    public static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON};

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 500);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Group group =  new Group();

        readCSV("src/weatherwarnings-2015.csv");

        double previousAngle = 0;
        double incr = 100;
        double factor = 80;
        for (int i = 0; i < countWarnings.length; i ++){
            System.out.println(previousAngle);
            gc.setFill(pieColours[i]);
            gc.fillArc(50, 100, 300, 300, previousAngle, countWarnings[i]/factor, ArcType.ROUND);
            gc.fillRect(400, incr, 70, 20);
            previousAngle += countWarnings[i]/factor;
            incr += 40;
        }

        Text text = new Text("FLASH FLOOD");
        Text text1 = new Text("SEVERE THUNDERSTORM");
        Text text2 = new Text("SPECIAL MARINE");
        Text text3 = new Text("TORNADO");

        text.setX(500);
        text.setY(110);
        text1.setX(500);
        text1.setY(150);
        text2.setX(500);
        text2.setY(190);
        text3.setX(500);
        text3.setY(230);

        group.getChildren().addAll(text,text1,text2,text3);

        group.getChildren().addAll(canvas);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
    }

    private static void readCSV(String fileName) {
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line != null) {

                String[] data = line.split(",");

                //System.out.println(data[5]);

                if (data[5].equals(warnings[0])){
                    countWarnings[0]++;
                } else if (data[5].equals(warnings[1])){
                    countWarnings[1]++;
                } else if (data[5].equals(warnings[2])){
                    countWarnings[2]++;
                } else if (data[5].equals(warnings[3])){
                    countWarnings[3]++;
                } else {
                    System.out.println("Error!!!");
                }

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}