package com.swen.comix.model;

import java.io.IOException;

import com.swen.comix.persistence.UserDAO;

/**
 * @Author Angela Ngo
 */
public class AuthenticatedAction implements Action{
    private SignedInUser user ;
    private ComicBook comic; 
    private UserDAO dao;
    private PersonalCollection pc;
    private double previousValue;

    public AuthenticatedAction(SignedInUser user, UserDAO dao){
        this.user = user; 
        this.dao = dao;
        this.pc = user.getPersonalCollection();
    }

    @Override
    public void execute(ComicBook comic) throws IOException {
        this.comic = comic; 
        this.previousValue = comic.getValue();
        pc.authenticate((ComicBookComponent) comic);
        dao.updateUser(user);
    }

    @Override
    public boolean isReversible() {
        return true; 
    }

    @Override
    public void unexecute() throws IOException {
        this.comic.setValue(previousValue);
        dao.updateUser(user);
    }
    
    @Override
    public void redo() throws IOException {
        execute(comic);
    }
}
