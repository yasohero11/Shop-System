package sample;

import Control.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class Order {

    private String date;
    private String time;
    private int counter = 0;
    private boolean finished = false;
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
        System.out.println(counter);
    }

    public boolean isFinished() {
        return finished;
    }
    public void setFinished(Boolean value){
        finished = value;
    }

    public void deleteOrder(String name){
                VBox layout = Controller.nameLayout;
                for(int i = 0 ;  i < layout.getChildren().size(); i++) {
                   // System.out.println(((Text)((HBox)layout.getChildren().get(i)).getChildren().get(0)).getText()+"  " +name);
                    if (name.equals(((Text)((HBox)layout.getChildren().get(i)).getChildren().get(0)).getText())) {
                        Controller.nameLayout.getChildren().remove(i);
                        Controller.amountLayout.getChildren().remove(i);
                        Controller.priceLayout.getChildren().remove(i);
                        Controller.totalLayout.getChildren().remove(i);
                        counter--;
                        break;
                    }
                }
            }



    public String getTime() {
        return time;
    }


    public String getDate() {
        return date;
    }



    class OrderNode {

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
