package com.swen.comix.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalCollection {   
    private List<ComicBook> personalCollection;

    private String username;

    static final String STRING_FORMAT = "Collection [userName=%s, collection=%s]";

    public PersonalCollection(@JsonProperty("name") String username){
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
        boolean output = this.personalCollection.remove(comic);
        if(output!= true){
            System.out.println("Comic doesn't exist in personal collection."); 
        }
    }

    public void grade(ComicBookComponent comic){
        ComicBook gradedComic = new Grade(comic);
        comic.setValue(gradedComic.getValue());
    }

    public void slab(ComicBookComponent comic){
        if (comic.getGrade() != 1){
            ComicBook slabbedComic = new Slab(comic);
            comic.setValue(slabbedComic.getValue());
        }
    }

    public void sign(ComicBook comic){
        ComicBook signedComic = new Signed(comic);
        comic.setValue(signedComic.getValue());
    }

    public void authenticate(ComicBook comic){
        if (comic.getSignatures() != 0){
            if (!comic.isAuthenticated()){
                ComicBook authenticatedComic = new Authenticate(comic);
                comic.setValue(authenticatedComic.getValue());
            }
        }
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
