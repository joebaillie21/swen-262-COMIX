package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo
 */
public class SlabbedAction implements Action {
    private PersonalCollection pc; 
    private ComicBook comic; 

    public SlabbedAction(PersonalCollection pc){
        this.pc = pc; 
    }
    @Override
    public void execute(ComicBook comic) {
        pc.slab((ComicBookComponent)comic);
        this.comic = comic; 
    }

    @Override
    public boolean isReversible() {
        return true; 
    }

    @Override
    public void unexecute() {
        ArrayList<ComicBookComponent> perCol = pc.getPersonalCollection(); 
        for(int i = 0; i < perCol.size(); i++){
            if(perCol.get(i).equals(this.comic)){
                int comicLocation = i; 
    
                ((ComicBookComponent)comic).setSlabbed((false));
                perCol.set(comicLocation, (ComicBookComponent)comic); // updating the authentication in the personal collection by setting is back to false 
            }
        }
    }
    
}
