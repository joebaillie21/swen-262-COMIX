package com.swen.comix.persistence;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.PersonalCollection;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class PersonalCollectionFileDAO implements PersonalCollectionDAO{
    
    TreeMap<String, PersonalCollection> collections;

    private String fileName;
    private ObjectMapper objectMapper;
    
    public PersonalCollectionFileDAO(String fileName, ObjectMapper objectMapper) throws IOException{
        this.fileName = fileName;
        this.objectMapper = objectMapper;
        load();
    }

    private boolean load() throws IOException{
        collections = new TreeMap<>();

        // Deserializes the JSON objects from the file into an array of Personal Collections
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        PersonalCollection[] collectionsArray = objectMapper.readValue(new File(fileName),PersonalCollection[].class);

        for (PersonalCollection collection : collectionsArray) {
            collections.put(collection.getUsername(),collection);
        }
        return true;
    }

    public List<PersonalCollection> getCollections() throws IOException{
        return new ArrayList<>(collections.values());
    }

    private boolean save() throws IOException {
        List<PersonalCollection> collectionArr = getCollections();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(fileName),collectionArr);
        return true;
    }

    @Override
    public void addComic(ComicBook comic, String username) throws IOException {
        // find the collection under the username given
        List<PersonalCollection> collectionArr = getCollections();
        for (PersonalCollection collectionInFile: collectionArr){
            if (username == collectionInFile.getUsername()){
                //add comic book
                collectionInFile.add(comic);
            }
        }
        save();
    }

    @Override
    public void removeComic(ComicBook comic, String username) throws IOException{
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeComic'");
    }

    @Override
    public void addCollection(String username, PersonalCollection collection) throws IOException{
        //PersonalCollection newCollection = new PersonalCollection(collection.getUsername());
        collections.put(username, collection);
        save();
    }
    
}
