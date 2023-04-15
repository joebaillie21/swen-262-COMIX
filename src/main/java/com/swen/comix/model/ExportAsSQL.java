package com.swen.comix.model;

import java.io.IOException;
import java.util.ArrayList;

public class ExportAsSQL implements Exporter {
    private ArrayList<ComicBook> comicBooks;
    public ExportAsSQL(ArrayList<ComicBook> comicBooks){
        this.comicBooks = comicBooks; 
    }
    @Override
    public String toFile()throws IOException {
        String psuedoPath = """
                INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher, principle_character)
                        VALUES
                """;
        for (ComicBook c : comicBooks) {

        }
        return null;

    }

  

}
