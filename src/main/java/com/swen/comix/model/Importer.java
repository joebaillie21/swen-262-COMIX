package com.swen.comix.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author Angela Ngo 
 * Interface for the CSV, XML, JSON and SQL 
 */
public interface Importer {
    public ArrayList<ComicBookComponent> importToJava() throws IOException;  
}
