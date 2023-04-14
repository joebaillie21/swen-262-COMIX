package com.swen.comix.model;

import java.util.ArrayList;

public interface ComicBook {
    public Publisher getPublisher(); 
    public ArrayList<Author> getAuthors();
    public String getSeriesTitle();
    public String getPublicationDate();
    public String getDescription();
    public String getVolNum();
    public String getIssueNumber();
    public int getGrade();
    public void setGrade(int newGrade);
    public void setValue(double value);
    public void setSignatures(int signatures);
    public int getSignatures();
    public ArrayList<String> getPrincipleCharacters();
    public boolean isAuthenticated();
    public double getValue();
}
