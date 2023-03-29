package com.swen.comix.persistence;

import java.io.IOException;

import com.swen.comix.model.ComicBook;
import com.swen.comix.model.PersonalCollection;

public interface PersonalCollectionDAO {
    /**
     * adds a comic book to the collection of the user
     * @param comic
     * @param username
     */
    public void addComic(ComicBook comic, String username) throws IOException;

    /**
     * adds a comic book to the collection of the user
     * @param comic
     * @param username
     */
    public void removeComic(ComicBook comic, String username) throws IOException;

    /**
     * adds a collection to the collections file
     * @param username
     */
    public void addCollection(String username, PersonalCollection colelction) throws IOException;

    
}
