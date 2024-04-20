package sample.GUI.Controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;


import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.BE.Picture;
import sample.GUI.Main;
import sample.GUI.Model.PictureModel;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public TextField txtSearch;

    @FXML
    private ListView<Picture> lstPictures;

    @FXML
    private HBox hBoxTopBar;

    private double mousePosX = 0;
    private double mousePosY = 0;

    @FXML
    private BorderPane mainBorder;
    @FXML
    private ImageView imageView;

    private final PictureModel pictureModel;
    @FXML
    private Label lblRed;
    @FXML
    private Label lblGreen;
    @FXML
    private Label lblBlue;

    private int queueCount = 0;

    private Thread thread;


    public MainController() {
        pictureModel = new PictureModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        draggableWindow();
        txtSearchListener();
        listViewListener();
        lstPictures.setItems(pictureModel.getObsPictureList());
    }

    public void txtSearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                pictureModel.searchPicture(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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

    public void addImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image files (*.jpeg, *.png, *.jpg)" , "*.png","*.jpeg","*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        fileChooser.setTitle("Choose image file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                File newFile = new File("resources/data/pictures/" + selectedFile.getName());
                Files.copy(selectedFile.toPath(), newFile.toPath());
                Picture picture = new Picture(selectedFile.getName(), newFile.getPath());
                lstPictures.getItems().add(picture);
            } catch (FileAlreadyExistsException e) {
                alertBox("File already exists", "File is already present in the folder"+ '\n' + e);
            }
        }
    }

    private void listViewListener() {
        lstPictures.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setImageView(newValue);
            }
        });
    }

    @FXML
    private void setImageView(Picture picture) {
        Image image = new Image(new File(picture.getPath()).toURI().toString());
        imageView.setImage(image);
        setColorsOfImage(picture);
    }

    private void alertBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void btnStartSlideshow(ActionEvent actionEvent) {
        ObservableList<Picture> observablePictures = pictureModel.getObsPictureList();
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < observablePictures.size(); i++) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setImageView(observablePictures.get(queueCount));
                            queueCount++;
                            if (queueCount >= observablePictures.size()) {
                                queueCount = 0;
                            }
                        }
                    });

                    Thread.sleep(2000);
                }

                return null;
            }
        };
        thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void btnStopSlideshow(ActionEvent actionEvent) {
        thread.interrupt();
    }

    private void setColorsOfImage(Picture p) {
        lblRed.setText(String.valueOf(pictureModel.getRedPixelCount(p)));
        lblGreen.setText(String.valueOf(pictureModel.getGreenPixelCount(p)));
        lblBlue.setText(String.valueOf(pictureModel.getBluePixelCount(p)));
    }
}
