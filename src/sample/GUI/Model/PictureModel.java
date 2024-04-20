package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Picture;
import sample.BLL.PictureManager;

public class PictureModel {

    private ObservableList<Picture> obsPictureList;

    private PictureManager pictureManager;

    public PictureModel(){
        pictureManager = new PictureManager();
        obsPictureList = FXCollections.observableArrayList();
        obsPictureList.addAll(pictureManager.getAllPictures());
    }

    public ObservableList<Picture> getObsPictureList() {
        return obsPictureList;
    }

    public void addPicture(Picture picture){
        obsPictureList.add(picture);
        pictureManager.addPicture(picture);
    }
}
