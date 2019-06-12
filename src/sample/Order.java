package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class Order {

    private String date;
    private String time;
    private int counter = 0;
    LinkedList<OrderNode> list;

    Order() {
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
        this.date = date.format(d);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        this.time = time.format(d);
        list = new LinkedList<>();
    }
    public void addOrder(String productName, double price, Label count, Label totalPrice){
        OrderNode node = new OrderNode(productName , price ,count , totalPrice);
        list.add(node);
    }
    public String getTime() {
        return time;
    }


    public String getDate() {
        return date;
    }



    class OrderNode {

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
         private String name;
         private String  amount;
         private double price;
         private String total;


         private OrderNode(String name , double price , Label count , Label totalPrice){
             this.name = name;
             this.price = price;
             amount = count.getText();
             total =  totalPrice.getText();
             count.textProperty().addListener(e->amount = count.getText());
             totalPrice.textProperty().addListener(e->total = totalPrice.getText());
             Text productName = new Text(name);
             HBox pane = new HBox(productName);
             HBox HPrice = new HBox(new Text(String.valueOf(price)));
             HBox amount = new HBox(count);
             HBox total = new HBox(totalPrice);
             pane.setPrefHeight(24);
             HPrice.setPrefHeight(24);
             amount.setPrefHeight(24);
             total.setPrefHeight(24);
             HPrice.setAlignment(Pos.CENTER);
             amount.setAlignment(Pos.CENTER);
             total.setAlignment(Pos.CENTER);

             pane.setSpacing(200);
             Controller.nameLayout.getChildren().add(pane);
             Controller.amountLayout.getChildren().add(amount);
             Controller.priceLayout.getChildren().add(HPrice);
             Controller.totalLayout.getChildren().add(total);

             if (counter % 2 == 0) {
                 pane.setStyle("-fx-background-color: #B3B6B7");
                 HPrice.setStyle("-fx-background-color: #B3B6B7");
                 amount.setStyle("-fx-background-color: #B3B6B7");
                 total.setStyle("-fx-background-color: #B3B6B7");
             }

             counter++;

         }

        public String getName() {
            return name;
        }

        public String getAmount() {
            return amount;
        }

        public String getTotal() {
            return total;
        }

        public double getPrice() {
            return price;
        }


    /*
    public int check(String name){
        for( int i = 0;  i < Main.products.size(); i++){
            if(Co)
        }
    }
*/

    }
}
