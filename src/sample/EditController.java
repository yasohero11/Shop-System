package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    public TextField name;

    @FXML
    public TextField price;

    @FXML
    public  ListView listView;

    @FXML
    Button save;


    private String oldName = "";
    private double oldPrice =-1;



    public static ListView view;

    public void onSave(ActionEvent event){
        if(name.getLength() != 0 && price.getLength()!=0){
            Product product = getSelectedProduct();
            product.setProductName(name.getText());

            product.setPrice(Double.parseDouble(price.getText()));
            ((Text)listView.getSelectionModel().getSelectedItem()).setText(name.getText());
            //save.setDisable(true);
            listView.refresh();
        }

    }
    public void onClear(ActionEvent event){

    }
    public void onClose(ActionEvent event){

    }
    public void onChange(InputEvent event){

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        view = listView;
        listView.getSelectionModel().selectedItemProperty().addListener(e->{
            name.setDisable(false);
            price.setDisable(false);
            Product product = getSelectedProduct();
            name.setText(product.getProductName());
            price.setText(String.valueOf(product.getPrice()));
            oldName = product.getProductName();
            oldPrice = Double.parseDouble(price.getText());
            System.out.println(oldName + " " + oldPrice );
        });


        EventHandler event = e->{






        };
/*
        name.textProperty().addListener(e->{
            if(!oldName.equals("") && oldPrice != -1) {
                if (!oldName.equalsIgnoreCase(name.getText())) {
                    save.setDisable(false);
                    System.out.println(oldName +" " + oldPrice);
                }

                if (oldName.equalsIgnoreCase(name.getText()) && oldPrice == Double.parseDouble(price.getText()))
                    save.setDisable(true);


            }

        });

        price.textProperty().addListener(e->{
            if(!oldName.equals("") && oldPrice != -1) {
                if (oldPrice != Double.parseDouble(price.getText()))
                    save.setDisable(false);

                if (oldName.equalsIgnoreCase(name.getText()) && oldPrice == Double.parseDouble(price.getText()))
                    save.setDisable(true);
            }
        });
        */
    }
    private Product getSelectedProduct(){
        int index = listView.getSelectionModel().getSelectedIndex();
        return Main.products.get(index);
    }
}
