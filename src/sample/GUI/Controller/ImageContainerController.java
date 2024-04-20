package sample.GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageContainerController {

    @FXML
    public Label txtTitle;
    @FXML
    private ImageView imgPicture;

    public void setImage(String URL) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(URL));
        imgPicture.setFitHeight(150);
        imgPicture.setFitWidth(200);
        imgPicture.setPreserveRatio(false);
        imgPicture.setImage(image);
    }
}
