package sample.DAL;

import sample.BE.Picture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureDAO {

    public List<Picture> getAllPictures() {
        File[] directory = new File("resources/data/pictures").listFiles();
        List<Picture> picturelist = new ArrayList<>();

        if (directory != null) {
            for (File file : directory) {
                Picture p = new Picture(file.getName(), file.getPath());
                picturelist.add(p);
            }
        }
        return picturelist;
    }
}
