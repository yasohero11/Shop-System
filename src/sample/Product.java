package sample;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
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
    Label totalPrice;
    private boolean layoutExist =  false;

    Product(String name , double price ){
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
        this.date = date.format(d);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        this.time = time.format(d);
        this.name = name;
        text = new Label(name);
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
        add.setOnAction(e->{
            if(amount != 1000){
                amount++;
                count.setText(String.valueOf(amount));
                totalPrice.setText(String.valueOf(price * amount));
                if(amount==1)
                Main.order.addOrder(getProductName() , getTotalPrice());
            }
        });
        sub.setOnAction(e->{
            if(amount != 0){
                amount--;
                count.setText(String.valueOf(amount));
                totalPrice.setText(String.valueOf(price * amount));
                if(amount == 0) {

                }
            }
        });
        this.name =  name;
        this.price = price;
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

    public Label getCount() {
        return count;
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


}
