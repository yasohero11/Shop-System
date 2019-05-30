package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;




public class Controller {

    @FXML
    VBox layout;

    @FXML
    Button add;

    public void add(ActionEvent event){
        Product product= new Product("name" , 50);
        layout.getChildren().add(product.getLayout());
    }
}
