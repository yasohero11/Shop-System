package Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.*;


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
    @FXML
    private Stage stage;
    @FXML
    private TextField search;

    private static Stage tempStage ;
    private Product product;
    public static ListView view;

    public void onSave(ActionEvent event){
        if(name.getLength() != 0 && price.getLength()!=0){
            if(!Controller.products.exist(name.getText() , view.getSelectionModel().getSelectedIndex())) {
                Main.sync(product);
                product.setProductName(name.getText());
                // Main.products.resetAllProduct();
                product.setPrice(Double.parseDouble(price.getText()));
                ((Text) listView.getSelectionModel().getSelectedItem()).setText(name.getText());

                ((Text)((Pane)DeleteController.tempProductPane.getChildren().get(
                        listView.getSelectionModel().getSelectedIndex())).getChildren().get(0)).setText(name.getText());

                save.setDisable(true);
                listView.refresh();
                Controller.products.writeAll();
            }
            else
                new ErrorMessage("The name you entered is already taken");
        }

    }
    public void onClear(ActionEvent event){
        Controller.products.clearProducts();

        for(Order order : Main.orders)
            order.setEditable(false);

        if(Main.getUnFinshedOrder() != null)
            Main.getUnFinshedOrder().clearOrder();

        name.setDisable(true);
        price.setDisable(true);
    }
    public void onClose(ActionEvent event){ stage.close(); }

    public void onChange(InputEvent event){

    }
    public static void show(){
        tempStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tempStage =  stage;
        view = listView;
        listView.getSelectionModel().selectedItemProperty().addListener(e->{
            name.setDisable(false);
            price.setDisable(false);
            product = getSelectedProduct();
            if(product != null) {
                save.setDisable(true);
                name.setText(product.getProductName());
                price.setText(String.valueOf(product.getPrice()));

            }
            // System.out.println(product.getProductName() + " " + product.getPrice() );
        });


        EventHandler event = e->{






        };

        name.textProperty().addListener(e->{
            if (!product.getProductName().equalsIgnoreCase(name.getText()))
                save.setDisable(false);

            if(price.getLength()!=0)
                if (product.getProductName().equalsIgnoreCase(name.getText())
                        && product.getPrice() == Double.parseDouble(price.getText()))
                    save.setDisable(true);

        });

        price.textProperty().addListener(e->{
            if(price.getLength()!=0) {
                if (product.getPrice() != Double.parseDouble(price.getText()))
                    save.setDisable(false);


                if (getSelectedProduct().getProductName().equalsIgnoreCase(name.getText())
                        && product.getPrice() == Double.parseDouble(price.getText()))
                    save.setDisable(true);
            }
        });

        search.textProperty().addListener(e->{
            listView.getItems().clear();
            if(search.getLength() != 0){
                for(int i = 0; i < Controller.products.size(); i++) {
                    Product product = Controller.products.getProduct(i);
                    if (product.getProductName().startsWith(search.getText()))
                        listView.getItems().add(new Text(product.getProductName()));

                }

            }
            else
                for(int i = 0; i < Controller.products.size(); i++) {
                    // System.out.println(Main.products.getSize() + " " +Main.products.getProduct(i).getProductName());

                    listView.getItems().add(new Text(Controller.products.getProduct(i).getProductName()));
                }



        });

    }
    private Product getSelectedProduct(){
        if(listView.getItems().size() != 0)
            return Controller.products.getProduct(((Text)listView.getSelectionModel().getSelectedItem()).getText());

        return null;
    }
}
