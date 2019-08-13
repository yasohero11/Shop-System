package Control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.ErrorMessage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class OpeningScreenControl implements Initializable {
    @FXML
    private ImageView icon;
    @FXML
    private TextField nameField;
    @FXML
    private Stage stage;
    @FXML
    private HBox hBox;

    private double x = 0;
    private double y = 0;
    private ImageView logo;
    private FileChooser fileChooser;
    private Stage fileChooserStage;
    private File imageFile;
    private static SimpleBooleanProperty isDone;


    public void onSave(ActionEvent event) throws IOException {
        if(nameField.getLength() != 0){
            stage.close();
            File file = new File("setup");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(nameField.getText());
            System.out.println(imageFile);
            if(imageFile!=null)
                printWriter.println(imageFile.getAbsolutePath());
            else
                printWriter.println("images/icon.png");

            printWriter.close();
            isDone.set(true);

        }
        else
            new ErrorMessage("Please Make Sure That You Entered The name Of The Company");
    }
    public void onClose(ActionEvent event){
        stage.close();
        System.exit(0);
    }
    public void onMini(ActionEvent event){
        stage.setIconified(true);
    }

    public void onChoose(ActionEvent event) throws Exception{
        imageFile = fileChooser.showOpenDialog(fileChooserStage);

        if (imageFile != null)
            icon.setImage(new Image(new FileInputStream(imageFile.getAbsolutePath())));

    }

    public static BooleanProperty onDoneProperty() {
        return isDone;
    }

    /*
        private void copyImage(File file) throws IOException{
            FileInputStream in = new FileInputStream(file.getAbsolutePath());
            String name = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\"));

            FileOutputStream ou = new FileOutputStream("C:\\Users\\tazos\\Desktop\\"+name);
            BufferedInputStream bin = new BufferedInputStream(in);
            BufferedOutputStream bou = new BufferedOutputStream(ou);
            int b=1;
            while (b != -1){
                b = bin.read();
                bou.write(b);
            }

        }
        */
    @Override
    public void initialize(URL location, ResourceBundle resources){

        fileChooserStage = new Stage();
        fileChooser = new FileChooser();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        isDone = new SimpleBooleanProperty(false);


        logo = new ImageView();

        hBox.setOnMousePressed(e->{
            x = e.getSceneX();
            y = e.getSceneY();
        });
        hBox.setOnMouseDragged(e->{

            stage.setX(e.getScreenX()-x);
            stage.setY(e.getScreenY()-y);
        });
    }


}
