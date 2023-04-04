package com.swen.comix.model;


/**
 * @Author Angela Ngo
 */
public class SignedInUser extends User{
    private String username, password;
    private Action selectedAction;
    private ConversionAction selectedConversion;
    private PersonalCollection personalCollection; 


    public SignedInUser(String username, String password){
        super(username);
        this.password = password; 
        this.personalCollection = super.getPersonalCollection(); 
        selectedAction = null; 
        selectedConversion = null; 

    }
    public void setCommand(Action command){
        this.selectedAction = command;
    }

    public void setConversion(ConversionAction conversionAction){
        this.selectedConversion = conversionAction; 
    }

    /**
     * HAVENT DONE THIS YET
     * @param comic
     */
    public void executeCommand(ComicBook comic){
        // not sure if this should be void or not
    }

    /**
     * HAVENT DONE THIS YET 
     */
    public void unexecuteCommand(){
        
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public PersonalCollection getPersonalCollection(){
        return personalCollection;
    }

    /**
     * this takes in the filename and then 
     * the user has to decide if this replaces the DB (the immutable)
     * or does it replace the personal collection 
     * @param filename - name of the new file to be imported in 
     * @param toBeReplaced - the name of the source that is being 
     * replaced 
     */
    public void importFile(String filename, String toBeReplaced ){

    }

    /**
     * This create a new file in the data folder that is in the given specified format
     * This should print to the PTUI console the location of the file
     * @param filepath - name of the original file 
     * @param format - the format of what you want to convert the file to 
     */
    public void exportFile(String filename, String format){

    }
}
