package sample;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleSwitch extends Pane {

    private Rectangle rectangle;
    protected Circle circle;
    private TranslateTransition move;
    private FillTransition backgroundFillTransition;
    private FillTransition circleFillTransition;
    private ParallelTransition transition;
    private BooleanProperty isOpen;
    private Color backgroundOpeningColor;
    private Color backgroundClosingColor =  Color.LIGHTGRAY;
    private Color circleOpeningColor;
    private Color circleClosingColor;
    private double coordinates;
    private double mainCoordintes =0;
    public ToggleSwitch(double width , double height , double radius){
        isOpen = new SimpleBooleanProperty(false);
        rectangle = new Rectangle(width,height);
        circle =  new Circle(radius);

        move = new TranslateTransition(Duration.seconds(0.25), circle);
        backgroundFillTransition = new FillTransition(Duration.seconds(0.25) , rectangle);
        circleFillTransition = new FillTransition(Duration.seconds(0.25) , circle);
        transition = new ParallelTransition(move);
        getChildren().addAll(rectangle, circle);
        circle.setFill(circleClosingColor);
        circle.setStroke(Color.LIGHTGRAY);
        circle.setCenterX(height/2);
        circle.setCenterY(height/2);
        rectangle.setArcWidth(height);
        rectangle.setArcHeight(height);

        coordinates = width - (radius*2);

        setOnMouseClicked(e->{
            if(!isOpen.get()) {
                move.setToX(coordinates);
                backgroundFillTransition();
                circleFillTransition();
                circle.setStroke(circleOpeningColor);
                transition.play();
                isOpen.set(true);
            }
            else
            {
                backgroundFillTransition();
                circleFillTransition();
                move.setToX(mainCoordintes);
                circle.setStroke(circleClosingColor);
                transition.play();
                isOpen.set(false);
            }
        });

    }




    public void setBackgroundColor(Color color){
        rectangle.setFill(color);
    }
    public void setCircleColor(Color color){
        circle.setFill(color);
    }
    public void setBackgroundFillTransitionColor(Color from , Color to){
        backgroundOpeningColor = to;
        backgroundClosingColor = from;
        setBackgroundColor(from);
        transition.getChildren().add(backgroundFillTransition);
    }
    private void backgroundFillTransition(){
        if(!isOpen.get()){
            backgroundFillTransition.setFromValue(backgroundClosingColor);
            backgroundFillTransition.setToValue(backgroundOpeningColor);
        }else{
            backgroundFillTransition.setFromValue(backgroundOpeningColor);
            backgroundFillTransition.setToValue(backgroundClosingColor);
        }
    }

    public BooleanProperty switchedOnProperty() {
        return isOpen;
    }
    public boolean isSwitchedOn(){
        return isOpen.get();
    }

    public void setCircleFillTransitionColor(Color from , Color to){
        circleClosingColor = from;
        circleOpeningColor = to;
        setCircleColor(from);
        transition.getChildren().add(circleFillTransition);
    }

    private void circleFillTransition(){
        if(!isOpen.get()) {
            circleFillTransition.setFromValue(circleClosingColor);
            circleFillTransition.setToValue(circleOpeningColor);
        }
        else{
            circleFillTransition.setFromValue(circleOpeningColor);
            circleFillTransition.setToValue(circleClosingColor);
        }
    }
    protected void googleMode(){
        coordinates = coordinates + circle.getRadius();
        mainCoordintes = -3;
    }

    public void setDuration(Duration duration) {
        move.setDuration(duration);
    }
}
