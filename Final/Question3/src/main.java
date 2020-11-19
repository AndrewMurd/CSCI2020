import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group group = new Group();
        //Get flag image
        ImageView flag = new ImageView("images/flag0.gif");
        group.getChildren().addAll(flag);
        flag.setX(40);
        flag.setY(460);
        //Raise flag through thread
        new Thread(()-> {
           try {
               //Raise flag 390 pixels
               for (int i = 0; i < 390; i++) {
                   flag.setY(flag.getY() - 1);
                   try {
                       Thread.sleep(20);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           } catch (Exception e){
               e.printStackTrace();
           }
        }).start();

        stage.setTitle("Question 3");
        Scene scene = new Scene(group, 500, 700);
        stage.setScene(scene);
        stage.show();
    }
}