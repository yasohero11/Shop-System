package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable  {

    @FXML
    private VBox layout;
    @FXML
    private VBox orderLayout;
    @FXML
    private VBox namePane;
    @FXML
    private VBox pricePane;
    @FXML
    private VBox amountPane;
    @FXML
    private VBox totalPane;

    public static ChoiceBox box;
   private  AddMessage addMessage;
   private EditMessage editMessage;
   public static VBox pane;
   public static VBox orderPane;
   public static VBox priceLayout;
   public static VBox nameLayout;
   public static VBox amountLayout;
   public static VBox totalLayout;







    public void add(ActionEvent event){
       addMessage.show();
    }
    public void buy(ActionEvent event){

    }
    public void edit(ActionEvent event){
        editMessage.show();
       // box.getSelectionModel().select(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        box = new ChoiceBox();
        pane = layout;
        orderPane = orderLayout;
        nameLayout = namePane;
        priceLayout = pricePane;
        amountLayout = amountPane;
        totalLayout = totalPane;
        try {
            editMessage = new EditMessage();
        }
        catch(Exception e){
            System.out.println("err");
        }
        addMessage = new AddMessage();
    }
}
