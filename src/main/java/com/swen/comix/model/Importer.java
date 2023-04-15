package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo 
 * Interface for the CSV, XML, JSON and SQL 
 */
public interface Importer {
    public ArrayList<ComicBook> importToJava(String filename);  
}
