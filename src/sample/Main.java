package sample;

import Control.Controller;
import Control.OpeningScreenControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.*;

import java.util.LinkedList;


public class Main extends Application {

    public static LinkedList<Order> orders = new LinkedList<>();
    public static BufferedReader reader;

    public static Order getUnFinshedOrder(){
        for(Order order : Main.orders)
            if (!order.isFinished())
                return order;


        return  null;
    }



    public static void read(){

        try
        {
            String line =reader.readLine();

            while (line != null){
                // System.out.println(line);
                int first$ =  line.indexOf("$")+1;

                int second$ = line.indexOf("$" ,first$)+1;

                Order order = new Order(line.substring(0,first$-1),line.substring(first$ , second$-1),line.substring(second$));
                order.setFinished(true);
                order.setEditable(true);
                orders.add(order);
                line = reader.readLine();
                while (!line.equals("!")) {
                    first$ = line.indexOf("$") + 1;
                    second$ = line.indexOf("$", first$) + 1;
                    String name = line.substring(0, first$ - 1);
                    double price = Double.parseDouble(line.substring(first$, second$ - 1));
                    Product product =Controller.products.getProduct(name);
                    order.addOrder(name, price, (product == null? new Label(""):product.count));
                    order.list.get(order.list.size() - 1).setAmount(line.substring(second$));
                    line = reader.readLine();
                    if(!order.edit.isDisable()) {

                        if(product==null || product.getPrice() != price)
                            order.setEditable(false);


                    }

                }
                line = reader.readLine();

            }


        }catch (Exception e){
            System.out.println(e + " Main");
        }

        Controller.tableView.setItems(null);
        Controller.dataLayout.getChildren().clear();
    }

    public static void sync(Product product){
        for (Order order : orders)
            if(order.exists(product.getProductName()) && order.isFinished())
                order.setEditable(false);

        if(orders.getLast().exists(product.getProductName()) && !orders.getLast().isFinished()) {
            orders.getLast().clearOrder();
            Controller.products.resetAllProduct();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        File file = new File("orders.txt");
        //Scanner s = new Scanner(file);
        if (file.exists())
            reader = new BufferedReader(new FileReader(file));
        if(!new File("setup").exists()) {
            Stage stage = FXMLLoader.load(getClass().getResource("../View/openingScreen.fxml"));
            stage.show();
            OpeningScreenControl.onDoneProperty().addListener(e -> {
                createStage(primaryStage);
            });
        }
        else
            createStage(primaryStage);


    }

    private void createStage(Stage primaryStage){
        try {

            BufferedReader reader = new BufferedReader(new FileReader("setup"));
            String name = reader.readLine();
            String imagePath = reader.readLine();

            Parent root = FXMLLoader.load(getClass().getResource("../View/FXML.fxml"));
            ImageView image = null ;
                 if(!imagePath.contains("images/"))
                 image = new ImageView(new Image(new FileInputStream(imagePath)));
                 else
                     image = new ImageView(new Image("images/icon.png"));

            Controller.companyLogo.setImage(image.getImage());
            primaryStage.setTitle(name);

            Controller.companyName.setText(name);
            Scene scene = new Scene(root, 1310, 800);
            primaryStage.getIcons().add(image.getImage());
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setResizable(false);
            primaryStage.show();

        }catch (IOException e){
            System.out.println("create method");
        }

    }



    public static void main(String[] args) {
        launch(args);
    }
}
