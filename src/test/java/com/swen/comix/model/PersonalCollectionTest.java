package com.swen.comix.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.persistence.PersonalCollectionDAO;
import com.swen.comix.persistence.PersonalCollectionFileDAO;

public class PersonalCollectionTest {

    /**
     * TODO
     * Does not test anything yet, just checks for errors
     * @throws IOException
     */
    @Test
    public void testAddManual() throws IOException{
        File datafile = new File("src/data/temp.json");
        if (datafile.exists()) {
            datafile.delete();
        }
        
        FileWriter writer = new FileWriter(datafile);
        writer.write("[]");
        writer.close();

        PersonalCollection collection = new PersonalCollection("user");
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, null, new Author("Stan Lee"), null, null);
        collection.add(comic);
        ObjectMapper mockMapper = new ObjectMapper();
        PersonalCollectionDAO collectionDAO = new PersonalCollectionFileDAO("src/data/temp.json", mockMapper);

        collectionDAO.addCollection(collection.getUsername(), collection);  
        
        ComicBook comic2 = new ComicBookComponent(new Publisher("DC"), "Batman", 0, 0, null, new Author("A Bat"), null, null);
        collectionDAO.addComic(comic2, collection.getUsername());

        datafile.delete();
    }
}
