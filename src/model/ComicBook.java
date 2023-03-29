package model;

import java.util.ArrayList;

public interface ComicBook {
    public Publisher getPublisher(); 
    public ArrayList<Author> getAuthors();
    public String getSeriesTitle();
    public String getPublicationDate();
    public String getDescription();
    public int getVolNum();
    public int getIssueNum();
    public int getGrade();
    public ArrayList<String> getPrincipleCharacters();
    public boolean getAuthenticated();
    public double getValue();
}
