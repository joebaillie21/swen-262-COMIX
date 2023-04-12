package com.swen.comix.model;

/**
 * @Author Angela Ngo
 */
public class AuthenticatedAction implements Action{
    private PersonalCollection pc; 
    private ComicBook comic; 
    private double previousValue;

    public AuthenticatedAction(PersonalCollection pc){
        this.pc = pc; 
    }

    @Override
    public void execute(ComicBook comic) {
        this.comic = comic; 
        this.previousValue = comic.getValue();
        pc.authenticate((ComicBookComponent) comic);
    }

    @Override
    public boolean isReversible() {
        return true; 
    }

    @Override
    public void unexecute() {
        this.comic.setValue(previousValue);
    }
    
    @Override
    public void redo() {
        execute(comic);
    }
}
