package sample;

import Control.Controller;
import Control.DeleteController;
import Control.ErrorController;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;

;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private String date;
    private String time;
    private Pane layout;
    private Label count;
    private Label orderCount;
    private int amount = 0;
    private JFXButton add;
    private JFXButton sub;
    private Label text;
    Label totalPrice;
    private boolean layoutExist =  false;
    public JFXButton deleteButton;

    Product(String name , double price ){
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
        this.date = date.format(d);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        this.time = time.format(d);
        this.name = name;
        this.price = price;
        text = new Label(name);
        deleteButton = new JFXButton("X");
        deleteButton.setAlignment(Pos.TOP_CENTER);
        orderCount = new Label("0");
        layout = new Pane();
        count = new Label("0");
        totalPrice = new Label("0");
        add = new JFXButton("+");
        sub = new JFXButton("-");
        sub.setPrefWidth(30);
        add.setPrefWidth(30);
        layout.setPrefSize(523 , 25);
        setLayout(text , 0 , 0);
        setLayout(add , 400 , 0);
        setLayout(count ,  450 , 3);
        setLayout(sub , 480 , 0);
        layout.getChildren().addAll(text ,add , count , sub);
        layout.setStyle("-fx-background-color : white");

        add.setOnMouseClicked(e->{
            if(amount != 1000){
                amount++;
                refresh();
                if(!Main.orders.isEmpty()){
                    if(Main.orders.getLast().isFinished()) {
                        Order order = new Order();
                        Main.orders.add(order);
                    }
                }else
                {
                    Order order = new Order();
                    Main.orders.add(order);
                }
                if(amount==1)
                Main.orders.getLast().addOrder(getProductName(),this.price ,orderCount , getTotalPrice());
            }
        });
        sub.setOnAction(e->{
            if(amount != 0){
                amount--;
                refresh();
                if(amount == 0) {
                 Main.orders.getLast().deleteOrder(this.name);
                 if(Controller.nameLayout.getChildren().size() == 0 && !Main.orders.getLast().isFinished())
                     Main.orders.remove(Main.orders.size()-1);
                }
            }
        });
        deleteButton.setOnAction(e->{
         Main.products.removeProduct(this.name);
         Main.products.resetAllProduct();
        });

        /*
        add.pressedProperty().addListener(e->System.out.println("Asas"));
        this.name =  name;
        this.price = price;
        */
    }

    private void refresh(){
        count.setText(String.valueOf(amount));
        orderCount.setText(String.valueOf(amount));
        totalPrice.setText(String.valueOf(this.price * amount));
    }

    public void reset(){
        amount = 0;
        refresh();
    }

    public static boolean exist(String name){
        if(Main.products.getSize() !=0)
            if(Main.products.getProduct(name) != null)
                return true;

        return false;
    }

    public Label getTotalPrice() {
        return  totalPrice;
    }


    private void setLayout(Labeled button , double x , double y){
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    public Pane getLayout() {
        return layout;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return name;
    }
    public void setProductName(String newName){

        name =  newName;
        System.out.println(newName + " " + name);
        text.setText(name);
        amount=0;
        count.setText("0");
    }

    public void setPrice(double price) {
        this.price = price;

        amount=0;
        count.setText("0");
    }

    public String getTime() {
        return time;
    }


    public String getDate() {
        return date;
    }


    @Override
    public int compareTo(Product o) {
        if(o.getProductName().equals(name) && o.getPrice() == price)
            return 0;
        else
            return 1;
    }
}
