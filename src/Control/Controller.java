package Control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.*;
import sample.Order.OrderNode;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable  {

    public static Products products ;
    @FXML
    private VBox layout;
    @FXML
    private Pane mainPane;
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
    @FXML
    private Button buy;
    @FXML
    private Text componyName;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView icon;
    @FXML
    private Text title;
    @FXML
    private Stage editStage;

    public static Text companyName;
    public static ImageView companyLogo;
    public static TableView<OrderNode> tableView;
    public static ChoiceBox box;
    private AddMessage productAddMessage;
    private EditMessage editMessage;
    private VBox deletePane;
    public static BorderPane orderPane;
    public static VBox pane;
    public static FlowPane dataLayout ;
    public static Button buyButtton;
    private static PrintWriter writer;
    private double x;
    private double y;



    private int count = 0;







    public void add(ActionEvent event){
        productAddMessage.show();
    }

    public void onClearOrder(ActionEvent event){
        Order order = Main.getUnFinshedOrder();
        if(order != null) {
            order.clearOrder();
            tableView.setItems(null);
        }

    }
    public void edit(ActionEvent event){
        editStage.show();
        // box.getSelectionModel().select(0);
    }

    public void onBuy(ActionEvent event){
        Order order = Main.getUnFinshedOrder();
        if(Main.orders.size() != 0 && order != null) {
            Controller.dataLayout.getChildren().clear();
            order.setEditable(true);
            order.setDeleteable(true);
            order.setFinished(true);
            products.resetAllProduct();
            table.setItems(null);
            if(buy.getText().equals("Buy"))
                writeOrder(order);
            else{
                writerAllOrders();
                buy.setText("Buy");
            }

        }
    }

    public static void writerAllOrders(){
        try {
            writer = new PrintWriter("orders.txt");
            for (Order order1 : Main.orders)
                writeOrder(order1);

            writer.flush();
        }
        catch (IOException e){
            System.out.println(e + " Controller");
        }
    }
    public static void writeOrder(Order order){

        writer.println(order.getMONTH_YEAR()+"$"+order.getDate()+"$"+order.getTime());

        for (OrderNode orderNode : order.list)
            writer.println(orderNode.getName()+"$"+orderNode.getPrice()+"$"+orderNode.getAmount());
        writer.println("!");
        writer.flush();
    }
    public void onHistory(ActionEvent event){
        if(Main.getUnFinshedOrder()==null)
        HistoryController.historyStage.show();
        else
            new ErrorMessage("Please Finish The Current Order First");
    }

    public void onDelete(ActionEvent event) {
        if(count % 2 == 0) {
            scrollPane.setContent(DeleteController.tempProductPane);
            ((Button)event.getSource()).setStyle("-fx-background-color:#A6ACAF");
        }
        else {
            scrollPane.setContent(layout);
            ((Button)event.getSource()).setStyle("-fx-background-color: linear-gradient(to right, #8e2de2, #4a00e0)");
        }

        count++;

    }
    public void onClose(ActionEvent event){
        ((Stage)((Button) event.getSource()).getScene().getWindow()).close();
        System.exit(0);
    }
    public void onMini(ActionEvent event){
        ((Stage)((Button) event.getSource()).getScene().getWindow()).setIconified(true);
    }

    public void onPress(MouseEvent event){
        x = event.getSceneX();
        y = event.getSceneY();
    }

    public void onDrag(MouseEvent event){

        ((AnchorPane)event.getSource()).getScene().getWindow().setX(event.getScreenX()-x);
        ((AnchorPane)event.getSource()).getScene().getWindow().setY(event.getScreenY()-y);
    }






    @Override
    public void initialize(URL location, ResourceBundle resources){
        tableView =table;
        orderPane=orderLayout;
        dataLayout = dataPane;
        buyButtton = buy;
        companyName=componyName;
        companyLogo =logo;
        logo.imageProperty().addListener(e->icon.setImage(logo.getImage()));
        companyName.textProperty().addListener(e->title.setText(componyName.getText()));

        try {

            FXMLLoader.load(getClass().getResource("../View/historyPane.fxml"));
            HistoryController.historyStage.initModality(Modality.APPLICATION_MODAL);
            editStage = FXMLLoader.load(getClass().getResource("../View/FXMLedit.fxml"));
            editStage.initModality(Modality.APPLICATION_MODAL);
            deletePane = FXMLLoader.load(getClass().getResource("../View/FXMLdelete.fxml"));
        }
        catch(IOException e){
            System.out.println(e.getMessage() + " Controller,s initialize 1");
        }
        catch (Exception e){
            System.out.println(e.getMessage() + " Controller,s initialize");
        }

        box = new ChoiceBox();
        pane=layout;

        productAddMessage = new AddMessage();
        products = new Products();
        Main.read();
        try {
            writer = new PrintWriter("orders.txt");
        }catch (IOException e){
            System.out.println(e + " Controller,s writer");
        }

        for (Order order :Main.orders)
            writeOrder(order);


        search.textProperty().addListener(e->{
            pane.getChildren().clear();
            DeleteController.tempProductPane.getChildren().clear();
            if(search.getLength() != 0){
                for(int i  = 0 ;  i < products.size();i++) {
                    Product product = products.getProduct(i);
                    if (product.getProductName().startsWith(search.getText())) {
                        pane.getChildren().add(product.getLayout());
                        DeleteController.addToDeletePane(product);
                    }
                }

            }else
                for(int i  = 0 ;  i < products.size();i++) {
                    layout.getChildren().add(products.getProduct(i).getLayout());
                    DeleteController.addToDeletePane(products.getProduct(i));
                }


        });

/*
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
*/
        nameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCell.setCellValueFactory(new PropertyValueFactory<>("amount"));
        priceCell.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalCell.setCellValueFactory(new PropertyValueFactory<>("total"));

        IOSToggleSwitch iosToggleSwitch = new IOSToggleSwitch(60 , 30);
        FlowPane flowPane = new FlowPane(new ImageView("images/sun.png"), iosToggleSwitch , new ImageView("images/moon.png"));
        flowPane.setHgap(10);
        flowPane.setLayoutX(mainPane.getPrefWidth()-170);
        flowPane.setLayoutY(15);
        mainPane.getChildren().add(flowPane);
        iosToggleSwitch.switchedOnProperty().addListener(e->{
            if(iosToggleSwitch.isSwitchedOn()) {
                mainPane.getStylesheets().add("Styles/lightMode.css");
                mainPane.getStylesheets().add("Styles/darkMode.css");
            }
            else
                mainPane.getStylesheets().remove(mainPane.getStylesheets().size()-1);

        });
    }


}
