package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo
 * This is only applicable for the personal collection therefore
 */
public class SearchByIssueNumber implements SearchStrategy {
    private String toBeSearched;
    private PersonalCollection personalCollection; 
    final int NUM_RESULTS = 10; 

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
        int currCount = 0;

        for (int i = 0; i < pc.size(); i++) {
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if (comic.getDescription().equals(toBeSearched) && currCount != NUM_RESULTS) {
                comics.add(pc.get(i));
                currCount++;
            }
            if (currCount == NUM_RESULTS) {
                return comics;
            }

        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics;
    }
}
