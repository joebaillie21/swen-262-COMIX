package model;
import java.util.*;

public class SortBySeriesTitle implements SortStrategy{

    private PersonalCollection personalCollection;

    public SortBySeriesTitle(PersonalCollection pc){
        this.personalCollection = pc;
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSorted, boolean isSortDb) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'algorithm'");
    }
    
}
