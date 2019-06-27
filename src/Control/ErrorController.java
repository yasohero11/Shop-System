package Control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable{

    @FXML
    Stage stage;

    @FXML
    private Text text;

    public static Text tempText;





    public void close(ActionEvent event) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tempText = text;
        stage.show();
    }
}
