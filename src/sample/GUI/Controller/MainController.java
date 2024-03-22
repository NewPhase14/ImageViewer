package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.GUI.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private HBox hBoxTopBar;
    private double mousePosX = 0;
    private double mousePosY = 0;
    @FXML
    private BorderPane mainBorder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        draggableWindow();
        try {
            openAllPhotos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void minimizeButton(ActionEvent actionEvent) {
        Stage s = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    public void closeButton(ActionEvent actionEvent) {
        Stage s = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        s.close();
    }

    private void draggableWindow(){
        hBoxTopBar.setOnMousePressed(e ->{
            mousePosX = e.getSceneX();
            mousePosY = e.getSceneY();
        });

        hBoxTopBar.setOnMouseDragged(e -> {
            Main.s.setX(e.getScreenX()-mousePosX);
            Main.s.setY(e.getScreenY()-mousePosY);
        });
    }

    @FXML
    private void openAllPhotos() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PicturePage.fxml"));
        VBox vbox = fxmlLoader.load();

        mainBorder.setCenter(vbox);
    }
}
