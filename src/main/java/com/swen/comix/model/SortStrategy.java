package com.swen.comix.model;

import java.util.ArrayList;

/**@author Angela Ngo 
 * This is the interface for the sorts that we have
 */
public interface SortStrategy {
    public ArrayList<ComicBook> algorithm(ArrayList<ComicBook> searchResults) throws Exception; 
}
