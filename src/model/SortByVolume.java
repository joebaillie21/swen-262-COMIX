package model;
import java.util.*;

public class SortByVolume implements SortStrategy{

    private PersonalCollection personalCollection;

    public SortByVolume(PersonalCollection pc){
        this.personalCollection = pc;
    }

    @Override
    public ArrayList<ComicBook> algorithm(String toBeSorted, boolean isSortDb) throws Exception {
        ArrayList<ComicBook> comics = new ArrayList<>();

        if (isSortDb == true) {
            comics = sortOnDb(toBeSorted);
        } else if (isSortDb == false) {
            comics = sortOnPC();
        }

        return comics;
    }

    private ArrayList<ComicBook> sortOnDb(String toBeSorted){
        // joe
        return null;
    }

    private ArrayList<ComicBook> sortOnPC(){

        ArrayList<ComicBook> pc = (ArrayList<ComicBook>)personalCollection.getPersonalCollection();
        Collections.sort(pc, SortByVolume.cbComparator);

        return null;
    }

    public static Comparator<ComicBook> cbComparator = new Comparator<ComicBook>(){
        @Override
        public int compare(ComicBook o1, ComicBook o2) {
            ComicBookComponent comic1 = (ComicBookComponent) o1;
            ComicBookComponent comic2 = (ComicBookComponent) o2;
            return String.valueOf(comic1.getVolNum()).compareTo(String.valueOf(comic2.getVolNum()));
        }
    };


    
}
