package sample;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Order {

    private String date;
    private String time;
    private VBox layout;
    int count =0;


    Order(){
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
        this.date = date.format(d);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        this.time = time.format(d);
        layout =  new VBox();
    }

    public void deleteOrder(String name ){
        int size = layout.getChildren().size();
        for(int i = 0 ;  i < size; i++) {
            if (name.equals(((Text) layout.getChildren().get(i)).getText())) {
                layout.getChildren().remove(i);
                i = size;
            }
        }
    }

    public void addOrder(String ProductName , String total){
        Text name = new Text(ProductName);
        Text totalPrice = new Text(total);
        HBox pane = new HBox(name , totalPrice);
        pane.setSpacing(200);
        layout.getChildren().add(pane);
        if(count % 2 ==0)
            pane.setStyle("-fx-background-color: #B3B6B7");

        count++;
    }



    public VBox getLayout() {
        return layout;
    }

    public String getTime() {
        return time;
    }


    public String getDate() {
        return date;
    }



}
