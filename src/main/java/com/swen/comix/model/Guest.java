package com.swen.comix.model;

import java.io.IOException;

/**
 * @Author Angela Ngo
 * every user(literal) is a guest at startup until they login with correct credentials
 * They have access to these functions only until they log in
 */
public class Guest{
    private PersonalCollection currentSelectedPC;
    private ComixMediator CL; 
    public Guest(ComixMediator CL) {
        this.currentSelectedPC = null; 
        this.CL = CL; 
    }

    /**
     * allows for a guest to search for a personal collection within made users
     * @param name - name of the collection they want to get 
     * @throws IOException
     */
    public void searchForPersonalCollection(String name) throws IOException{
        this.currentSelectedPC = CL.getSpecificPersonalCollection(name); 
    }
    
    public PersonalCollection getCurrentlySelectedPC(){
        return currentSelectedPC;
    }

    /**
     * calls the login function from the mediator (comixLogin). if the guest has already called this function with correct credentials, this function will not work and throw an illegal argument exception
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public User login(String username, String password) throws IOException{
        if (CL.isLoggedIn()){
            return CL.login(username, password);
        }
        throw new IllegalArgumentException();
    }

    /**
     * calls the create account function from the mediator (comixLogin). If user with given username and password exists, will throw illegal argument exception
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public User createAccount(String username, String password) throws IOException{
        User user = new User(username);
        return this.CL.addUser(user);
    }
}
