package model;

import java.util.ArrayList;

/**
 * @Author Angela Ngo
 */
public class AuthenticatedAction implements Action{
    private PersonalCollection pc; 
    private ComicBook comic; 
    public AuthenticatedAction(PersonalCollection pc){
        this.pc = pc; 
    }
    @Override
    public void execute(ComicBook comic) { // sets this equal to true in the personal collection 
        pc.authenticate((ComicBookComponent) comic);
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
    
                ((ComicBookComponent)comic).setAuthentication(false);
                perCol.set(comicLocation, (ComicBookComponent)comic); // updating the authentication in the personal collection by setting is back to false 
            }
        }
    }
    
}
