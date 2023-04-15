package com.swen.comix.model;

import java.io.IOException;

import com.swen.comix.persistence.UserDAO;

public class RemoveAction implements Action {
    private SignedInUser user ;
    private ComicBook comic; 
    private UserDAO dao;
    private PersonalCollection pc;

    public RemoveAction(SignedInUser user, UserDAO dao){
        this.user = user; 
        this.dao = dao;
        this.pc = user.getPersonalCollection();
    }

    @Override
    public void execute(ComicBook comic) throws IOException {
        pc.remove(comic);
        this.comic = comic;
        dao.updateUser(user);
    }

    @Override
    public boolean isReversible() {
        return true;
    }

    @Override
    public void unexecute() throws IOException {
        pc.add(comic); 
        dao.updateUser(user);
    }

    @Override
    public void redo() throws IOException {
        execute(comic);
    }
}
