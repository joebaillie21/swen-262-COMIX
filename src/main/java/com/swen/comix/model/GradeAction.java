package com.swen.comix.model;

import java.io.IOException;
import java.util.List;

import com.swen.comix.persistence.UserDAO;

/**
 * @Author Angela Ngo
 */
public class GradeAction implements Action{
    private SignedInUser user ;
    private ComicBook comic; 
    private UserDAO dao;
    private PersonalCollection pc;
    private double previousValue;
    private int previousGrade;
    private int newGrade;

    public GradeAction(SignedInUser user, UserDAO dao, int newGrade){
        this.user = user; 
        this.dao = dao;
        this.pc = user.getPersonalCollection();
        this.newGrade = newGrade;
    }
    @Override
    public void execute(ComicBook toBeGradedcomic) throws IOException {
        previousGrade = toBeGradedcomic.getGrade();
        previousValue = toBeGradedcomic.getValue();
        toBeGradedcomic.setGrade(this.newGrade);
        pc.grade((ComicBookComponent)toBeGradedcomic);
        this.comic = toBeGradedcomic;
        dao.updateUser(user);
    }

    @Override
    public boolean isReversible() {
        return true; 
    }

    @Override
    public void unexecute() throws IOException {
        this.comic.setGrade(previousGrade);
        this.comic.setValue(previousValue);
        dao.updateUser(user);
    }

    @Override
    public void redo() throws IOException {
        execute(comic);
    }
}
