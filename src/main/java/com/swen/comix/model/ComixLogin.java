package com.swen.comix.model;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.persistence.UserDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @Author Angela Ngo
 */
public class ComixLogin implements ComixMediator {
    private boolean isLoggedIn;
    private UserDAO userDAO;

    public ComixLogin(UserDAO userDAO){
        isLoggedIn = false;
        this.userDAO = userDAO;
    }
    /**
     * String username - the key of the hashmap 
     * String password - the password of the user 
     * return a user from the existing users in file
     * @throws IOException
     */
    @Override
    public User login(String username, String password) throws IOException {
        // calls the File DAO to search the file to see if the parameters match an existing user
        return userDAO.userAuthentication(username, password);
    }

    @Override
    public boolean isLoggedIn(){
        return isLoggedIn;
    }

    // need to be able to access the specific personal collection

    /**
     * this allows guest to get a specific personal collection from the 
     * hashmap that stores the users information and their personal collection
     * @param username - username of collection want to look through
     * @return personal collection of the specified user 
     * @throws IOException
     */
    @Override
    public PersonalCollection getSpecificPersonalCollection(String username) throws IOException{
        //return (PersonalCollection) userLogins.get(username).get(PERSONAL_COLLECTION);
        return userDAO.getUser(username).getPersonalCollection();
    }


    /**
     * This will add the user to the users.json and save the data 
     * @param user - A type of signed in user with a password 
     * @throws IOException - due to the use of file writer 
     */
    @Override
    public SignedInUser addUser(SignedInUser user) throws IOException{
        //Calls file DAO to create a new user with the given info and returns it
        return userDAO.createUser(user);
    }
}
