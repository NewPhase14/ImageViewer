package sample.GUI.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Picture;
import sample.BLL.PictureManager;

import java.util.List;

public class PictureModel {

     private PictureManager pictureManager;

     private ObservableList<Picture> obsPictureList;

     public PictureModel() {
         pictureManager = new PictureManager();

         obsPictureList = FXCollections.observableArrayList();
         obsPictureList.addAll(pictureManager.getAllPictures());
     }

     public void searchPicture(String query){
         List<Picture> searchResult = pictureManager.searchPicture(query);
         obsPictureList.clear();
         obsPictureList.addAll(searchResult);
     }

     public ObservableList<Picture> getObsPictureList() {
         return obsPictureList;
     }

     public int redPixelCount(Picture p){
         return pictureManager.redPixelCount(p);
     }

     public int greenPixelCount(Picture p){
         return pictureManager.greenPixelCount(p);
     }

     public int bluePixelCount(Picture p){
         return pictureManager.bluePixelCount(p);
     }

}