package com.swen.comix.model;

import java.util.ArrayList; 


public class User {
    private String name;
    private PersonalCollection collection; 
    private SearchStrategy selectedSearch;
    private SortStrategy selectedSort; 
    
    public User(){}
    
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
        return this.name; 
    }

    public ArrayList<ComicBook> search(String toBeSearched, boolean isDatabase) throws Exception{
        ArrayList<ComicBook> result = this.selectedSearch.algorithm(toBeSearched, isDatabase);
        return result; 
    }

    public ArrayList<ComicBook> sort(ArrayList<ComicBook> result) throws Exception{
        ArrayList<ComicBook> sorted = this.selectedSort.algorithm(result);
        return sorted; 
    }

    /**Not sure if this is necessary */
    public void receiveRequest(){

    }

    public PersonalCollection getPersonalCollection(){
        return this.collection;
    }

    /**
     * TEMPORARY TESTING PURPOSES DELETE LATER
     * @param collection
     */
    public void setCollection(PersonalCollection collection) {
        this.collection = collection;
    }
}
