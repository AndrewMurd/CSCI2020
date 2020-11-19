import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;


public class main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    private static double[] avgHousingPricesByYear = {247381.0,264171.4,287715.3,294736.1,308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3,1219479.5,1246354.2,1295364.8,1335932.6,1472362.0,1583521.9,1613246.3};

    private static String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(1500, 800);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        int widthBarGraph = 70;
        int factor = 3000;
        gc.setFill(Color.BLUE);

        for (int i = 100; i < avgCommercialPricesByYear.length * widthBarGraph; i += widthBarGraph){
            gc.fillRect(i, (400 - avgCommercialPricesByYear[i/widthBarGraph]/factor) + 200, 20, avgCommercialPricesByYear[i/widthBarGraph]/factor);
        }

        gc.setFill(Color.RED);
        for (int i = 80; i < avgHousingPricesByYear.length * widthBarGraph; i += widthBarGraph){
            gc.fillRect(i, (400 - avgHousingPricesByYear[i/widthBarGraph]/factor) + 200, 20, avgHousingPricesByYear[i/widthBarGraph]/factor);
        }

        double previousAngle = 0;
        for (int i = 0; i < purchasesByAgeGroup.length; i ++){
            System.out.println(previousAngle);
            gc.setFill(pieColours[i]);
            gc.fillArc(800, 200, 300, 300, previousAngle, purchasesByAgeGroup[i]/31, ArcType.ROUND);
            previousAngle += purchasesByAgeGroup[i]/31;
        }

        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("");
        stage.show();
    }
}