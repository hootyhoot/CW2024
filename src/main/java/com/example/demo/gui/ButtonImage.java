package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ButtonImage extends ImageView {

    public ButtonImage(String IMAGE_NAME,double SizeH, double SizeW ) {
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH);
        setFitWidth(SizeW);
    }
}

