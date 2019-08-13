package Control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

    @FXML
    public  VBox productPane;

    public static VBox tempProductPane;

    public static void addToDeletePane(Product product){
        Text name =new Text(product.getProductName());
        Pane flowPane = new Pane(name, product.deleteButton);
        flowPane.setStyle("-fx-background-color: gray");
        flowPane.setPrefSize(523 , 30);
        name.setLayoutY(flowPane.getPrefHeight()/1.5);
        product.deleteButton.setAlignment(Pos.CENTER_RIGHT);
        tempProductPane.getChildren().add(flowPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tempProductPane= productPane;
        tempProductPane.setSpacing(14);
        tempProductPane.setPadding(new Insets(10,0,0,0));

    }

}
