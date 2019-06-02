package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private VBox layout;

    @FXML
    private ScrollPane scrollPane;

    public static ChoiceBox box;

   private  AddMessage addMessage;
   private EditMessage editMessage;



    public void add(ActionEvent event){
       addMessage.show();
    }
    public void edit(ActionEvent event){
        editMessage.show();
        box.getSelectionModel().select(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        box = new ChoiceBox();
        editMessage = new EditMessage(layout);
        addMessage = new AddMessage(layout );
        scrollPane.setContent(Main.order.getLayout());
    }
}
