package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    public TextField name;

    @FXML
    public TextField price;

    @FXML
    public  ListView listView;

    public static ListView view;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view = listView;
    }
}
