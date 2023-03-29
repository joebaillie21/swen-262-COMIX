package model;

public class Guest extends User{
    private PersonalCollection currentSelectedPC; 
    private ComixLogin CL; 
    public Guest(String name, ComixLogin CL) {
        super(name);
        this.currentSelectedPC = null; 
        this.CL = CL; 
    }

    /**
     * allows for a guest to search for a personal collection within made users
     * @param name - name of the collection they want to get 
     */
    public void searchForPersonalCollection(String name){
        currentSelectedPC = CL.getSpecificPersonalCollection(name); 
    }
    
    public PersonalCollection getCurrentlySelectedPC(){
        return currentSelectedPC;
    }
}
