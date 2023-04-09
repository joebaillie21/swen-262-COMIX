package com.swen.comix.persistence;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserFileDAO implements UserDAO{
    TreeMap<String,User> users;   // Provides a local cache of the user objects
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
       User[] userArray = objectMapper.readValue(new File(filename),User[].class);

        for (User user : userArray) {
            users.put(user.getName(),user);
        }
        return true;
    }
    
    /**
     * Converts the mapping of users into a list of users for use in other functions
     * @return List of users
     * @throws IOException
     */
    public List<User> getUsers() throws IOException{
        return new ArrayList<>(users.values());
    }

    private boolean save() throws IOException {
        List<User> userList = getUsers();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),userList);
        return true;
    }

    @Override
    public User createUser(User user) throws IOException{
        User newUser = new User(user.getName());
        List<User> userArr = getUsers();
        for(User userInFile: userArr){
            if(userInFile.getName().equals(user.getName())){
                throw new IllegalArgumentException("User already exists");
            }
        }
        users.put(newUser.getName(),newUser);
        save(); // may throw an IOException
        return newUser;
    }

    @Override
    public User getUser(String userName) throws IOException{
        List<User> userArr = getUsers();
        for(User userInFile: userArr){
            System.out.println(userInFile.getName());
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
    public User updateUser(User user) throws IOException {
        if (users.containsKey(user.getName()) == false)
            return null;  // user does not exist

        users.put(user.getName(),user);
        save(); // may throw an IOException
        return user;
    }
    
    @Override
    public User userAuthentication(String userName, String password) throws IOException{
        List<User> userList = getUsers();
        for(User userInFile: userList){
            if(userInFile.getName().equals(userName)){
                return userInFile;
            }
        }
        throw new IllegalArgumentException("User not found");
    }


    @Override
    public void addComic(ComicBook comic, String username) throws IOException {
        // find the user under the username given
        List<User> userList = getUsers();
        for (User userInFile: userList){
            if (username == userInFile.getName()){
                //add comic book
                userInFile.getPersonalCollection().add(comic);
            }
        }
        save();
    }

    @Override
    public void removeComic(ComicBook comic, String username) throws IOException{
        // find the user under the username given
        List<User> userList = getUsers();
        for (User userInFile: userList){
            if (username == userInFile.getName()){
                //remove comic book
                userInFile.getPersonalCollection().remove(comic);
            }
        }
        save();
    }
}
