package com.swen.comix.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComicBookComponent implements ComicBook {
    static final String STRING_FORMAT = "[Publisher=%s, Author=%s, Title=%s, Description=%s, VolNum=%s, IssueNum=%s, Characters=%s]";

    private Publisher publisher; 
    private Author author; 
    private String seriesTitle, publicationDate, description; 
    private int volNum, issueNum, grade; 
    private ArrayList<String> principleCharacters, signatures; 
    private boolean isAuthenticated; 
    private double value; 


    public ComicBookComponent(Publisher publisher, String seriesTitle, int volNum, int issueNum, String publicationDate, Author author, ArrayList<String> principleCharacters, String description){
        this.publisher = publisher; 
        this.author = author; 
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

    public Publisher getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public int getVolNum() {
        return volNum;
    }

    public int getIssueNum() {
        return issueNum;
    }

    public int getGrade() {
        return grade;
    }

    public ArrayList<String> getPrincipleCharacters() {
        return principleCharacters;
    }

    public ArrayList<String> getSignatures() {
        return signatures;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void gradeAlgorithm(ComicBook comic) {
        // work on this 
    }

    public String toString(){
        return String.format(STRING_FORMAT, publisher.getName(), author.getName(), seriesTitle, description, volNum, issueNum, principleCharacters);
    }
}
