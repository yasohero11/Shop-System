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

    private int amount = 0;
    private JFXButton add;
    private JFXButton sub;
    private Label text;

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
        deleteButton = new JFXButton("");
        deleteButton.setLayoutX(475);
        deleteButton.setLayoutY(1);

        Image image = new Image("images/delete.png" );
        ImageView imageView = new ImageView(image );
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        deleteButton.setGraphic(imageView);

        layout = new Pane();
        count = new Label("0");
        add = new JFXButton("+");
        sub = new JFXButton("-");
        sub.setPrefSize(30 , 30);
        add.setPrefSize(30,30);
        layout.setPrefSize(523 , 30);
        setLayout(text , 0 , 0);
        setLayout(add , 398 , 0);
        setLayout(count ,  448 , 3);
        setLayout(sub , 478 , 0);
        layout.getChildren().addAll(text ,add , count , sub);
        layout.setStyle("-fx-background-color : white");

        add.setOnMouseClicked(e->{
            if(amount != 1000){
                amount++;
                count.setText(String.valueOf(amount));
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
                Main.orders.getLast().addOrder(getProductName(),this.price ,count );
            }
        });
        sub.setOnAction(e->{
            if(amount != 0){
                amount--;
                count.setText(String.valueOf(amount));
                if(amount == 0) {
                  Main.orders.getLast().deleteOrder(this.name);
                 if(Main.orders.getLast().list.isEmpty())
                     Main.orders.remove(Main.orders.size()-1);
                }
            }
        });
        deleteButton.setOnAction(e->{
         Main.products.removeProduct(this.name);
         Main.products.resetAllProduct();
         if(!Main.orders.isEmpty())
         Main.orders.getLast().clearOrder();
        });

        /*
        add.pressedProperty().addListener(e->System.out.println("Asas"));
        this.name =  name;
        this.price = price;
        */
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
