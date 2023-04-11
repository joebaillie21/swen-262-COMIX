package com.swen.comix.persistence;

import java.io.IOException;

import com.swen.comix.model.ComicBook;
import com.swen.comix.model.User;

public interface UserDAO {
    /**
     * searches the json file for an existing user with the given name. Will throw an illegal argument exception if user is not in the file
     * @param userName username used to check the file
     * @return the user found in the file
     * @throws IOException
     */
    public User getUser(String userName) throws IOException;

    /**
     * searched the json file for an existing user with the matching username AND password (TODO PASSWORD NOT CHECKED YET). throws illegal argument exception if not found
     * @param userName username checked
     * @param password password checked
     * @return the user with the given credentials
     * @throws IOException
     */
    public User userAuthentication(String userName, String password) throws IOException;

    /**
     * creates a new user. checks to make sure user does not already exist
     * @param user a new user object created before the call of this function
     * @return the new user object
     * @throws IOException
     */
    public User createUser(User user) throws IOException;

    /**
     * adds a given comic to the user's collection with the given username 
     * @param comic a comic object serialized from the database or with information entered mannualy
     * @param username the username used to check the file
     * @throws IOException
     */
    public void addComic(ComicBook comic, String username) throws IOException; //TESTING DELETE LATER

    /**
     * removes a given comic from the user's collection with the given username
     * @param comic a comic object serialized from the database or with information entered mannualy
     * @param username the username used to check the file
     * @throws IOException
     */
    public void removeComic(ComicBook comic, String username) throws IOException;
}
