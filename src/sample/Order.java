package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Order {

    private String date;
    private String time;
    int count = 0;


    Order(){
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
        this.date = date.format(d);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        this.time = time.format(d);

    }
/*
    public void deleteOrder(String name ){
        int size = layout.getChildren().size();
        for(int i = 0 ;  i < size; i++) {
            if (name.equals(((Text) layout.getChildren().get(i)).getText())) {
                layout.getChildren().remove(i);

                i = size;
            }
        }
    }
    */

    public void addOrder(String ProductName , Label totalPrice){
        Text name = new Text(ProductName);
        HBox pane = new HBox(name , totalPrice);
        pane.setSpacing(200);
        Controller.orderPane.getChildren().add(pane);
        if(count % 2 ==0)
            pane.setStyle("-fx-background-color: #B3B6B7");

        count++;
    }
    /*
    public int check(String name){
        for( int i = 0;  i < Main.products.size(); i++){
            if(Co)
        }
    }
*/


    public String getTime() {
        return time;
    }


    public String getDate() {
        return date;
    }



}
