package model;

import java.util.ArrayList;

public class PersonalCollection {
    private ArrayList<ComicBookComponent> personCollection; 

    public PersonalCollection(){
        personCollection = new ArrayList<ComicBookComponent>(); 
    }
    public void add(ComicBookComponent comic){
        // TO DO
    }

    public void remove(ComicBookComponent comic){
        // TO DO
    }

    public void grade(ComicBookComponent comic){
        // TO DO
    }

    public void slab(ComicBookComponent comic){
        // TO DO
    }

    public void sign(ComicBookComponent comic){
        // TO DO
    }

    public void authenticate(ComicBookComponent comic){

    }

    public ArrayList<ComicBookComponent> getPersonalCollection(){
        return personCollection;
    }
    
}
