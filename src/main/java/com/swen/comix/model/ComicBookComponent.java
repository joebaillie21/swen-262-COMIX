package com.swen.comix.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

    /**
     * @Author Angela Ngo
     */
public class ComicBookComponent implements ComicBook {
    static final String STRING_FORMAT = "[Publisher=%s, Author=%s, Title=%s, Description=%s, VolNum=%s, IssueNum=%s, Characters=%s]";

    private Publisher publisher; 
    private ArrayList<Author> authors; 
    private String seriesTitle, publicationDate, description; 
    private int volNum, issueNum, grade, signatures; 
    private ArrayList<String> principleCharacters; 
    private boolean isAuthenticated, isSlabbed; 
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
        this.signatures = 0;
        this.grade = 0; // smallest grade is 1 not 0 
        this.value = -1;
        isAuthenticated = false; 
        isSlabbed = false; 
    }

    @Override
    public void gradeAlgorithm() {
        // work on this 
    }

    // need to do something about the grading stuff since it has to directly impact the comicbook components
    public boolean isAuthenticated(){return isAuthenticated;}

    public String getDescription(){ return description;}

    public ArrayList<String> getPrincipleCharacters(){ return principleCharacters;}

    public int getSignatures(){return signatures;}

    public String getSeriesTitle(){return seriesTitle;}
    
    public Publisher getPublisher(){return publisher;}

    public int getVolNum(){return volNum;}

    public int getIssueNum(){return issueNum;}
    
    public int getGrade(){return grade;}

    public double getValue(){return value;}

    public ArrayList<Author> getAuthors(){return authors;}

    public String getPublicationDate(){return publicationDate; }

    public boolean getSlabbed(){return isSlabbed;}
    public void setAuthentication(boolean isAuthenticated){
        this.isAuthenticated = isAuthenticated; 
    }

    public void setGrade(int grade){
        this.grade = grade; 
    }

    public void setValue(int value){
        this.value = value; 
    }

    public void setSlabbed(boolean isSlabbed){
        this.isSlabbed = isSlabbed; 
    }
    
    public boolean equals(Object o){ 
        if(!(o instanceof ComicBookComponent)){
            return false;
        }

        ComicBookComponent other = (ComicBookComponent) o; 
        for(int i = 0; i < other.getPrincipleCharacters().size(); i++){
            if(!other.getPrincipleCharacters().get(i).equals(principleCharacters.get(i))){
                return false; 
            }
        }

        for(int k = 0; k < other.getAuthor().size(); k++){
            if(!other.getAuthor().get(k).equals(author.get(k))){
                return false; 
            }
        }

        return (
            other.getAuthors().equals(authors) && other.getPublicationDate().equals(publicationDate) && other.getValue() == value && other.getGrade() == grade
            && other.getDescription().equals(description) && other.getIssueNum() == issueNum && other.getVolNum() == volNum && other.getPublisher().getName().equals(publisher.getName())
            && other.isAuthenticated() == isAuthenticated && other.getSeriesTitle().equals(seriesTitle) && other.getSlabbed() == isSlabbed
        );

    }

    public String toString(){
        return String.format(STRING_FORMAT, publisher.getName(), authors.toString(), seriesTitle, description, volNum, issueNum, principleCharacters);
    }
}
