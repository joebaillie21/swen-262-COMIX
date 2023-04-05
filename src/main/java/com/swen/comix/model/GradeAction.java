package com.swen.comix.model;

import java.util.List;

/**
 * @Author Angela Ngo
 */
public class GradeAction implements Action{
    private PersonalCollection pc; 
    private ComicBook comic; 
    private int previousGrade; 
    private int newGrade;

    public GradeAction(PersonalCollection pc, int newGrade){
        this.pc = pc; 
        this.newGrade = newGrade;
    }
    @Override
    public void execute(ComicBook toBeGradedcomic) {
        previousGrade = (toBeGradedcomic).getGrade();
        toBeGradedcomic.setGrade(this.newGrade);
        pc.grade((ComicBookComponent)toBeGradedcomic);
        this.comic = toBeGradedcomic;
    }

    @Override
    public boolean isReversible() {
        return true; 
    }

    @Override
    public void unexecute() {
        comic.setGrade(previousGrade);
    }
}
