package model;

import java.util.ArrayList;

public interface SearchStrategy {
    public ArrayList<ComicBook> algorithm(String toBeSearched) throws Exception;
}