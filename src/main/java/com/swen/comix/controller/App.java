package com.swen.comix.controller;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.db.Database;
import com.swen.comix.model.ComixLogin;
import com.swen.comix.model.ComixMediator;
import com.swen.comix.model.Guest;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.SearchByAuthor;
import com.swen.comix.model.SearchByDescription;
import com.swen.comix.model.SearchByPrincipleCharacter;
import com.swen.comix.model.SearchBySeriesTitle;
import com.swen.comix.model.User;
import com.swen.comix.persistence.UserDAO;
import com.swen.comix.persistence.UserFileDAO;
import com.swen.comix.view.PTUI;
/*
 * @Author Ash Roushinko
 */
public class App {
    private User user;
    private Guest guest;
    private UserFileDAO userDAO;
    private ComixMediator mediator;
    private PTUI view;
    private Scanner input;
    private Boolean running;
    private String username, password, searchResult;
    private PersonalCollection collection;
    private Database database;

    public App() throws IOException{
        init();
    }

    public void run() throws IOException{
        running = true;
        while(running){
            input = new Scanner(System.in);
            switch(view.getCommand()){
                
                case GUEST:
                    String newInput = input.nextLine();
                    switch (newInput){
                        case "1":
                            view.setCommand(Command.SIGNIN);
                            break;
                        case "2":
                            view.setCommand(Command.SIGNUP);
                            break;
                        case "3":
                            view.setCommand(Command.SEARCHDATABASE);
                            break;
                        case "4":
                            view.setCommand(Command.BROWSEOTHERCOLLECTION);
                            break;
                        case "5":
                            view.setCommand(Command.CLOSING);
                            break;
                        default:
                            view.setCommand(Command.ERROR);
                            view.handleCommand();
                            view.setCommand(Command.GUEST);
                    }
                    break;

                case SIGNIN:
                    this.username = input.nextLine();
                    view.setCommand(Command.SIGNINPASSWORD);
                    break;
                case SIGNINPASSWORD:
                    this.password = input.nextLine();
                    try{
                        this.user = mediator.login(this.username, this.password);
                        this.collection = this.user.getPersonalCollection();
                        view.setCommand(Command.SIGNINCOMPLETE);
                        view.handleCommand();
                        view.setCommand(Command.SIGNEDINUSER);
                    }
                    catch(IllegalArgumentException exception){
                        view.setCommand(Command.ERROR);
                        view.handleCommand();
                        view.setCommand(Command.GUEST);
                    }
                    break;

                case SIGNUP:
                    this.username = input.nextLine();
                    view.setCommand(Command.SIGNUPPASSWORD);
                    break;
                case SIGNUPPASSWORD:
                    this.password = input.nextLine();
                    try {
                        this.user = guest.createAccount(this.username, this.password);
                        this.collection = new PersonalCollection(this.username);
                        this.user.setCollection(collection);
                        view.setCommand(Command.SIGNEDINUSER);
                    } catch (Exception e) {
                        view.setCommand(Command.NEWACCOUNTERROR);
                        view.handleCommand();
                        view.setCommand(Command.GUEST);
                    }
                    break;

                case BROWSEOTHERCOLLECTION:
                    String name = input.nextLine();
                    try {
                        this.guest.searchForPersonalCollection(name);
                        this.searchResult = this.guest.getCurrentlySelectedPC().toString();
                        //view.setCommand(Command.PCRESULT);
                    } catch (IOException|IllegalArgumentException|NullPointerException e) {
                        view.setCommand(Command.ERROR);
                        view.handleCommand();
                        view.setCommand(Command.GUEST);
                    }
                    break;
                
                case SIGNEDINUSER:
                    String commandNum = input.nextLine();
                    switch(commandNum){
                        case "1":
                            view.setCommand(Command.SEARCHTYPEDATABASE);
                            break;
                        case "2":
                            view.setCommand(Command.SEARCHCOLLECTION);
                            break;
                        case "3":
                            view.setCommand(Command.ADDTOCOLLECTION);
                            break;
                        case "4":
                            view.setCommand(Command.REMOVEFROMCOLLECTION);
                            break;
                        case "5":
                            view.setCommand(Command.EDITMARKSELECTION);
                            break;
                        case "6":
                            view.setCommand(Command.UNDO);
                            break;
                        case "7":
                            view.setCommand(Command.REDO);
                            break;
                        case"8":
                            view.setCommand(Command.IMPORTEXPORT);
                            break;
                        case "9":
                            view.setCommand(Command.CLOSING);
                            break;
                        default:
                            view.setCommand(Command.ERROR);
                            view.handleCommand();
                            view.setCommand(Command.SIGNEDINUSER);                        
                    }
                    break;
                
                case ADDTOCOLLECTION:
                    view.setCommand(Command.HOWTOADD);
                    break;
                case HOWTOADD:
                    String addInputNum = input.nextLine();
                    switch(addInputNum){
                        case "1":
                            view.setCommand(Command.ADDFROMDB);
                            break;
                        case "2":
                            view.setCommand(Command.ADDFROMINPUT);
                            break;
                        default:
                            view.setCommand(Command.ERROR);
                            view.handleCommand();
                            view.setCommand(Command.SIGNEDINUSER);
                    }
                case EDITMARKSELECTION:
                    break;
                case ERROR:
                    break;
                case IMPORTEXPORT:
                    break;
                case NEWACCOUNTERROR:
                    break;
                case OTHERCOLLECTIONRESULT:
                    break;
                case REDO:
                    break;
                case REMOVEFROMCOLLECTION:
                    break;
                case SEARCHCOLLECTION, SEARCHDATABASE:
                    boolean database = true;
                    if(!view.getCommand().equals(Command.SEARCHDATABASE)){
                        database = false;
                    }

            // - SEARCH PERSONAL COLLECTION
            else if(view.getCommand().equals(Command.SEARCHCOLLECTION)){

            }

            // - ADD TO PERSONAL COLLECTION
            else if(view.getCommand().equals(Command.ADDTOCOLLECTION)){

            }

            // - REMOVE FROM PERSONAL COLLECTION
            else if(view.getCommand().equals(Command.REMOVEFROMCOLLECTION)){

            }

            // - EDIT/MARK COMIC
            else if(view.getCommand().equals(Command.EDITMARKSELECTION)){

            }

            // - UNDO COMMAND
            else if(view.getCommand().equals(Command.EDITMARKSELECTION)){

            }

            // - REDO COMMAND
            else if(view.getCommand().equals(Command.EDITMARKSELECTION)){

            }

            // - IMPORT EXPORT
            else if(view.getCommand().equals(Command.IMPORTEXPORT)){

            }

        }
    }

    public void init() throws IOException{
        ObjectMapper mockMapper = new ObjectMapper();
        //mockMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.userDAO = new UserFileDAO("src/data/users.json", mockMapper);
        this.mediator = new ComixLogin(this.userDAO);
        this.guest = new Guest(mediator);
        this.view = new PTUI();
        run();
    }
}
