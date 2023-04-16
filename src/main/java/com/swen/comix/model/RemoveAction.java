package com.swen.comix.model;

public class RemoveAction implements Action {
    private PersonalCollection pc ;
    private ComicBook comic; 

    public RemoveAction(PersonalCollection pc){
        this.pc = pc; 
    }

    @Override
    public void execute(ComicBook comic) {
        pc.remove(comic);
        this.comic = comic;
    }

    @Override
    public boolean isReversible() {
        return true;
    }

    @Override
    public void unexecute() {
        pc.add((ComicBookComponent) comic); 
    }

    @Override
    public void redo() {
        execute(comic);
    }
}
