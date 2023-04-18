package com.swen.comix.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import oracle.net.jdbc.TNSAddress.Description;

/**
 * @Author Angela Ngo
 *         this takes in a CSV file and then it translates it to be an arraylist
 */
public class ImportFromCSV implements Importer {
    final int SERIES = 0, ISSUE = 1, FULL_TITLE = 2, DESCRIPTION = 3, PUBLISHER = 4, PUBLICATION_DATE = 5, AUTHORS = 8;
    final String DEFAULT_VOL = " Vol. 1";
    final String DEFAULT_VAL_IF_EMPTY = "N/A";
    final private String CSVFileName;

    public ImportFromCSV(String filename) {
        this.CSVFileName = filename;
    }

    @Override
    public ArrayList<ComicBookComponent> importToJava() throws IOException {
        ArrayList<ComicBookComponent> comics = new ArrayList<>();
        FileReader fr = new FileReader(CSVFileName);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(fr)
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();

        List<String[]> allData = csvReader.readAll();
        for (String[] row : allData) {
            if (!row[SERIES].equals("")) {
                String[] seriesAndVol = row[SERIES].split(",");
                String volNum = "";
                if (seriesAndVol.length != 2) {
                    volNum = DEFAULT_VOL;
                } else {
                    volNum = seriesAndVol[1];
                }
                String title = "";
                if (row[FULL_TITLE].equals("")) {
                    title = DEFAULT_VAL_IF_EMPTY;
                } else if (!row[FULL_TITLE].equals("")) {
                    title = row[FULL_TITLE];
                }
                String seriesTitle = seriesAndVol[0] + ": " + title;

                String publicationDate = row[PUBLICATION_DATE];
                ArrayList<String> principleCharacters = new ArrayList<>();
                String description = "";
                if (row[DESCRIPTION].equals("")) {
                    description = DEFAULT_VAL_IF_EMPTY;
                } else if (!row[DESCRIPTION].equals("")) {
                    description = row[DESCRIPTION];
                }
                ArrayList<Author> authors = authorSplitter(row[AUTHORS]);
                Publisher publisher = new Publisher(row[PUBLISHER]);
                String issueNumber = row[ISSUE];

                // now creating the comic // description and full title
                ComicBookComponent cb = new ComicBookComponent(publisher, seriesTitle, volNum, issueNumber,
                        publicationDate, authors, principleCharacters, description);
                comics.add(cb);
            }

        }
        return comics;
    }

    /**
     * helper method to split up the author names since they are split by "|"
     * 
     * @param authors - raw data of list of authors in string form
     * @return ArrayList<Author> - an array list of authors
     */
    private ArrayList<Author> authorSplitter(String authors) {
        String[] authorArr = authors.split("|");
        ArrayList<Author> authorAL = new ArrayList<>();
        for (int i = 0; i < authorArr.length; i++) {
            Author auth = new Author(authorArr[i]);
            authorAL.add(auth);
        }
        return authorAL;
    }

    public static void main(String[] args) throws IOException {
        ImportFromCSV imCSV = new ImportFromCSV("src\\data\\comics(1).csv");
        ArrayList<ComicBookComponent> comics = imCSV.importToJava();
        System.out.println(comics.size());
        System.out.println(comics);
    }

}