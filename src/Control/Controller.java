package Control;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.*;
import sample.Order.OrderNode;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable  {

    @FXML
    private VBox layout;

    @FXML
    private TextField search;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TableColumn<OrderNode,String> nameCell;
    @FXML
    private TableColumn<OrderNode,Integer> amountCell;
    @FXML
    private TableColumn<OrderNode,Double> priceCell;
    @FXML
    private TableColumn<OrderNode,Double> totalCell;
    @FXML
    private BorderPane orderLayout;
    @FXML
    private TableView<OrderNode> table;
    @FXML
    private FlowPane dataPane;

   public static TableView<OrderNode> tableView;
   public static ChoiceBox box;
   private AddMessage addMessage;
   private EditMessage editMessage;
   private VBox deletePane;
   public static BorderPane orderPane;
   public static VBox pane;
   public static FlowPane dataLayout ;



   private int count = 0;







    public void add(ActionEvent event){
       addMessage.show();
    }
    public void buy(ActionEvent event){

    }
    public void edit(ActionEvent event){
        editMessage.show();

       // box.getSelectionModel().select(0);
    }

    public void onBuy(ActionEvent event){
        Order order =Main.orders.getLast();
        if(Main.orders.size() != 0 && !order.isEmpty() && !order.isFinished()) {
            order.cloneTable(table);
            order.setFinished(true);
            Main.products.resetAllProduct();
            table.setItems(null);

            /*
            for (Order i:Main.orders){
                for(OrderNode n: i.dates){
                    System.out.println(n.getName() +" "+n.getAmount()+" " +n.getPrice()+" " +" " +n.getTotal());
                }
                System.out.println("-------------------");
            }
            */
        }
    }
    public void onHistory(ActionEvent event){
        HistoryController.historyStage.show();
    }

    public void onDelete(ActionEvent event) {
        if(count % 2 == 0) {
            scrollPane.setContent(DeleteController.tempProductPane);
            ((Button)event.getSource()).setTextFill(Color.WHITE);
            ((Button)event.getSource()).setStyle("-fx-background-color:#797D7F");
        }
        else {
            scrollPane.setContent(layout);
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setStyle("-fx-background-color:#A6ACAF");
        }

        count++;

    }

    public void resetAllProductes(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        tableView =table;
        orderPane=orderLayout;
        dataLayout = dataPane;

        try {
            deletePane = FXMLLoader.load(getClass().getResource("../View/FXMLdelete.fxml"));
            FXMLLoader.load(getClass().getResource("../View/historyPane.fxml"));
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        box = new ChoiceBox();
        pane=layout;

        try {
            editMessage = new EditMessage();
        }
        catch(Exception e){
            System.out.println("err");
        }
        addMessage = new AddMessage();

        search.textProperty().addListener(e->{
            pane.getChildren().clear();
            DeleteController.tempProductPane.getChildren().clear();
            if(search.getLength() != 0){
                for(int i  = 0 ;  i < Main.products.size();i++) {
                    Product product = Main.products.getProduct(i);
                    if (product.getProductName().startsWith(search.getText())) {
                        pane.getChildren().add(product.getLayout());
                        DeleteController.addToDeletePane(product);
                    }
                }

            }else
                for(int i  = 0 ;  i < Main.products.size();i++) {
                    layout.getChildren().add(Main.products.getProduct(i).getLayout());
                    DeleteController.addToDeletePane(Main.products.getProduct(i));
                }


        });
        Main.products.addProduct("ahmedddddddddddddd" , 15);
        Main.products.addProduct("khaled" , 1);
        Main.products.addProduct("moahmed" , 5);
        Main.products.addProduct("koko" , 20);
        Main.products.addProduct("mohsen" , 10);
        Main.products.addProduct("ahme" , 15);
        Main.products.addProduct("khale" , 1);
        Main.products.addProduct("moahme" , 5);
        Main.products.addProduct("kok" , 20);
        Main.products.addProduct("mohse" , 10);
        Main.products.addProduct("ahm" , 15);
        Main.products.addProduct("khal" , 1);
        Main.products.addProduct("moahm" , 5);
        Main.products.addProduct("ko" , 20);
        Main.products.addProduct("mohs" , 10);
        Main.products.addProduct("ah" , 15);
        Main.products.addProduct("kha" , 1);
        Main.products.addProduct("moah" , 5);
        Main.products.addProduct("k" , 20);
        Main.products.addProduct("moh" , 10);
      nameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
      amountCell.setCellValueFactory(new PropertyValueFactory<>("amount"));
      priceCell.setCellValueFactory(new PropertyValueFactory<>("price"));
      totalCell.setCellValueFactory(new PropertyValueFactory<>("total"));
    }


}
