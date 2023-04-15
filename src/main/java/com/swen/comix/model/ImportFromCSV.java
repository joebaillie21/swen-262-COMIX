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

public class ImportFromCSV implements Importer {
    final int SERIES = 0, ISSUE = 1, FULL_TITLE = 2, DESCRIPTION = 3, PUBLISHER = 4, PUBLICATION_DATE = 5, AUTHORS  = 8;

    private String CSVFileName; 

    public ImportFromCSV(String filename){
        this.CSVFileName = filename;
    }
    @Override
    public ArrayList<ComicBook> toArrayList() throws IOException {
        ArrayList<ComicBook> comics = new ArrayList<>();
        FileReader fr = new FileReader(CSVFileName); 
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(fr)
                                .withSkipLines(1)
                                .withCSVParser(parser)
                                .build();

        List<String[]> allData = csvReader.readAll();
        for(String [] row: allData){
            String seriesTitle = row[SERIES] + ": " + row[FULL_TITLE];
            String publicationDate = row[PUBLICATION_DATE]; 
            ArrayList<String> principleCharacters = new ArrayList<>(null);
            String description = row[DESCRIPTION]; 
            ArrayList<Author> authors = authorSplitter(row[AUTHORS]); 
            Publisher publisher = new Publisher(row[PUBLISHER]);
            ArrayList<String> IssueAndVol = splitIssueAndVolNum(row[ISSUE]); 
            String issueNumber = IssueAndVol.get(1);
            String volNum = IssueAndVol.get(0);


            // now creating the comic 
            ComicBookComponent cb = new ComicBookComponent(publisher, seriesTitle, volNum, issueNumber, publicationDate, authors, principleCharacters, description);
            comics.add(cb); 
        }
        return comics; 
    }

    /**
     * helper method to split up the author names since they are split by "|"
     * @param authors - raw data of list of authors in string form 
     * @return ArrayList<Author> - an array list of authors 
     */
    private ArrayList<Author> authorSplitter(String authors){
        String [] authorArr = authors.split("|");
        ArrayList<Author> authorAL = new ArrayList<>(); 
        for(int i = 0; i < authorArr.length; i++){
            Author auth = new Author(authorArr[i]);
            authorAL.add(auth);
        }
        return authorAL; 
    }
    
    /**
     * helper method for main method to delegate work. splits up issue number (raw data) from the issue and volume number 
     * @param rawIssueNum - raw data from the CSV  
     * @return ArrayList<String> containing volume number and then issue number 
     */
    private static ArrayList<String> splitIssueAndVolNum(String rawIssueNum){
        String[] temp = rawIssueNum.split("");
        String issueNum= "";
        String volNum = "";
        ArrayList<String> issueAndVol = new ArrayList<>();
        for(int i = 0; i < temp.length; i++){
            String t = temp[i];
            try{
                int num = Integer.parseInt(t);
                issueNum += t; 
            }catch(NumberFormatException e){
                volNum += t; 
            }
        }
        issueAndVol.add(volNum);
        issueAndVol.add(issueNum);
        return issueAndVol; 
    }

    public static void main(String [] args){
        String issueNum = "17B";
        System.out.println(splitIssueAndVolNum(issueNum));
    }
}
