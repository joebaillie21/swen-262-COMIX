package com.swen.comix.model;

import java.util.ArrayList;

public class ExportAsCSV implements Exporter {
    private ArrayList<ComicBook> comics; 
    public ExportAsCSV(ArrayList<ComicBook> comics){
        this.comics = comics; 
    }
    @Override
    public String toFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toFile'");
    }
    
}
