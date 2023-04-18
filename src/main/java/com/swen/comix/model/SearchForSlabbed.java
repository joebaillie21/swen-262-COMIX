package com.swen.comix.model;

import java.util.ArrayList;
/**
 * @Author Angela Ngo 
 * This searches through the personal collection and gets an arraylist of 
 * comicbooks that are slabbed 
 */
public class SearchForSlabbed implements SearchStrategy{
    private PersonalCollection personalCollection;

    public SearchForSlabbed(PersonalCollection personalCollection){
        this.personalCollection = personalCollection;
    }

    /**
     * gets array list of comicbooks from personal collection containing slabbed comics s
     * @param toBeSearched - will be empty or null, not needed for this particular method 
     * @param isSearchDb - boolean will always be false for this , this is on personal 
     * collection only
     * @return ArrayList<ComicBook> - arraylist of comic books containing only slabbed comics from personal 
     * collection 
     */
    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        
        if(isSearchDb == false){ // this wil almost always be false 
            comics = searchOnPC(); 
        }
        return comics; 
    }

    /**
     * Helper method to find all the comic books that are slabbed in the personal collection 
     * @return ArrayList<ComicBook> - this will
     */
    private ArrayList<ComicBook> searchOnPC(){
        ArrayList<ComicBookComponent> pc = (ArrayList<ComicBookComponent>) personalCollection.getPersonalCollection();
        ArrayList<ComicBook> comics = new ArrayList<>(); 

        for(int i = 0; i < pc.size(); i++){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getSlabbed() == true){ // if they are slabbed
                comics.add(comic); 
            }
        }

        return comics;
    }
    
}