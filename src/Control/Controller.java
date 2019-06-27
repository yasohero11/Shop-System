package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.AddMessage;
import sample.EditMessage;
import sample.Main;
import sample.Product;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable  {

    @FXML
    private VBox layout;
    @FXML
    private VBox namePane;
    @FXML
    private VBox pricePane;
    @FXML
    private VBox amountPane;
    @FXML
    private VBox totalPane;
    @FXML
    private TextField search;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane productPane;

   public static ChoiceBox box;
   private AddMessage addMessage;
   private EditMessage editMessage;
   public static VBox pane;
   public static VBox orderPane;
   public static VBox priceLayout;
   public static VBox nameLayout;
   public static VBox amountLayout;
   public static VBox totalLayout;
   private BorderPane borderPane;

   private int count = 0;







    public void add(ActionEvent event){
       addMessage.show();
    }
    public void buy(ActionEvent event){

    }
    public void edit(ActionEvent event){
         editMessage.show();
       // box.getSelectionModel().select(0);
    }

    public void onBuy(ActionEvent event){
        if(Main.orders.size() != 0) {
            Main.orders.getLast().setFinished(true);
            clearOrderPane();
            Main.products.resetAllProduct();
        }
    }

    public void onDelete(ActionEvent event) {
        if(count % 2 == 0) {
            scrollPane.setContent(DeleteController.temPBorderPane);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setStyle("-fx-background-color:#797D7F");
        }
        else {
            scrollPane.setContent(layout);
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setStyle("-fx-background-color:#A6ACAF");
        }

        count++;

    }
    public static void clearOrderPane(){
        nameLayout.getChildren().clear();
        amountLayout.getChildren().clear();
        priceLayout.getChildren().clear();
        totalLayout.getChildren().clear();
        if(!Main.orders.isEmpty()&& !Main.orders.getLast().isFinished())
            Main.orders.remove(Main.orders.size()-1);
    }

    public void resetAllProductes(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {
            borderPane = FXMLLoader.load(getClass().getResource("../View/FXMLdelete.fxml"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        box = new ChoiceBox();
        pane = layout;
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

        search.textProperty().addListener(e->{
            pane.getChildren().clear();
            DeleteController.tempProductPane.getChildren().clear();
            DeleteController.buttonP.getChildren().clear();
            if(search.getLength() != 0){
                for(int i  = 0 ;  i < Main.products.getSize();i++) {
                    Product product = Main.products.getProduct(i);
                    if (product.getProductName().startsWith(search.getText())) {
                        pane.getChildren().add(product.getLayout());
                        DeleteController.addToDeletePane(product);
                    }
                }

            }else
                for(int i  = 0 ;  i < Main.products.getSize();i++) {
                    layout.getChildren().add(Main.products.getProduct(i).getLayout());
                    DeleteController.addToDeletePane(Main.products.getProduct(i));
                }


        });
    }


}
