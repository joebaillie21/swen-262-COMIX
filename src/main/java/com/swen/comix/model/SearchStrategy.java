package com.swen.comix.model;

import java.util.ArrayList;

public interface SearchStrategy{
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb); 
}