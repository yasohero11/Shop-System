package Control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {
    @FXML
    public  BorderPane borderPane;
    @FXML
    public  VBox productPane;
    @FXML
    public  VBox buttonPane;


    public static VBox tempProductPane;
    public static BorderPane temPBorderPane;
    public static VBox buttonP;

    public static void addToDeletePane(Product product){
        FlowPane flowPane = new FlowPane(new Text(product.getProductName()));
        flowPane.setStyle("-fx-background-color: white");
        flowPane.setPrefHeight(25);
        DeleteController.buttonP.getChildren().add(product.deleteButton);
        tempProductPane.getChildren().add(flowPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        temPBorderPane = borderPane;
        tempProductPane= productPane;
        buttonP =  buttonPane;
    }

}
