package sample;

import Control.Controller;
import Control.HistoryController;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import jdk.management.resource.internal.TotalResourceContext;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Order {

    private String date;
    private String time;
    private  String MONTH_YEAR;
    private int counter = 0;
    private boolean finished = false;
    private SimpleDoubleProperty totalMoney = new SimpleDoubleProperty(0.0);
    public ObservableList<OrderNode> list;
    public TableView<OrderNode> table;
    private TableColumn<OrderNode,String> nameCell;
    private TableColumn<OrderNode,Integer> amountCell;
    private TableColumn<OrderNode,Double> priceCell;
    private TableColumn<OrderNode,Double> totalCell;
    public JFXButton edit =  new JFXButton("Edit");

    public Order() {
        edit.setAlignment(Pos.CENTER_RIGHT);
        Date d = new Date();
        date = new SimpleDateFormat("MM/dd/y").format(d);
        time = new SimpleDateFormat("hh:mm:ss a").format(d);
        MONTH_YEAR = new SimpleDateFormat("MM/y").format(d);
        if(Main.orders.size() > 1){
            date = "20/10/2020";
            MONTH_YEAR = "20/2020";
        }

       // Controller.dataLayout.getChildren().addAll(new Text("Date :"+this.date), new Text("Time :" +this.time)
               // ,new Text("Total :" + totalMoney.get()));
        list = FXCollections.observableArrayList();
        Controller.tableView.setItems(list);
        table = new TableView<>();
        nameCell = new TableColumn<>("Product Name");
        amountCell = new TableColumn<>("Amount");
        priceCell = new TableColumn<>("Price");
        totalCell=new TableColumn<>("Total");
        table.getColumns().addAll(nameCell,amountCell,priceCell,totalCell);
        nameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCell.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceCell.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCell.setCellValueFactory(new PropertyValueFactory<>("total"));

        nameCell.setPrefWidth(200);
        amountCell.setPrefWidth(50);
        priceCell.setPrefWidth(50);
        totalCell.setPrefWidth(70);
        HistoryController.addMonth_Year(date,time,MONTH_YEAR);
        edit.setOnAction(e->{
            Controller.tableView.setItems(list);
        });
    }
    public void addOrder(String productName, double price, Label count){
        OrderNode node = new OrderNode(productName , price ,count);
        list.add(node);
    }

   // public void editOrder(int index)

    public boolean isFinished() {
        return finished;
    }
    public void setFinished(Boolean value){
        finished = value;
    }
    public void clearOrder(){
        list.clear();
        Main.orders.removeLast();
    }

    public void deleteOrder(String name){
                for(int i = 0 ;  i < list.size(); i++) {
                   if(list.get(i).getName().equalsIgnoreCase(name))
                       list.remove(i);
                }
            }

    public SimpleDoubleProperty getTotal() {
        return totalMoney;
    }

    public boolean isEmpty(){return list.isEmpty();}
    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }


   public class OrderNode {

       private SimpleStringProperty name = new SimpleStringProperty("");
       private SimpleStringProperty amount = new SimpleStringProperty("");
       private SimpleDoubleProperty price = new SimpleDoubleProperty(0.0);
       private SimpleDoubleProperty total = new SimpleDoubleProperty(0.0);

       public OrderNode(String name, double price, Label count) {
           this.name.set(name);
           this.price.set(price);
           amount.set(count.getText());
           total.set(price);
           totalMoney.set(totalMoney.get()+price);
           count.textProperty().addListener((observable ,oldValue, newValue) -> {
               System.out.println(Main.orders.size());
               if(!isFinished()){
                   amount.set(count.getText());
                   total.set(price * Integer.parseInt(amount.get()));
                   //totalMoney.set(totalMoney.get()+);
                   Controller.tableView.refresh();
               }
           });




       }

        public String getName() {
            return name.get();
        }

        public String getAmount() {
            return amount.get();
        }

        public double getTotal() {
            return total.get();
        }

        public double getPrice() {
            return price.get();
        }

       }
       public void cloneTable(TableView<OrderNode> tableView){
        table.setItems(list);

       }
   }
