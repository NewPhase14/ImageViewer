package sample.BLL;

import sample.BE.Picture;

import java.util.ArrayList;
import java.util.List;

public class PictureManager {

    private List<Picture> pictures;

    public PictureManager() {
        pictures = new ArrayList<>();
    }

    public List<Picture> getAllPictures() {
        List<Picture> p = new ArrayList<>();

        for (int i = 0; i < pictures.size(); i++) {
            p.add(pictures.get(i));
        }
        return p;
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

}
