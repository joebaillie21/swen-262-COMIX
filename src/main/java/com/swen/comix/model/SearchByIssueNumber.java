package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo
 * This is only applicable for the personal collection therefore
 */
public class SearchByIssueNumber implements SearchStrategy {

    private PersonalCollection personalCollection; 


    public SearchByIssueNumber(PersonalCollection personalCollection){
        this.personalCollection = personalCollection; 
    }


    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        // turn the String into an int 
        int issueNum = Integer.parseInt(toBeSearched);

        if(isSearchDb == false){
            comics = searchOnPC(issueNum);
        }
        return comics;
    }

    private ArrayList<ComicBook> searchOnPC(int toBeSearched){
        ArrayList<ComicBook> comics = new ArrayList<>();
        ArrayList<ComicBook> pc = (ArrayList<ComicBook>)personalCollection.getPersonalCollection();
        

        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getDescription().equals(toBeSearched)) {
                comics.add(pc.get(i));
                
            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }
}
