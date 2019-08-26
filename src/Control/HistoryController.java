package Control;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

    public static void addMonth_Year(String date , String time , String month_year ){
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

        }else
            addTime(addDate(historyList.getRoot().getChildren().get(indexOfItem),date),time);


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
        if(historyList.getSelectionModel().getSelectedItem() != null) {
            String selectedItem = historyList.getSelectionModel().getSelectedItem().getValue();
            if (selectedItem.contains(":")) {
                for (Order order : Main.orders) {
                    if (order.getTime().equals(selectedItem))
                        historyPane.getChildren().add(makeHistoryLayout(order));
                }
            } else {
                for (Order order : Main.orders) {
                    if (order.getDate().startsWith(selectedItem.substring(0, 2)) && order.getDate()
                            .endsWith(selectedItem.substring(selectedItem.length() - 2)))
                        historyPane.getChildren().add(makeHistoryLayout(order));

                }
            }
        }
    }

    private VBox makeHistoryLayout(Order order){
        Text totalMoney = new Text(order.totalMoney.getText()) ;
        order.totalMoney.textProperty().addListener(e->{
            totalMoney.setText(order.totalMoney.getText());
        });

        FlowPane flowPane = new FlowPane(new Text(order.getTime()),totalMoney);
        flowPane.setHgap(10);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setStyle("-fx-background-color:#21F783");
        flowPane.setPrefSize(100,30);
        HBox hBox= new HBox(order.delete , order.edit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);
        VBox vBox = new VBox(flowPane, order.table , hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(3);
        return vBox;
    }

    public static void removeTime(String month_year , String date,String time){
        int i;
        for (i = 0; i < dates.size();i++)
            if(dates.get(i).equals(month_year))
                break;
        TreeItem<String > root =historyList.getRoot();
        TreeItem<String> subRoot =root.getChildren().get(i);

        TreeItem<String> subTree  = null;

        for(TreeItem<String> temp : subRoot.getChildren())
            if (temp.getValue().equals(date)) {
                subTree = temp;
                break;
            }

        for(TreeItem<String>temp:subTree.getChildren())
            if (time.equals(temp.getValue())) {
                subTree.getChildren().remove(temp);
                break;
            }



        if(subTree.getChildren().size() == 0) {
            subRoot.getChildren().remove(subTree);
            dates.remove(i);
        }

        if(subRoot.getChildren().size() == 0)
            root.getChildren().remove(subRoot);

        //historyPane.getChildren().clear();
        //System.out.println("value "+subTree.getValue());
        //System.out.println(subTree==null? subRoot :subTree);
        TreeItem<String > treeItem = historyList.getSelectionModel().getSelectedItem();
        historyList.getSelectionModel().select(null);
        historyList.getSelectionModel().select(treeItem);


        //}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        historyStage=stage;
        historyPane=historyLayout;
        historyList = historyTree;
        column = ordersColumn;
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        historyPane.setPadding(new Insets(10));
        dates = FXCollections.observableArrayList();
        historyList.setRoot(new TreeItem<>("Dates"));
        historyList.getRoot().setExpanded(true);
        historyList.setShowRoot(false);
        stage.setOnCloseRequest(e->historyList.getSelectionModel().select(null));
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
