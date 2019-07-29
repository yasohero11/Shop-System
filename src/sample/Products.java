package sample;

import Control.Controller;
import Control.DeleteController;
import Control.EditController;

import javafx.scene.text.Text;

import java.util.LinkedList;

public class Products extends LinkedList<Product>{




    public  void addProduct(String name ,  double price){
        Product product = new Product(name , price);
        DeleteController.addToDeletePane(product);
        Controller.pane.getChildren().add(product.getLayout());
        EditController.view.getItems().add(new Text(product.getProductName()));
        add(product);
    }

    public void removeProduct(String productName){
        for(int i  = 0;  i < size(); i++){
            if(productName.equals(get(i).getProductName())){
                remove(i);
               // Controller.clearOrderPane();
                Controller.pane.getChildren().remove(i);
                EditController.view.getItems().remove(i);
                DeleteController.tempProductPane.getChildren().remove(i);

                i = size();
            }
        }
    }

    public boolean exist(String name){
        if(!isEmpty())
            if(getProduct(name) != null)
                return true;

        return false;
    }
    public boolean exist(String name , int index){
        for (int i = 0; i< size();i++)
            if(i != index)
                if(get(i).getProductName().equalsIgnoreCase(name))
                    return true;

        return false;
    }


    public Product getProduct(String name){
        for(int i  = 0;  i < size(); i++)
            if(name.equals(get(i).getProductName()))
                return get(i);

            return null;
    }
    public Product getProduct(int index){
        if(!isEmpty())
            return get(index);

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
        for(int i  = 0;  i < size(); i++)
            get(i).reset();
    }

    public void clearProducts(){
        clear();
        DeleteController.tempProductPane.getChildren().clear();
        Controller.pane.getChildren().clear();
        EditController.view.getItems().clear();
        Main.orders.getLast().clearOrder();
    }






}
