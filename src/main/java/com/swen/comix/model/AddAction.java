package com.swen.comix.model;

import java.io.IOException;

import com.swen.comix.persistence.UserDAO;

public class AddAction implements Action {
    private SignedInUser user ;
    private ComicBook comic; 
    private UserDAO dao;
    private PersonalCollection pc;

    public AddAction(SignedInUser user, UserDAO dao){
        this.user = user; 
        this.dao = dao;
        this.pc = user.getPersonalCollection();
    }
    @Override
    public void execute(ComicBook comic) throws IOException {
        pc.add((ComicBookComponent)comic); 
        this.comic = comic; 
        dao.updateUser(user);
    }

    @Override
    /**
     * The adding action is reversible so it will be 
     */
    public boolean isReversible() {
        return true;
    }

    @Override
    /**
     * this unexecutes the specific add command
     */
    public void unexecute() throws IOException {
        pc.remove((ComicBookComponent)comic); 
        dao.updateUser(user);
    }

    @Override
    public void redo() throws IOException {
        execute(comic);
    }
}
