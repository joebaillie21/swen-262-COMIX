package com.swen.comix.model;

import java.util.ArrayList;
/**
 * @Author Angela 
 */
public interface SearchStrategy {
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) throws Exception;
}