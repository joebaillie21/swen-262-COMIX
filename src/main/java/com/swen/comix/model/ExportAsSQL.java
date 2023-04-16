package com.swen.comix.model;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ExportAsSQL implements Exporter {

    // This method works slightly different than the other export
    // Rather than exporting to a file path, it will return an statement that will
    // add the data to the SQL database
    // The returned statement will then be called within the database class

    @Override
    public String export(ArrayList<ComicBook> comicBooks) {
        String psuedoPath = """
                INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher, principle_character)
                        VALUES
                """;
        for (ComicBook c : comicBooks) {
            String holder = "(";
            String seriesTitle = "'" + c.getSeriesTitle() + "'";
            String volumeNumber = "'" + c.getVolNum() + "'";
            String issueNumber = "'" + c.getIssueNumber() + "'";
            String publicationDate = "TO_DATE('" + c.getPublicationDate() + "', 'YYYY-MM-DD)";
            ArrayList<Author> authorsArray = c.getAuthors();
            String authorsString = "";
            for (int i = 0; i < authorsArray.size(); i++) {
                authorsString += authorsArray.get(i).getName();
                if (i != authorsArray.size() - 1) {
                    authorsString += ", ";
                }
            }
            String publisher = c.getPublisher().getName();
            ArrayList<String> pcArray = c.getPrincipleCharacters();
            String principleCharacterString = "";
            for (int i = 0; i < pcArray.size(); i++) {
                principleCharacterString += pcArray.get(i);
                if (i != pcArray.size() - 1) {
                    principleCharacterString += ", ";
                }
            }
            holder += seriesTitle + ", " + volumeNumber + ", " + issueNumber + ", " + publicationDate + ", "
                    + authorsString;
        }

        return null;

    }

}
