package sample.BLL;


import sample.BE.Picture;
import sample.BLL.util.PixelCounter;
import sample.BLL.util.Searcher;
import sample.DAL.PictureDAO;

import java.util.List;

public class PictureManager {

    private final Searcher searcher;

    private final PictureDAO pictureDAO;

    private PixelCounter pixelCounter;

    public PictureManager() {
        pictureDAO = new PictureDAO();
        searcher = new Searcher();
        pixelCounter = new PixelCounter();
    }

    public List<Picture> searchPicture(String query){
        List<Picture> allPictures = getAllPictures();
        return searcher.search(allPictures, query);
    }

    public List<Picture> getAllPictures(){
        return pictureDAO.getAllPictures();
    }

    public int redPixelCount(Picture p){
        return pixelCounter.imageRedCounter(p);
    }

    public int greenPixelCount(Picture p){
        return pixelCounter.imageGreenCounter(p);
    }

    public int bluePixelCount(Picture p){
        return pixelCounter.imageBlueCounter(p);
    }
}
