package com.swen.comix.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class ExportAsCSV implements Exporter {
    private ArrayList<ComicBook> comics; 
    //maybe have a name for the new file? 
    
    public ExportAsCSV(ArrayList<ComicBook> comics){
        this.comics = comics; 
    }
    @Override
    public String toFile() throws IOException{
        String[] header = {"Series", "Issue", "Full Title", "Description", "Publisher", "Release Date", "Format", "Added Date", "Authors"};
        ArrayList<String[]> csvEntries = new ArrayList<>(); 
        csvEntries.add(header);
        for(int i = 0; i < comics.size(); i++ ){
            ComicBook cb = comics.get(i);
            String fullSeriesTitle = cb.getSeriesTitle() + ", " + cb.getVolNum();
            String authors = "";
            ArrayList<Author> authorAL = cb.getAuthors();
            for(int k = 0; k < (authorAL.size()-1) ; k++){
                authors += cb.getAuthors().get(i).getName();
                authors += "| ";
            }
            authors += authorAL.get(authorAL.size()-1);
            String [] entry = {fullSeriesTitle, cb.getIssueNumber(), "", cb.getDescription(), cb.getPublisher().getName(), cb.getPublicationDate(), "Comic", "N/A", authors};
            csvEntries.add(entry);
        }

        
        try(CSVWriter writer =  new CSVWriter(new FileWriter("newCSV.csv"))){
            writer.writeAll(csvEntries);
        }

        return null;

    }
    
}
