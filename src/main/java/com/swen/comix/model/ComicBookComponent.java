package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

    /**
     * @Author Angela Ngo
     */
public class ComicBookComponent implements ComicBook {
    private Publisher publisher; 
    private Author author; 
    private String seriesTitle, publicationDate, description; 
    private int volNum, issueNum, grade, signatures; 
    private ArrayList<String> principleCharacters; 
    private boolean isAuthenticated, isSlabbed; 
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
        this.signatures = 0;
        this.grade = -1; 
        this.value = -1; 
        isAuthenticated = false; 
        isSlabbed = false; 
    }

    @Override
    public void gradeAlgorithm(ComicBook comic) {
        // work on this 
    }

    // need to do something about the grading stuff since it has to directly impact the comicbook components
    public boolean isAuthenticated(){return isAuthenticated;}

    public String getDescription(){ return description;}

    public ArrayList<String> getPrincipleCharacter(){ return principleCharacters;}

    public int getSignature(){return signatures;}

    public String getSeriesTitle(){return seriesTitle;}
    
    public Publisher getPublisher(){return publisher;}

    public int getVolNum(){return volNum;}

    public int getIssueNum(){return issueNum;}
    
    public int getGrade(){return grade;}

    public double getValue(){return value;}

    public Author getAuthor(){return author;}

    public String getPubDate(){return publicationDate; }

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
    @Override
    public boolean equals(Object o){ 
        if(!(o instanceof ComicBookComponent)){
            return false;
        }
        
        ComicBookComponent other = (ComicBookComponent) o; 
        for(int i = 0; i < other.getPrincipleCharacter().size(); i++){
            if(!other.getPrincipleCharacter().get(i).equals(principleCharacters.get(i))){
                return false; 
            }
        }

        return (
            other.getAuthor().getName().equals(author.getName()) && other.getPubDate().equals(publicationDate) && other.getValue() == value && other.getGrade() == grade
            && other.getDescription().equals(description) && other.getIssueNum() == issueNum && other.getVolNum() == volNum && other.getPublisher().getName().equals(publisher.getName())
            && other.isAuthenticated() == isAuthenticated && other.getSeriesTitle().equals(seriesTitle) && other.getSlabbed() == isSlabbed
        );
        
    }
}
