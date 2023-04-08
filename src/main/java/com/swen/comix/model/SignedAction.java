package com.swen.comix.model;

import java.util.List;

/**
 * @Author Angela Ngo
 */
public class SignedAction implements Action{
    private PersonalCollection pc; 
    private ComicBook comic; 
    private int previousNum;
    private double previousValue;

    public SignedAction(PersonalCollection pc){
        this.pc = pc; 
    }

    @Override
    public void execute(ComicBook comic) {
        this.comic = comic; 
        previousNum = ((ComicBookComponent)comic).getSignatures();
        previousValue = comic.getValue();
        pc.sign((ComicBookComponent)comic);
    }

    @Override
    public boolean isReversible() {
        return true;     
    }

    @Override
    public void unexecute() {
        this.comic.setSignatures(previousNum);
        this.comic.setValue(previousValue);
    }   
}
