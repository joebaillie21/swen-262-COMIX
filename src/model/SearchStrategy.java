package model;

import java.util.ArrayList;

public interface SearchStrategy {
    public ArrayList<ComicBook> algorithm(String toBeSearched, String searchOn) throws Exception;
}