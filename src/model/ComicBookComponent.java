package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComicBookComponent implements ComicBook {
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

    @Override
    public void gradeAlgorithm(ComicBook comic) {
        // work on this 
    }
    
}
