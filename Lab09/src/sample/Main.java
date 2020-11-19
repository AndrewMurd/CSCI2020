package sample;
import com.google.gson.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



    public class Main extends Application {

        private Canvas canvas;

        private Group root = new Group();

        @Override
        public void start(Stage primaryStage) throws Exception{

            int count =0;
            int count1 =0;
            ArrayList<Float> numlist1 = new ArrayList<Float>();
            ArrayList<Float> numlist2 = new ArrayList<Float>();
            BufferedReader reader;
            String line;
            StringBuffer MSFTResponseContent = new StringBuffer();
            StringBuffer GOOGLResponseContent = new StringBuffer();
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=KOGVZJS2UO6XF9TB"); //Microsoft
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            System.out.println(status);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {

                MSFTResponseContent.append(line);
                System.out.println(line + "///");
                if ((line.contains("close")) && (count1 ==0)) {
                    count1 +=1;} else if ((line.contains("close")) && (count1 ==1)) {
                    String ex2 = line.substring(line.lastIndexOf("close") + 9);
                    String strNew = ex2.replace(",", "");
                    String strNew2 = strNew.replaceAll("^\"|\"$", "");
                    numlist1.add(Float.parseFloat(strNew2));
                }
            }

            System.out.println(MSFTResponseContent.toString());
            url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=GOOGL&apikey=KOGVZJS2UO6XF9TB"); //Google
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            status = connection.getResponseCode();
            System.out.println(status);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                GOOGLResponseContent.append(line);
                System.out.println(line + "///");
                if ((line.contains("close")) && (count ==0)) {
                    count +=1;} else if ((line.contains("close")) && (count ==1)) {
                    String ex2 = line.substring(line.lastIndexOf("close") + 9);
                    String strNew = ex2.replace(",", "");
                    String strNew2 = strNew.replaceAll("^\"|\"$", "");
                    numlist2.add(Float.parseFloat(strNew2));
                }

            }
            System.out.println(GOOGLResponseContent.toString());
            System.out.println("ARRAYS:");
            System.out.println(numlist1);
            System.out.println(numlist2);

            //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            //Group root = new Group();
            canvas = new Canvas();
            canvas.widthProperty().bind(primaryStage.widthProperty());
            canvas.heightProperty().bind(primaryStage.heightProperty());
            root.getChildren().add(canvas);

            primaryStage.setTitle("Lab 9");
            Scene scene = new Scene(root, 1000, 950);
            primaryStage.setScene(scene);
            primaryStage.show();

            downloadStockPrices(numlist1,numlist2);
        }


        private void downloadStockPrices(ArrayList<Float> numlist1, ArrayList<Float> numlist2) {
            //test values
            //Double[] arrayx = {118.705,120.82,117.25,113.25,113.24,111.2,108.3,107.27,106.48,107.88,107.9,104.88,102.51,102.41,106.88,111.27,113.42,111.33,108.56,109.96,112.24,108.7,110.54,111.81,113.08,116.18,115.1,115.29,113.73,111.955,112.777,108.56,109.75,110.16,108.09,111.15,108.2,105.6,101.43,100.11,102.52,102.03,102.69,100.86,98.98,98.69,97.95,96.3964,97.9,97.07,94.18,93.065,95.139,94.05,97.24,96.54,95.84,94.07,93.5,93.24,96.07,94.06,90.79,89.78,88.41,86.05,87.4999,87.09,84.58,85.06,83.9,84.1,84.9,84.54,86.2,78.97,77.87,76.12,74.535,75.97,75.49,74.6,74.96,73.35,74.1,73.13,73.44,74.42,74.3,73.27,69.84,71.71,71.25,71.1,72.89,71.86,70.22,69.44,69.56};
            //Double[] arrayy = {97.2,93.96,97.46,103.89,104.3,104.58,99.3528,103.91,105.9,100.11,101.5901,106.9468,104.2,110.64,112.2175,111.035,108.36,107.23,108.51,105.78,106.69,107.559,104.76,106.13,103.89,101.1,(double) 98,97.26,99.42,100.07,100.38,97.23,96.32,95.83,95.05,92.45,90.28,93.42,90.62,87.51,88.4,87.08,92.83,92.26,90.86,91.01,87.8,83.83,91.5,89.74,88.0104,87.24,85.5,85.03,84.71,84.12,80.7,83.175,82.25,82.24,82.9,82.88,78.01,77.25,75.86,73.71,72.92,73.85,74.07,72.98,72.05,71.7,71.93,71.28,71.445,72.32,72.66,69.2,68.02,68.09,69.71,68.13,68.59,69.451,67.5,67.14,68.04};
            ArrayList<Float> stockOnePrices = numlist1; //stock1
            ArrayList<Float> stockTwoPrices = numlist2; //stock2





            drawPlotLine(root, stockOnePrices, stockTwoPrices);

        }
        private void drawPlotLine(Group root, ArrayList<Float> stockOnePrices, ArrayList<Float> stockTwoPrices) {
            GraphicsContext gc = canvas.getGraphicsContext2D();

	        /*
	        Parameters:
	        x1 - the X coordinate of the starting point of the line.
	        y1 - the Y coordinate of the starting point of the line.
	        x2 - the X coordinate of the ending point of the line.
	        y2 - the Y coordinate of the ending point of the line.
	         */

            gc.setStroke(Color.BLACK);
            // y-axis line
            gc.strokeLine(50,50,50,900);
            // x-axis line
            gc.strokeLine(50,900,950,900);

            gc.setStroke(Color.RED);
            plotLine(stockOnePrices, gc);
            gc.setStroke(Color.BLUE);
            plotLine(stockTwoPrices, gc);

        }

        private void plotLine(ArrayList<Float> stockPrices, GraphicsContext gc) {

            float baseX = 50;
            float baseY = 650;
            float oldPrice = stockPrices.get(0);
            float newPrice;
            float oldDate = baseX;
            float newDate = baseX;


            for (int i = 1; i < stockPrices.size(); i++) {
                float price = stockPrices.get(i);

                //price / 100 * 50
                newPrice = baseY - ((price / 10) * 50);
                newDate += 10;
                gc.strokeLine(oldDate, oldPrice, newDate, newPrice);
                oldDate = newDate;
                oldPrice = newPrice;
                //System.out.println("newPrice = " + newPrice);
            }



        }

        public static void main(String[] args) {
            launch(args);
        }


    }

