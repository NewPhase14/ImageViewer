package sample.BE;

import java.util.List;

public class Folder {

    private String name;
    private int numberOfPictures;
    private String filePath;
    private List<Picture> pictures;

    public Folder(String name, int numberOfPictures, String filePath, List<Picture> pictures) {
        this.name = name;
        this.numberOfPictures = numberOfPictures;
        this.filePath = filePath;
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPictures() {
        return numberOfPictures;
    }

    public void setNumberOfPictures(int numberOfPictures) {
        this.numberOfPictures = numberOfPictures;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
