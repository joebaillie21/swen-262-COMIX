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
        pc.sign((ComicBookComponent)comic);
        this.comic = comic; 
        previousNum = ((ComicBookComponent)comic).getSignature();
    }

    @Override
    public boolean isReversible() {
        return true;     
    }

    @Override
    public void unexecute() {
        List<ComicBook> perCol = pc.getPersonalCollection(); 
        for(int i = 0; i < perCol.size(); i++){
            if(perCol.get(i).equals(this.comic)){
                int comicLocation = i; 
                ((ComicBookComponent)comic).setGrade(previousNum); // NOTE: NEED TO SET IT THE PREVIOUS GRADE! NOT SURE HOW
                
                perCol.set(comicLocation, (ComicBookComponent)comic); // updating the authentication in the personal collection by setting is back to false 
            }
        }   
    }
    
}
