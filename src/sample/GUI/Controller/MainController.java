package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.BE.Picture;
import sample.GUI.Main;
import sample.GUI.Model.PictureModel;


import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public TextField txtSearch;
    @FXML
    private ListView<Picture> lstPictures;

    @FXML
    private HBox hBoxTopBar;
    private double mousePosX = 0;
    private double mousePosY = 0;

    @FXML
    private BorderPane mainBorder;

    private PictureModel pictureModel;

    public MainController() {
        pictureModel = new PictureModel();
    }
    private File selectedFile;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        draggableWindow();
        lstPictures.setItems(pictureModel.getObsPictureList());

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                pictureModel.searchPicture(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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

    public void addImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image files (*.jpeg, *.png, *.jpg)" , "*.png","*.jpeg","*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        fileChooser.setTitle("Choose image file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                File newFile = new File("resources/data/pictures/" + selectedFile.getName());
                Files.copy(selectedFile.toPath(), newFile.toPath());
                Picture picture = new Picture(selectedFile.getName(), newFile.getPath());
                lstPictures.getItems().add(picture);
            } catch (FileAlreadyExistsException e) {
                System.out.println("File already exists");
            }
        }
    }

}
