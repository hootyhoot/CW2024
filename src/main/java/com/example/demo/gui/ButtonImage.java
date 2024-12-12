package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents an image used as a button in the GUI.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/gui/ButtonImage.java">Source code</a>
 */
public class ButtonImage extends ImageView {

    /**
     * Constructs a ButtonImage with the specified image name, height, and width.
     *
     * @param IMAGE_NAME the name of the image file
     * @param SizeH the height of the image
     * @param SizeW the width of the image
     */
    public ButtonImage(String IMAGE_NAME, double SizeH, double SizeW) {
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH);
        setFitWidth(SizeW);
    }
}