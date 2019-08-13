package sample;

import Control.Controller;
import Control.DeleteController;
import Control.EditController;

import javafx.scene.text.Text;

import java.io.*;
import java.util.LinkedList;

public class Products extends LinkedList<Product>{

    private PrintWriter writer;
    private BufferedReader reader;
    private boolean doneReading = false;
    public Products(){
        try {
            File file = new File("products.txt");
            if (file.exists()) {
                reader = new BufferedReader(new FileReader(file));


                String line = reader.readLine();

                while (line != null) {

                    addProduct(line.substring(0, line.indexOf('$')), Double.parseDouble(line.substring(line.indexOf('$') + 1)));
                    line = reader.readLine();
                }
                reader.close();
            }
            doneReading = true;
            writer = new PrintWriter("products.txt");
            writeAll();

        }
        catch (Exception e){
            System.out.print(e + " products");
        }


    }

    public void writeAll(){
        try {
            writer = new PrintWriter("products.txt");
            for(Product product : this)
                writer.println(product.getProductName() + "$" + product.getPrice());
            writer.flush();
        }
        catch (IOException e){
            System.out.println(e + "from products class");
        }

    }




    public  void addProduct(String name ,  double price){

        if(doneReading) {
            writer.println(name + "$" + price);
            writer.flush();
        }

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

    public Product exist(String name){
        if(!isEmpty())
            return getProduct(name);

        return null;
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
    }






}
