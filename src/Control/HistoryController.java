package Control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Main;
import sample.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {


    @FXML
    private Stage stage;
    @FXML
    private  TreeTableView<String> historyTree;
    @FXML
    private FlowPane historyLayout;
    @FXML
    private TreeTableColumn<String,String> ordersColumn;

    public static ObservableList<String> dates;
    public static Stage  historyStage;
    public static TreeTableView<String> historyList;
    public static FlowPane historyPane;
    public  static TreeTableColumn<String,String> column;
    static int indexOfItem = 0;

    public static void addMonth_Year(String date , String time , String month_year){
        boolean exists = false;

        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(month_year)) {
                exists = true;
                indexOfItem = i;
                i = dates.size();
            }
        }


        if(exists == false) {
            dates.add(month_year);
            TreeItem<String> treeItem = new TreeItem<>(month_year );
            addTime(addDate(treeItem,date),time);
            historyList.getRoot().getChildren().add(treeItem);
            System.out.println("entered");
        }else{

            addTime(addDate(historyList.getRoot().getChildren().get(indexOfItem),date),time);
        }

    }
    private static TreeItem addDate(TreeItem<String> rootItem , String date){
        boolean exists = false;
        TreeItem<String> treeItem = null;
        for (TreeItem<String> temp : rootItem.getChildren()){
            if(temp.getValue().equals(date)) {
                treeItem = temp;
                exists = true;
                break;
            }
        }
        if(!exists) {
            treeItem = new TreeItem<>(date);
            rootItem.getChildren().add(treeItem);
        }
        return treeItem;
    }
    private static void addTime(TreeItem<String> subItem , String time){
             subItem.getChildren().add(new TreeItem<>(time));
        }

        public void showOrders(){
          String selectedItem = historyList.getSelectionModel().getSelectedItem().getValue();
          if(selectedItem.contains(":")){
              for (Order order : Main.orders){
                  if(order.getTime().equals(selectedItem)){
                      VBox vBox = new VBox(new Text(order.getTime()) , order.table);
                      historyPane.getChildren().add(vBox);
                  }
              }
          }
          else{
              for (Order order : Main.orders){
                  if(order.getDate().startsWith(selectedItem.substring(0,2)) && order.getDate()
                          .endsWith(selectedItem.substring(selectedItem.length()-2 )) ){
                      VBox vBox = new VBox(new Text(order.getTime()) , order.table , order.edit);
                      historyPane.getChildren().add(vBox);
                  }
              }
          }
        }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        historyStage=stage;
        historyPane=historyLayout;
        historyList = historyTree;
        column = ordersColumn;
        historyPane.setPadding(new Insets(10));
        dates = FXCollections.observableArrayList();
        historyList.setRoot(new TreeItem<>("Dates"));
        historyList.getRoot().setExpanded(true);

          historyList.getSelectionModel().selectedItemProperty().addListener(e->{
                 historyPane.getChildren().clear();
                 showOrders();
                  });
     ordersColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
         @Override
         public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String, String> param) {
             return new SimpleStringProperty(param.getValue().getValue());
         }
     });





    }
}
