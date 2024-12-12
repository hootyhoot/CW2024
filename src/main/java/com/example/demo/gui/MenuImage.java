package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents an image of the menu of the GUI.
 * Extends the `ImageView` class to display the menu image.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/gui/MenuImage.java">Source code</a>
 */
public class MenuImage extends ImageView {

    /**
     * Constructs a MenuImage with the specified position, image name, and size.
     *
     * @param PositionX the x position of the image
     * @param PositionY the y position of the image
     * @param IMAGE_NAME the name of the image file
     * @param SizeH the height of the image
     * @param SizeW the width of the image
     */
    public MenuImage(double PositionX, double PositionY, String IMAGE_NAME, double SizeH, double SizeW) {
        setLayoutX(PositionX);
        setLayoutY(PositionY);
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH);
        setFitWidth(SizeW);
    }
}