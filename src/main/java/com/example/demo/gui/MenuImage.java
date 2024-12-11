package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class MenuImage extends ImageView {

    public MenuImage(double PositionX, double PositionY,String IMAGE_NAME,double SizeH, double SizeW ) {
        setLayoutX(PositionX);
        setLayoutY(PositionY);
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH);
        setFitWidth(SizeW);
    }
}