package model;
import java.util.ArrayList;

/**
 * @Author Peyton
 */

public interface SortStrategy {
    public ArrayList<ComicBook> algorithm(String toBeSorted, boolean isSortDb) throws Exception;
}
