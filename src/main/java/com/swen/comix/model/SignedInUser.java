package com.swen.comix.model;

import java.util.Stack;

import com.swen.comix.persistence.UserDAO;

/**
 * @Author Angela Ngo
 */
public class SignedInUser extends User{
    private String username, password;
    private Action selectedAction;
    private ConversionAction selectedConversion;
    private PersonalCollection personalCollection; 
    private Stack<Action> completedActions;
    private Stack<Action> undoneActions;
    private Converter converter;


    public SignedInUser(String username, String password){
        super(username);
        this.password = password; 
        this.personalCollection = super.getPersonalCollection(); 
        selectedAction = null; 
        selectedConversion = null; 
        this.completedActions = new Stack<>();
        this.undoneActions = new Stack<>();
    }
    public void setCommand(Action command){
        this.selectedAction = command;
    }

    public void setConversion(ConversionAction conversionAction){
        this.selectedConversion = conversionAction; 
    }

    public void executeCommand(ComicBook comic){
        this.selectedAction.execute(comic);
        this.completedActions.add(selectedAction);
    }

    public void unexecuteCommand(){
        Action actionToUnexecute = this.completedActions.pop();
        this.undoneActions.add(actionToUnexecute);
        if (actionToUnexecute.isReversible()){
            actionToUnexecute.unexecute();
        }
    }

    public void redoCommand(){
        Action actionToRedo = this.undoneActions.pop();
        actionToRedo.redo();
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
    public void importFile(String filename, FileType format, UserDAO dao){
        // switch on the format (this is the incomming format of the file)
            // instantiate converter based on this format (ie: new Converter(ToType:(always java in this function), FromType))
            // call converter.convertFileToJava() which will return the file in java
                // Replace the user's personal collection with this output and update the local json with the file dao
            // if the switch lands in the to database, call the converter.convertFileToFile()
        switch(format){
            case JAVA:
                this.converter = new Converter();
            default:
                break;
        }
    }

    /**
     * This create a new file in the data folder that is in the given specified format
     * This should print to the PTUI console the location of the file
     * @param filepath - name of the original file 
     * @param format - the format of what you want to convert the file to 
     */
    public void exportFile(String filename, FileType toType, FileType fromType){

    }
}
