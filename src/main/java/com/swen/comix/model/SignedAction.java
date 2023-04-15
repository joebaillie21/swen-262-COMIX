package com.swen.comix.model;

import java.io.IOException;
import java.util.List;

import com.swen.comix.persistence.UserDAO;

/**
 * @Author Angela Ngo
 */
public class SignedAction implements Action{
    private SignedInUser user ;
    private ComicBook comic; 
    private UserDAO dao;
    private PersonalCollection pc;
    private int previousNum;
    private double previousValue;

    public SignedAction(SignedInUser user, UserDAO dao){
        this.user = user; 
        this.dao = dao;
        this.pc = user.getPersonalCollection();
    }

    @Override
    public void execute(ComicBook comic) throws IOException {
        this.comic = comic; 
        previousNum = ((ComicBookComponent)comic).getSignatures();
        previousValue = comic.getValue();
        pc.sign((ComicBookComponent)comic);
        dao.updateUser(user);
    }

    @Override
    public boolean isReversible() {
        return true;     
    }

    @Override
    public void unexecute() throws IOException {
        this.comic.setSignatures(previousNum);
        this.comic.setValue(previousValue);
        dao.updateUser(user);
    }   

    @Override
    public void redo() throws IOException {
        execute(comic);
    }
}
