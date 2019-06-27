package sample;

import Control.ErrorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Main extends Application {
    public static Products products = new Products();
    public static LinkedList<Order> orders = new LinkedList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/FXML.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1300, 795);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
