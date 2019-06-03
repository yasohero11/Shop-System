package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditMessage  extends PopUpMessage{

    private GridPane pane;
    protected TextField name;
    private Label nameLabel;
    private Button clear;
    private Label priceLabel;
    private TextField price;
    private Stage frame;
    public EditMessage()throws Exception{
        frame = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLedit.fxml"));
        frame.setScene(new Scene(root, 800, 550));
    }

    public void show(){
        frame.show();
    }



}

