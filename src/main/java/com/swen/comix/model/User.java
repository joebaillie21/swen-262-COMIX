package com.swen.comix.model;

import java.util.ArrayList; 

/**
 * TODO
 * needs password in contructor
 * This is basically a signed in user. Not sure if we need both classes
 */
public class User {
    private String name;
    private PersonalCollection collection; 
    private SearchStrategy selectedSearch;
    private SortStrategy selectedSort; 
    
    public User(String name){
        this.name = name;
        this.collection = new PersonalCollection(name);
        this.selectedSearch = null; 
        this.selectedSort = null; 
    }

    /**
     * This is a setter for a general search strategy - this is used in receiveRequest() method
     * @param selectedSearch
     */
    public void setSearchStrategy(SearchStrategy selectedSearch){
        this.selectedSearch = selectedSearch; 
    }

    public void setSortStrategy(SortStrategy selectedSort){
        this.selectedSort = selectedSort; 
    }

    public String getName(){
        return name; 
    }

    public ArrayList<ComicBookComponent> search(String toBeSearched){
        //havent figured out logic yet for this 
        return new ArrayList<>(); 
    }

    public ArrayList<ComicBookComponent> sort(){
        return new ArrayList<>(); 
    }

    /**Not sure if this is necessary */
    public void receiveRequest(){

    }

    public PersonalCollection getPersonalCollection(){
        return collection;
    }

    /**
     * TEMPORARY TESTING PURPOSES DELETE LATER
     * @param collection
     */
    public void setCollection(PersonalCollection collection) {
        this.collection = collection;
    }
}
