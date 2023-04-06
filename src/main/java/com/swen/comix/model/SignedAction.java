package com.swen.comix.model;

import java.util.List;

/**
 * @Author Angela Ngo
 */
public class SignedAction implements Action{
    private PersonalCollection pc; 
    private ComicBook comic; 
    private int previousNum;

    public SignedAction(PersonalCollection pc){
        this.pc = pc; 
    }

    @Override
    public void execute(ComicBook comic) {
        previousNum = ((ComicBookComponent)comic).getSignatures();
        pc.sign((ComicBookComponent)comic);
        this.comic = comic; 
    }

    @Override
    public boolean isReversible() {
        return true;     
    }

    @Override
    public void unexecute() {
        this.comic.setSignatures(previousNum);
    }   
}
