package com.swen.comix.model;

import java.io.IOException;

/**
 * @Author Angela Ngo 
 */
public interface ComixMediator {
    public User login(String username, String password) throws IOException;
    public PersonalCollection getSpecificPersonalCollection(String username) throws IOException;
    public boolean isLoggedIn();
    public SignedInUser addUser(SignedInUser user) throws IOException;
}
