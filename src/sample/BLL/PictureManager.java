package sample.BLL;


import sample.BE.Picture;
import sample.BLL.util.Searcher;
import sample.DAL.PictureDAO;

import java.util.ArrayList;
import java.util.List;

public class PictureManager {

    private Searcher searcher;

    private PictureDAO pictureDAO;

    public PictureManager() {
        pictureDAO = new PictureDAO();
        searcher = new Searcher();
    }

    public List<Picture> searchPicture(String query){
        List<Picture> allPictures = getAllPictures();
        List<Picture> searchResult = searcher.search(allPictures, query);
        return searchResult;
    }

    public List<Picture> getAllPictures(){
        return pictureDAO.getAllPictures();
    }
}
