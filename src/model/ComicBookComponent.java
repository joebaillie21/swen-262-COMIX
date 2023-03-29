package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComicBookComponent implements ComicBook {
    private Publisher publisher; 
    private ArrayList<Author> authors; 
    private String seriesTitle, publicationDate, description; 
    private int volNum, issueNum, grade; 
    private ArrayList<String> principleCharacters, signatures; 
    private boolean isAuthenticated; 
    private double value; 


    public ComicBookComponent(Publisher publisher, String seriesTitle, int volNum, int issueNum, String publicationDate, ArrayList<Author> authors, ArrayList<String> principleCharacters, String description){
        this.publisher = publisher; 
        this.authors = authors; 
        this.seriesTitle = seriesTitle; 
        this.volNum = volNum;
        this.issueNum = issueNum;  
        this.publicationDate = publicationDate; 
        this.principleCharacters = principleCharacters; 
        this.description = description; 
        this.signatures = new ArrayList<String>();
        this.grade = 0; 
        this.value = 0; 
        isAuthenticated = false; 
    }


    @Override
    public Publisher getPublisher() {
        return this.publisher;
    }


    @Override
    public ArrayList<Author> getAuthors() {
        return this.authors;
    }


    @Override
    public String getSeriesTitle() {
        return this.seriesTitle;

    }


    @Override
    public String getPublicationDate() {
        return this.publicationDate;
    }


    @Override
    public String getDescription() {
        return this.description;
    }


    @Override
    public int getVolNum() {
        return this.volNum;
    }


    @Override
    public int getIssueNum() {
        return this.issueNum;
    }


    @Override
    public int getGrade() {
        return this.grade;
    }


    @Override
    public ArrayList<String> getPrincipleCharacters() {
        return this.principleCharacters;
    }


    @Override
    public boolean getAuthenticated() {
        return this.isAuthenticated;
    }


    @Override
    public double getValue() {
        return this.value;
    }
    
}
