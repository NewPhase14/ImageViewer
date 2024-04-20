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

     public int getRedPixelCount(Picture p){
         return pictureManager.getRedPixelCount(p);
     }

     public int getGreenPixelCount(Picture p){
         return pictureManager.getGreenPixelCount(p);
     }

     public int getBluePixelCount(Picture p){
         return pictureManager.getBluePixelCount(p);
     }

}