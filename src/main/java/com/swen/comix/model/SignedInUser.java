package com.swen.comix.model;

import java.util.ArrayList;
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
     * @param filename - name of the new file to be imported from
     * @param toType determines whether the user wants to import to the db or the pc
     * @param fromType derived from the filename. Tells the type of the file the user wants to import from
     * @param dao the user file dao used when replacing the collection
     * replaced 
     * @throws Exception
     */
    public void importFile(String filename, FileType toType, FileType fromType, UserDAO dao) throws Exception{
        // switch on the format (this is the incomming format of the file)
            // instantiate converter based on this format (ie: new Converter(ToType:(either java or xml for this function), FromType:(derived from filename by ptui)))
            // if the switch lands in the to database, call the converter.convertFileToFile()
            // if other call converter.convertFileToJava() which will return the file in java
                // Replace the user's personal collection with this output and update the local json with the file dao
        switch(toType){
            case XML:
                this.converter = new Converter(FileType.JAVA, fromType);
                converter.convertFileToFile(filename);
                break;
            default:
                this.converter = new Converter(FileType.JAVA, fromType);
                ArrayList<ComicBookComponent> newPc = converter.convertFileToJava(filename);
                this.personalCollection.setPersonalCollection(newPc);
                dao.updateUser(this);
                break;
        }
    }

    /**
     * This create a new file in the data folder that is in the given specified format
     * This should print to the PTUI console the location of the file
     * @param toType - the type of the new exported file will be
     * @param fromType - determines whether the user wants to export the database or personal collection
     */
    public void exportFile(FileType toType, FileType fromType){
        this.converter = new Converter(toType, fromType);
    }
}
