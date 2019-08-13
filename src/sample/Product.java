package sample;

import Control.Controller;
import Control.DeleteController;
import Control.ErrorController;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.omg.CORBA.MARSHAL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private String date;
    private String time;
    private Pane layout;
    public  Label count;

    private int amount = 0;
    private JFXButton add;
    private JFXButton sub;
    private Label text;


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
        deleteButton = new JFXButton("");
        deleteButton.setLayoutX(470);
        deleteButton.setLayoutY(-3);

        Image image = new Image("images/close.png" );

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(15);
        imageView.setFitHeight(20);
        deleteButton.setPrefHeight(37);

        deleteButton.setGraphic(imageView);

        layout = new Pane();
        count = new Label("0");
        add = new JFXButton("+");
        sub = new JFXButton("-");
        sub.setPrefSize(30 , 30);
        add.setPrefSize(30,30);
        layout.setPrefSize(523 , 30);
        setLayout(text , 0 , layout.getPrefHeight()/5);
        setLayout(add , 390 , -3);
        setLayout(count ,  440 , 3);
        setLayout(sub , 470 , -3);
        layout.getChildren().addAll(text ,add , count , sub);
        layout.setStyle("-fx-background-color : gray");
        add.setId("addButton");
        sub.setId("subButton");
        add.setOnAction(e->{
            if(amount != 10000) {
                amount++;
                count.setText(String.valueOf(amount));
            }
        });

        // property

        count.textProperty().addListener(e->{
            Order order = Main.getUnFinshedOrder();
            if(order == null && !count.getText().equals("0")){
                Date date1 = new Date();
                order = new Order(new SimpleDateFormat("MM/y").format(date1),
                        new SimpleDateFormat("MM/dd/y").format(date1),new SimpleDateFormat("hh:mm:ss a").format(date1));
                Main.orders.add(order);

            }

            if(amount==1)
                order.addOrder(getProductName(), this.price, count);


            if(amount == 0 && order != null) {
                order.deleteOrder(this.name);
                if(order.list.isEmpty()) {
                    order.clearOrder();
                }

            }

        });


        sub.setOnAction(e->{
            if(amount != 0){
                amount--;
                count.setText(String.valueOf(amount));
            }
        });
        deleteButton.setOnAction(e->{
            Main.sync(this);
            Controller.products.removeProduct(this.name);
            Controller.products.writeAll();
        });

        /*
        add.pressedProperty().addListener(e->System.out.println("Asas"));
        this.name =  name;
        this.price = price;
        */
    }


    public void setCount(String count) {
        amount=1;
        this.count.setText(String.valueOf(amount));
        amount=Integer.parseInt(count);
        this.count.setText(count);
    }

    public void reset(){
        amount = 0;
        count.setText("0");
    }



    public double getTotalPrice() {
        return  amount*price;
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
        text.setText(name);
        reset();
    }

    public void setPrice(double price) {
        this.price = price;
        reset();
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
