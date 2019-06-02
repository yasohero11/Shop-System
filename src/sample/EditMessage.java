package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EditMessage  extends PopUpMessage{

    private GridPane pane;
    protected TextField name;
    private Label nameLabel;
    private Button clear;
    private Label priceLabel;
    private TextField price;
    public EditMessage(VBox layout) {
        priceLabel = new Label("Price:");
        price = new TextField();
//        setIcon(new Image("images/edit.png"));
        pane = new GridPane();
        pane.setHgap(20);
        clear = new Button("Clear");
        name = new TextField();
        nameLabel = new Label("New Name :");
        name.setPrefSize(220 , 20);
        setCenter(pane);
        setBottom(clear);
        pane.setVgap(10);
        pane.setPadding(new Insets(0,0,0,30));
        pane.add(nameLabel, 0, 0);
        pane.add(name, 1, 0 , 2 ,1);
        pane.add(priceLabel ,0 , 1);
        pane.add(price , 1 , 1 );
        pane.add(Controller.box, 3 ,2  , 2 , 1);
        Controller.box.getSelectionModel().select(0);
        Controller.box.setPrefSize(100, 20);


        save.setOnAction(e->{
            if(name.getLength()!=0 && price.getLength()!=0){
                for(int i  = 0 ; i < Main.products.size(); i++){
                    if(Controller.box.getValue().equals(Main.products.get(i).getProductName())){
                        Main.products.get(i).setProductName(name.getText());
                        Main.products.get(i).setPrice(Double.parseDouble(price.getText()));
                        Controller.box.getItems().set(i , name.getText());
                        name.setText("");
                        price.setText("");
                        close();
                        i  = Main.products.size();
                    }
                }
            }
        });

        clear.setOnAction(e->{
            Controller.box.getItems().clear();
            Main.products.clear();
            layout.getChildren().clear();
            close();
        });
        close.setOnAction(e->{
            name.setText("");
            price.setText("");
            close();
        });


    }

}

