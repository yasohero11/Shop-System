package sample;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public abstract class PopUpMessage {
    private BorderPane layout ;
    private Stage frame;
    protected Button save ;
    protected Button close ;
    private boolean contiue;
    private HBox bottomLayout ;

    public PopUpMessage() {
        bottomLayout = new HBox();
        frame = new Stage();
        frame.setTitle("Pop Up Message");
        frame.initModality(Modality.APPLICATION_MODAL);
        frame.setResizable(false);
        save = new Button("Save");
        close = new Button("Close");
        bottomLayout.getChildren().addAll(save, close);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setSpacing(20);
        layout = new BorderPane();
        layout.setBottom(bottomLayout);
        contiue =  false;
        frame.setScene(new Scene(layout , 500, 300));

        close.setOnAction(e->close());
    }


    public void show(){
        frame.show();
    }
    protected void close(){
        frame.close();
    }

    protected void setContiue(boolean contiue) {
        this.contiue = contiue;
    }

    protected BorderPane getLayout() {
        return layout;
    }


}