package com.swen.comix.model;

import java.util.ArrayList;
/**
 * @Author Angela 
 * Interface for searching on personal collections and database 
 */
public interface SearchStrategy {
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) throws Exception;
}