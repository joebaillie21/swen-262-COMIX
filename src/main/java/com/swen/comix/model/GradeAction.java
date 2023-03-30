package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo
 */
public class GradeAction implements Action{
    private PersonalCollection pc; 
    private ComicBook comic; 
    private int previousGrade; 

    public GradeAction(PersonalCollection pc){
        this.pc = pc; 
    }
    @Override
    public void execute(ComicBook comic) {
        pc.grade((ComicBookComponent)comic);
        this.comic = comic; 
        previousGrade = ((ComicBookComponent) comic).getGrade();
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
                ComicBookComponent CBC = (ComicBookComponent) comic; 
                CBC.setGrade(previousGrade); // NOTE: NEED TO SET IT THE PREVIOUS GRADE! NOT SURE HOW
                
                perCol.set(comicLocation, CBC); // updating the authentication in the personal collection by setting is back to false 
            }
        }  
    }
    
}
