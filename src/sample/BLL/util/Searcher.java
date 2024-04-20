package sample.BLL.util;

import sample.BE.Picture;

import java.util.ArrayList;
import java.util.List;

public class Searcher {

    public List<Picture> search(List<Picture> searchWord, String query){
        List<Picture> searchResult = new ArrayList<Picture>();

        for (Picture p : searchWord){
            if (compareTitle(query, p)){
                searchResult.add(p);
            }
        }
        return searchResult;
    }

    private boolean compareTitle(String query, Picture p){
        return p.getName().toLowerCase().contains(query.toLowerCase());
    }
}
