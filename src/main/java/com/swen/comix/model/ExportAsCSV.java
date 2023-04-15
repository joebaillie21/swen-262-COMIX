package com.swen.comix.model;

import java.io.IOException;
import java.util.ArrayList;

public class ExportAsCSV implements Exporter {
    private ArrayList<ComicBook> comics; 
    public ExportAsCSV(ArrayList<ComicBook> comics){
        this.comics = comics; 
    }
    @Override
    public String toFile() throws IOException{
        String[] header = {"Series", "Issue", "Full Title", "Description", "Publisher", "Release Date", "Format", "Added Date", "Authors"};
        ArrayList<String[]> csvEntries = new ArrayList<>(); 
        csvEntries.add(header);
        for(int i = 0; i < comics.size(); i++ ){

        }

        return null;

    }
    
}
