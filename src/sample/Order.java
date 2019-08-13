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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class Order {

    private String date;
    private String time;
    private  String MONTH_YEAR;
    private boolean finished = false;

    public ObservableList<OrderNode> list;
    public ObservableList<OrderNode> tempList;
    public TableView<OrderNode> table;
    private TableColumn<OrderNode,String> nameCell;
    private TableColumn<OrderNode,Integer> amountCell;
    private TableColumn<OrderNode,Double> priceCell;
    private TableColumn<OrderNode,Double> totalCell;
    public Text totalMoney = new Text("");
    public JFXButton edit =  new JFXButton("Edit");
    public JFXButton delete = new JFXButton("Delete");
    public Order(String month_year , String date , String time) {
        edit.setAlignment(Pos.CENTER_RIGHT);
        this.date = date ;
        this.time = time;
        MONTH_YEAR = month_year;
        Controller.dataLayout.getChildren().addAll(new Text("Date: "+date)
                , new Text("Time: "+time), totalMoney);

        edit.setDisable(true);
        edit.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView("images/edit.png");
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        edit.setGraphic(imageView);
        edit.setGraphicTextGap(15);
        ImageView imageView1 = new ImageView("images/delete.png");
        imageView1.setFitWidth(25);
        imageView1.setFitHeight(25);
        delete.setGraphic(imageView1);
        delete.setGraphicTextGap(15);


        list = FXCollections.observableArrayList();
        tempList = FXCollections.observableArrayList();
        Controller.tableView.setItems(list);
        table = new TableView<>();
        table.setItems(list);
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
        HistoryController.addMonth_Year(this.date,this.time,MONTH_YEAR);
        edit.setOnAction(e->{
            finished = false;
            tempList.setAll(list);
            list.clear();
            Controller.dataLayout.getChildren().addAll(new Text("Date: "+this.date)
                    , new Text("Time: "+this.time), totalMoney);
            for (int i = 0 ; i < tempList.size(); i++) {
                OrderNode orderNode = tempList.get(i);
                Product product = Controller.products.getProduct(orderNode.getName());
                product.setCount(orderNode.amount.get());
            }
            Controller.tableView.setItems(list);
            edit.setDisable(true);
            delete.setDisable(true);
            delete.setDisable(true);
            HistoryController.historyStage.close();
            Controller.buyButtton.setText("Save");
        });
        delete.setOnAction(e->clearOrder());
    }





    public void addOrder(String productName, double price, Label count){
        if(!exists(productName)) {
            OrderNode node = new OrderNode(productName, price, count);
            list.add(node);
            totalMoney.setText(getTotal());
            //Controller.dataLayout.getChildren().set(2 , Order.this.getTotal());
        }
    }

    public boolean exists(String name){
        for (OrderNode orderNode : list)
            if(name.equalsIgnoreCase(orderNode.name.get()))
                return true;

        return false;
    }


    public boolean isFinished() {
        return finished;
    }
    public void setFinished(Boolean value){
        finished = value;
    }
    public void clearOrder(){

        Controller.dataLayout.getChildren().clear();
        Main.orders.remove(this);
        Controller.buyButtton.setText("Buy");
        Controller.writerAllOrders();
        Controller.products.resetAllProduct();
        HistoryController.removeTime(MONTH_YEAR, date, time);

    }
    public void setEditable(Boolean value){
        edit.setDisable(!value);
    }
    public void setDeleteable(Boolean value){
        delete.setDisable(!value);
    }


    public void deleteOrder(String name){
        for(int i = 0 ;  i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(name))
                list.remove(i);

        }
    }





    public boolean isEmpty(){return list.isEmpty();}
    public String getTime() {
        return time;
    }

    public String getMONTH_YEAR() {
        return MONTH_YEAR;
    }

    public String getDate() {
        return date;
    }
    public String getTotal(){
        double temp=0;
        for(OrderNode orderNode : list) {
            temp = temp + orderNode.getTotal();
        }
        return  "Total money: " + temp;
    }


    public class OrderNode {

        private SimpleStringProperty name = new SimpleStringProperty("");
        private SimpleStringProperty amount = new SimpleStringProperty("");
        private SimpleDoubleProperty price = new SimpleDoubleProperty(0.0);
        private SimpleDoubleProperty total = new SimpleDoubleProperty(0.0);


        public OrderNode(String name, double price, Label count) {
            this.name.set(name);
            this.price.set(price);
            total.set(price);

            amount.set(count.getText());
            count.textProperty().addListener(e -> {
                if (!isFinished() && !list.isEmpty()) {
                    amount.set(count.getText());
                    total.set(price * Integer.parseInt(amount.get()));
                    totalMoney.setText(Order.this.getTotal());
                    Controller.tableView.refresh();
                    table.refresh();
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

        public void setAmount(String amount) {
            this.amount.set(amount);
            total.set(price.get() * Integer.parseInt(amount));
            totalMoney.setText(Order.this.getTotal());
        }

        public void setTotal(double total) {
            this.total.set(total);
        }
    }


}
