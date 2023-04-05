package com.swen.comix.model;

public class AddAction implements Action {
    private PersonalCollection pc ;
    private ComicBook comic; 

    public AddAction(PersonalCollection pc){
        this.pc = pc; 

    }
    @Override
    public void execute(ComicBook comic) {
        pc.add((ComicBookComponent)comic); 
        this.comic = comic; 
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
    public void unexecute() {
        pc.remove((ComicBookComponent)comic); 
    }
}
