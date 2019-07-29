package Control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Main;
import sample.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

    @FXML
    public  VBox productPane;

    public static VBox tempProductPane;

    public static void addToDeletePane(Product product){
        Text name =new Text(product.getProductName());
       // name.setFont(new Font(17));
        Pane flowPane = new Pane(name, product.deleteButton);
        flowPane.setStyle("-fx-background-color: white");
        flowPane.setPrefHeight(30);
        name.setLayoutY(flowPane.getPrefHeight()/2);

        product.deleteButton.setAlignment(Pos.CENTER_RIGHT);
        tempProductPane.getChildren().add(flowPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tempProductPane= productPane;

    }

}
