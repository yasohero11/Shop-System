package sample;


import Control.Controller;
import Control.DeleteController;
import Control.EditController;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddMessage extends PopUpMessage {

    private VBox layout;
    private Label price;
    private Label name;
    public TextField text1;
    public TextField text2;

    public AddMessage(){

        price = new Label("Price:");
        name = new Label("Name:");
        text1 = new TextField();
        text2 = new TextField();
        layout = new VBox();
        layout.getChildren().addAll(name , text1 , price , text2);
        setCenter(layout);
        save.setOnAction(e->{
           if(text1.getLength() !=0 && text2.getLength() !=0){
               if(!Product.exist(text1.getText())) {
                 Products.addProduct(text1.getText(), Double.parseDouble(text2.getText()));

                     System.out.println(Main.products.getSize());

                   close();
               }
               else
                   new ErrorMessage("The name you entered is already taken");
           }
           reset();

        });

        close.setOnAction(e->{
            close();
            reset();

        });

    }



    private void reset(){
        text1.setText("");
        text2.setText("");
    }

    public void setUrl(String url) {
        text2.setText(url);
    }
}
