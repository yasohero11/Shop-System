package sample;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.LinkedList;

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
               if(!exist(text1.getText())) {
                   Product product = new Product(text1.getText(), Double.parseDouble(text2.getText()));
                   Controller.pane.getChildren().add(product.getLayout());
                   Main.products.add(product);
                   EditController.view.getItems().add(new Text(product.getProductName()));
               }
           }

           reset();
           close();
        });

        close.setOnAction(e->{
            close();
            reset();

        });

    }

    private boolean exist(String name){
        for(int i = 0; i < Main.products.size(); i++)
            if(Main.products.get(i).getProductName().equalsIgnoreCase(name))
                return true;

        return false;
    }

    private void reset(){
        text1.setText("");
        text2.setText("");
    }

    public void setUrl(String url) {
        text2.setText(url);
    }
}
