package com.swen.comix.model;

import java.util.ArrayList;
import java.util.List;

public class PersonalCollection {   
    private List<ComicBook> personalCollection;

    private String username;

    static final String STRING_FORMAT = "Collection [userName=%s, collection=%s]";

    public PersonalCollection(String username){
        this.username = username;
        this.personalCollection = new ArrayList<ComicBook>();
    }

    public String getUsername() {
        return username;
    }

    public void add(ComicBook comic){
        this.personalCollection.add(comic);
    }

    public void remove(ComicBook comic){
        this.personalCollection.remove(comic);
    }

    public void grade(ComicBookComponent comic){
        ComicBook gradedComic = new Grade(comic);
        comic.setValue(gradedComic.getValue());
    }

    public void slab(ComicBook comic){
        // TO DO
    }

    public void sign(ComicBook comic){
        // TO DO
    }

    public void authenticate(ComicBook comic){

    }
    
    public List<ComicBook> getPersonalCollection() {
        return personalCollection;
    }

    public void setPersonalCollection(List<ComicBook> personalCollection) {
        this.personalCollection = personalCollection;
    }

    @Override
    public String toString(){
        return String.format(STRING_FORMAT, username, personalCollection.toString());
    }
    
}
