package sample;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.xml.soap.Node;
import javax.xml.soap.Text;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private String name;
    private double price;
    private String date;
    private String time;
    private Pane layout;
    private Label count;
    private Label text;
    private int amount = 0;
    private JFXButton add;
    private JFXButton sub;

    Product(String name , double price){
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/y");
        this.date = date.format(d);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
        this.time = time.format(d);
        layout = new Pane();
        text = new Label(name);
        count = new Label("0");
        add = new JFXButton("+");
        sub = new JFXButton("-");
        sub.setPrefWidth(30);
        add.setPrefWidth(30);
        layout.setPrefSize(523 , 25);
        setLayout(add , 400 , 0);
        setLayout(count ,  450 , 0);
        setLayout(sub , 480 , 0);
        setLayout(text , 0 , 2);
        layout.getChildren().addAll(text ,  add , count , sub);
        layout.setStyle("-fx-background-color : white");
        add.setOnAction(e->{
            if(amount != 1000){
                amount++;
                count.setText(String.valueOf(amount));
            }
        });
        sub.setOnAction(e->{
            if(amount != 0){
                amount--;
                count.setText(String.valueOf(amount));
            }
        });
        this.name =  name;
        this.price = price;
    }


    public void setLayout(Labeled button , double x , double y){
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
    public Pane getLayout() {
        return layout;
    }

    public Label getText() {
        return text;
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

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }


    public String getDate() {
        return date;
    }


}
