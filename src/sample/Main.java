package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Main extends Application {
    public static LinkedList<Product> products  = new LinkedList<>();
    public static Order order = new Order();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1300, 795));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
