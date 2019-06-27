package sample;

import Control.EditController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditMessage  {

    public EditMessage()throws Exception{

        Stage root = FXMLLoader.load(getClass().getResource("../View/FXMLedit.fxml"));

    }

    public void show(){
        EditController.show();
    }



}

