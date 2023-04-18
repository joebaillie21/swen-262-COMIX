package com.swen.comix.model;

import java.io.IOException;
import java.util.ArrayList;

public class ExportAsSQL implements Exporter {

    // This method works slightly different than the other export
    // Rather than exporting to a file path, it will return an statement that will
    // add the data to the SQL database
    // The returned statement will then be called within the database class

    private ArrayList<ComicBookComponent> comicBooks;

    public ExportAsSQL(ArrayList<ComicBookComponent> comicBooks) {
        this.comicBooks = comicBooks;
    }

    @Override
    public String toFile() throws IOException {
        String psuedoPath = """
                INSERT INTO comics (series_title, volume_number, issue_number, publication_date, author, publisher, principle_character)
                        VALUES
                """;
        for (ComicBook c : comicBooks) {
            String holder = "(";
            String hold = "";

            hold = c.getSeriesTitle();
            // hold.replaceAll("\'", "\\'");
            String seriesTitle = "'" + hold + "'";

            hold = c.getVolNum();
            // hold.replaceAll("\'", " ");
            String volumeNumber = "'" + hold + "'";

            hold = c.getIssueNumber();
            // hold.replaceAll("'", " ");
            String issueNumber = "'" + c.getIssueNumber() + "'";

            String publicationDate = "TO_DATE('" + c.getPublicationDate() + "', 'YYYY-MM-DD)";

            ArrayList<Author> authorsArray = c.getAuthors();
            String authorsString = "'";
            for (int i = 0; i < authorsArray.size(); i++) {
                authorsString += authorsArray.get(i).getName();
                if (i != authorsArray.size() - 1) {
                    authorsString += ", ";
                }
            }
            authorsString += "'";

            hold = c.getPublisher().getName();
            // hold.replaceAll("'", " ");
            String publisher = "'" + hold + "'";

            ArrayList<String> pcArray = c.getPrincipleCharacters();
            String principleCharacterString = "'";
            for (int i = 0; i < pcArray.size(); i++) {
                hold = pcArray.get(i);
                // hold.replaceAll("'", " ");
                principleCharacterString += hold;
                if (i != pcArray.size() - 1) {
                    principleCharacterString += ", ";
                }
            }
            principleCharacterString += "'";
            holder += seriesTitle + ", " + volumeNumber + ", " + issueNumber + ", " + publicationDate + ", "
                    + authorsString + ", " + publisher + ", " + principleCharacterString + ") ,";
            psuedoPath += holder + ",";
        }

        psuedoPath = psuedoPath.substring(0, psuedoPath.length() - 1);

        return psuedoPath;

    }

}
