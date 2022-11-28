package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

public class GuiElementBox {
    public VBox createBox(IMapElement element)
    {
        if(element == null)
            throw new IllegalArgumentException("Trying to create image on map from null value");
        int sizeOfImg = 20;
        try {
            Image image = new Image(new FileInputStream(element.getImageOfElement()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(sizeOfImg);
            imageView.setFitHeight(sizeOfImg);
            if(element instanceof Animal)
                return createBoxWithLabel(imageView, element.getPosition().toString());
            return createBoxWithLabel(imageView, "grass");
        }
        catch (java.io.FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    private VBox createBoxWithLabel(ImageView view, String labelText)
    {
        Label label = new Label(labelText);
        VBox box = new VBox(view, label);
        box.setAlignment(Pos.CENTER);
        return box;
    }

}
