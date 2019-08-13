package sample;


import Control.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMessage {

    private VBox vBox;
    private Label price;
    private Label name;
    private TextField text1;
    private TextField text2;
    private BorderPane layout ;
    private Stage frame;
    private Button save ;
    private Button close ;
    private HBox bottomLayout ;


    public AddMessage(){
        layout = new BorderPane();
        bottomLayout = new HBox();
        frame = new Stage();
        frame.setTitle("Pop Up Message");
        frame.initModality(Modality.APPLICATION_MODAL);
        frame.setResizable(false);
        frame.getIcons().add(new Image("images/add.png"));
        save = new Button("Save");
        close = new Button("Close");
        bottomLayout.getChildren().addAll(save, close);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setSpacing(20);
        layout.setBottom(bottomLayout);
        frame.setScene(new Scene(layout , 500, 300));
        price = new Label("Price:");
        name = new Label("Name:");
        text1 = new TextField();
        text2 = new TextField();
        vBox = new VBox();
        vBox.getChildren().addAll(name , text1 , price , text2);
        layout.setCenter(vBox);
        save.setOnAction(e->{
            if(text1.getLength() !=0 && text2.getLength() !=0)
                try {
                    Integer.parseInt(text2.getText());
                    if(Controller.products.getProduct(text1.getText())== null) {
                        Controller.products.addProduct(text1.getText(), Double.parseDouble(text2.getText()));
                        frame.close();
                    }
                    else
                        new ErrorMessage("The name you entered is already taken");
                }catch (Exception ex){
                    new ErrorMessage("Please Enter A Number");
                }


            reset();

        });




        close.setOnAction(e->{frame.close();reset();});

    }


    public void show(){frame.show();}

    private void reset(){
        text1.setText("");
        text2.setText("");
    }





}
