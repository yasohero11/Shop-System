package sample;

import Control.Controller;
import Control.DeleteController;
import Control.EditController;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.util.LinkedList;

public class Products {

    private static LinkedList<Product> products =  new LinkedList<>();


    public static void addProduct(String name ,  double price){
        Product product = new Product(name , price);
        DeleteController.addToDeletePane(product);
        Controller.pane.getChildren().add(product.getLayout());
        EditController.view.getItems().add(new Text(product.getProductName()));
        products.add(product);
    }

    public void removeProduct(String productName){
        for(int i  = 0;  i < products.size(); i++){
            if(productName.equals(products.get(i).getProductName())){
                products.remove(i);
                Controller.clearOrderPane();
                Controller.pane.getChildren().remove(i);
                EditController.view.getItems().remove(i);
                DeleteController.tempProductPane.getChildren().remove(i);
                DeleteController.buttonP.getChildren().remove(i);
                i = products.size();
            }
        }
    }
    public int getSize(){
        return products.size();
    }

    public Product getProduct(String name){
        for(int i  = 0;  i < products.size(); i++)
            if(name.equals(products.get(i).getProductName()))
                return products.get(i);

            return null;
    }
    public Product getProduct(int index){
        if(!products.isEmpty())
            return products.get(index);

        return null;
    }

    /*
    public void changeData(String productName ,  String newName ,  double price){
        getProduct(productName).setProductName(newName);
        getProduct(productName).setPrice(price);
    }

    */
    public void resetProduct(String name){
      getProduct(name).reset();
    }
    public void resetAllProduct(){
        for(int i  = 0;  i < products.size(); i++)
            products.get(i).reset();
    }

    public void clearProducts(){
        products.clear();
        DeleteController.tempProductPane.getChildren().clear();
        DeleteController.buttonP.getChildren().clear();
        Controller.pane.getChildren().clear();
        EditController.view.getItems().clear();
        Controller.clearOrderPane();
    }






}
