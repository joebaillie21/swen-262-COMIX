package com.swen.comix.model;

import java.util.List;

/**
 * @Author Angela Ngo
 */
public class SlabbedAction implements Action {
    private PersonalCollection pc; 
    private ComicBook comic; 
    private double previousValue;

    public SlabbedAction(PersonalCollection pc){
        this.pc = pc; 
    }
    @Override
    public void execute(ComicBook comic) {
        this.previousValue = comic.getValue();
        pc.slab((ComicBookComponent)comic); 
        this.comic = comic; 
        //dao.setCollection(user.getcollection)
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
