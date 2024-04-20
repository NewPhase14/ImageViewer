package sample.BLL;


import sample.BE.Picture;
import sample.BLL.util.Searcher;
import sample.DAL.PictureDAO;

import java.util.ArrayList;
import java.util.List;

public class PictureManager {

    private final Searcher searcher;

    private final PictureDAO pictureDAO;

    public PictureManager() {
        pictureDAO = new PictureDAO();
        searcher = new Searcher();
    }

    public List<Picture> searchPicture(String query){
        List<Picture> allPictures = getAllPictures();
        return searcher.search(allPictures, query);
    }

    public List<Picture> getAllPictures(){
        return pictureDAO.getAllPictures();
    }
}
