package com.swen.comix.model;

import java.util.ArrayList;

public class ExportAsSQL implements Exporter {

    @Override
    public String export(ArrayList<ComicBook> comicBooks) {
        String psuedoPath = """
                INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher, principle_character)
                        VALUES
                """;
        for (ComicBook c : comicBooks) {

        }
        return null;

    }

}
