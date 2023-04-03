package model;

import java.util.ArrayList;

/**
 * @Author Angela and Joe 
 */
public class SearchByDescription implements SearchStrategy {
    private String toBeSearched;
    private PersonalCollection personalCollection; 
    final int NUM_RESULTS = 10;

    public SearchByDescription(PersonalCollection personalCollection){
        this.personalCollection = personalCollection; 
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSearched, boolean isSearchDb) {
        ArrayList<ComicBook> comics = new ArrayList<>();
        
        if(isSearchDb == true){
            comics = searchOnDb(toBeSearched);
        }else if(isSearchDb == false){
            comics = searchOnPC(toBeSearched); 
        } 
        return comics;
    }

    private ArrayList<ComicBook> searchOnDb(String toBeSearched){
        // joe implementation
    }

    private ArrayList<ComicBook> searchOnPC(String toBeSearched){
        ArrayList<ComicBook> comics = new ArrayList<>(); 
        ArrayList<ComicBook> pc = personalCollection.getPersonalCollection(); 
        int currCount = 0; 
        
        for(int i = 0; i < pc.size(); i++){
            ComicBookComponent comic = (ComicBookComponent) pc.get(i);
            if(comic.getDescription().equals(toBeSearched) && currCount != NUM_RESULTS){
                comics.add(pc.get(i)); 
                currCount++;
            }
            if(currCount == NUM_RESULTS){
                return comics; 
            }
        
        }
        // return the ones even if it doesn reach the max NUM_COUNT
        return comics; 
    }
}
