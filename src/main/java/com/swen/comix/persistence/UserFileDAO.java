package com.swen.comix.persistence;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.ComicBookComponent;
import com.swen.comix.model.SignedInUser;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFileDAO implements UserDAO{
    TreeMap<String,SignedInUser> users;   // Provides a local cache of the user objects
                                // so that we don't need to read from the file
                                // each time
                                
    private ObjectMapper objectMapper;  // Provides conversion between User
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and w
    public UserFileDAO(String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the users from the file
    }

    /**
     * called when this DAO is created. Loads the existing data from the users json file into the local map
     * @return boolean to comfirm successful load
     * @throws IOException
     */
    private boolean load() throws IOException {
        users = new TreeMap<>();

        // Deserializes the JSON objects from the file into an array of users
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SignedInUser[] userArray = objectMapper.readValue(new File(filename),SignedInUser[].class);
       
        for (SignedInUser user : userArray) {
            users.put(user.getName(),user);
        }
        return true;
    }
    
    /**
     * Converts the mapping of users into a list of users for use in other functions
     * @return List of users
     * @throws IOException
     */
    public List<SignedInUser> getUsers() throws IOException{
        return new ArrayList<>(users.values());
    }

    private boolean save() throws IOException {
        List<SignedInUser> userList = getUsers();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),userList);
        return true;
    }

    @Override
    public SignedInUser createUser(SignedInUser user) throws IOException{
        SignedInUser newUser = new SignedInUser(user.getName(), user.getPassword());
        List<SignedInUser> userArr = getUsers();
        for(SignedInUser userInFile: userArr){
            if(userInFile.getName().equals(user.getName())){
                throw new IllegalArgumentException("User already exists");
            }
        }
        users.put(newUser.getName(),newUser);
        save(); // may throw an IOException
        return newUser;
    }

    @Override
    public SignedInUser getUser(String userName) throws IOException{
        List<SignedInUser> userArr = getUsers();
        for(SignedInUser userInFile: userArr){
            if(userInFile.getName().equals(userName)){
                return userInFile;
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    /**
     * updates the user given in the file. called whenever something is added to the database, or some change is made to the comics in a collection
     * @param user the user in the file updated
     * @return the updated user object
     * @throws IOException
     */
    @Override
    public SignedInUser updateUser(SignedInUser user) throws IOException {
        if (users.containsKey(user.getName()) == false)
            return null;  // user does not exist
        users.put(user.getName(),user);
        save(); // may throw an IOException
        return user;
    }
    
    @Override
    public SignedInUser userAuthentication(String userName, String password) throws IOException{
        List<SignedInUser> userList = getUsers();
        for(SignedInUser userInFile: userList){
            if(userInFile.getName().equals(userName) && userInFile.getPassword().equals(password)){
                return userInFile;
            }
        }
        throw new IllegalArgumentException("User not found");
    }


    /**
     * Adds comic to the certain users personal collection 
     * @param comic ComicBook- the comic book to be added to the personal collection 
     * @param username String - the users collection to add the comic to 
     */
    @Override
    public void addComic(ComicBook comic, String username) throws IOException {
        // find the user under the username given
        List<SignedInUser> userList = getUsers();
        for (SignedInUser userInFile: userList){
            if (username == userInFile.getName()){
                //add comic book
                userInFile.getPersonalCollection().add((ComicBookComponent) comic);
            }
        }
        save();
    }

    /**
     * Removes a comic from the personal collection from given username 
     * @param comic ComicBook - the comic book to remove from the personal collection 
     */
    @Override
    public void removeComic(ComicBook comic, String username) throws IOException{
        // find the user under the username given
        List<SignedInUser> userList = getUsers();
        for (SignedInUser userInFile: userList){
            if (username == userInFile.getName()){
                //remove comic book
                userInFile.getPersonalCollection().remove(comic);
            }
        }
        save();
    }

    public void updateSlab(ComicBook comic, String username, boolean updatedSlab) throws IOException{
        List<SignedInUser> userList = getUsers();
        for (SignedInUser userInFile: userList){
            if (username == userInFile.getName()){
                PersonalCollection pc = userInFile.getPersonalCollection();
                comic.setSlabbed(updatedSlab);
                pc.slab(comic);
            }
        }
        save();
    }

    public void updateSigned(ComicBook comic, String username, int updatedSigned) throws IOException{
        List<SignedInUser> userList = getUsers();
        for (SignedInUser userInFile: userList){
            if (username == userInFile.getName()){
                PersonalCollection pc = userInFile.getPersonalCollection();
                comic.setSignatures(updatedSigned);
                pc.slab(comic);
            }
        }
        save();
    }

    public void updateGrade(ComicBook comic, String username, int updatedGrade)throws IOException{
        List<SignedInUser> userList = getUsers();
        for (SignedInUser userInFile: userList){
            if (username == userInFile.getName()){
                PersonalCollection pc = userInFile.getPersonalCollection();
                comic.setGrade(updatedGrade);
                pc.slab(comic);
            }
        }
        save();
    }

    public void updateValue(ComicBook comic, String username, double updatedValue)throws IOException{
        List<SignedInUser> userList = getUsers();
        for (SignedInUser userInFile: userList){
            if (username == userInFile.getName()){
                PersonalCollection pc = userInFile.getPersonalCollection();
                comic.setValue(updatedValue);
                pc.slab(comic);
            }
        }
        save();
    }
}
