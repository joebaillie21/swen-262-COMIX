package com.swen.comix.controller;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.crypto.KeySelector.Purpose;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.swen.comix.model.ComixLogin;
import com.swen.comix.model.ComixMediator;
import com.swen.comix.model.Guest;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.SearchByAuthor;
import com.swen.comix.model.SearchByDescription;
import com.swen.comix.model.SearchByPrincipleCharacter;
import com.swen.comix.model.SearchBySeriesTitle;
import com.swen.comix.model.SignedInUser;
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

    public App() throws IOException{
        init();
    }

    public void run() throws IOException{
        running = true;
        while(running){
            input = new Scanner(System.in);

            //GUEST COMMANDS
            if(view.getCommand().equals(Command.GUEST)){
                String newInput = input.nextLine();
                if(newInput.equals("1")){
                    view.setCommand(Command.SIGNIN);
                }
                else if(newInput.equals("2")){
                    view.setCommand(Command.SIGNUP);
                }
                else if(newInput.equals("3")){
                    view.setCommand(Command.SEARCHDATABASE);
                }
                else if(newInput.equals("4")){
                    view.setCommand(Command.BROWSEOTHERCOLLECTION);
                }
                else if(newInput.equals("5")){
                    view.setCommand(Command.CLOSING);
                }
                else{
                   view.setCommand(Command.ERROR);
                   view.handleCommand();
                   view.setCommand(Command.GUEST);
                   view.handleCommand();
                }
                view.handleCommand();
            }

            // - SIGNIN
            else if(view.getCommand().equals(Command.SIGNIN)){
                this.username = input.nextLine();
                view.setCommand(Command.SIGNINPASSWORD);
                view.handleCommand();
            }
            else if(view.getCommand().equals(Command.SIGNINPASSWORD)){
                this.password = input.nextLine();
                try{
                    this.user = mediator.login(this.username, this.password);
                    this.collection = this.user.getPersonalCollection();
                    view.setCommand(Command.SIGNINCOMPLETE);
                    view.handleCommand();
                    view.setCommand(Command.SIGNEDINUSER);
                    view.handleCommand();
                }
                catch(IllegalArgumentException exception){
                    view.setCommand(Command.ERROR);
                    view.handleCommand();
                    view.setCommand(Command.GUEST);
                    view.handleCommand();
                }
                
            }

            // - SIGNUP
            else if(view.getCommand().equals(Command.SIGNUP)){
                this.username = input.nextLine();
                view.setCommand(Command.SIGNUPPASSWORD);
                view.handleCommand();
            }
            else if(view.getCommand().equals(Command.SIGNUPPASSWORD)){
                this.password = input.nextLine();
                try {
                    this.user = guest.createAccount(this.username, this.password);
                    this.collection = new PersonalCollection(this.username);
                    this.user.setCollection(collection);
                    view.setCommand(Command.SIGNINCOMPLETE);
                    view.handleCommand();
                    view.setCommand(Command.SIGNEDINUSER);
                    view.handleCommand();
                } catch (Exception e) {
                    view.setCommand(Command.NEWACCOUNTERROR);
                    view.handleCommand();
                    view.setCommand(Command.GUEST);
                    view.handleCommand();
                }
            }

            // - BROWSE COLLECTION GUEST
            else if(view.getCommand().equals(Command.BROWSEOTHERCOLLECTION)){
                String name = input.nextLine();
                try {
                    //System.out.println(userDAO.getUsers());
                    this.guest.searchForPersonalCollection(name);
                    //System.out.println(name);
                    this.searchResult = this.guest.getCurrentlySelectedPC().toString();
                    //view.setCommand(Command.PCRESULT);
                } catch (IOException e) {
                    view.setCommand(Command.ERROR);
                    view.handleCommand();
                    view.setCommand(Command.GUEST);
                    view.handleCommand();
                } catch (IllegalArgumentException e){
                    System.out.println("thing");
                    view.setCommand(Command.ERROR);
                    view.handleCommand();
                    view.setCommand(Command.GUEST);
                    view.handleCommand();
                } catch (NullPointerException e){
                    System.out.println("prob2");
                    view.setCommand(Command.ERROR);
                    view.handleCommand();
                    view.setCommand(Command.GUEST);
                    view.handleCommand();
                }
            }

            //USER COMMANDS
            else if(view.getCommand().equals(Command.SIGNEDINUSER)){
                String commandNum = input.nextLine();
                if(commandNum.equals("1")){
                    view.setCommand(Command.SEARCHTYPEDATABASE);
                }
                else if(commandNum.equals("2")){
                    view.setCommand(Command.SEARCHCOLLECTION);
                }
                else if(commandNum.equals("3")){
                    view.setCommand(Command.ADDTOCOLLECTION);
                }
                else if(commandNum.equals("4")){
                    view.setCommand(Command.REMOVEFROMCOLLECTION);
                }
                else if(commandNum.equals("5")){
                    view.setCommand(Command.EDITMARKSELECTION);
                }
                else if(commandNum.equals("6")){
                    view.setCommand(Command.UNDO);
                }
                else if(commandNum.equals("7")){
                    view.setCommand(Command.REDO);
                }
                else if(commandNum.equals("8")){
                    view.setCommand(Command.IMPORTEXPORT);
                }
                else if(commandNum.equals("9")){
                    view.setCommand(Command.CLOSING);
                }
                else{
                    view.setCommand(Command.ERROR);
                    view.handleCommand();
                    view.setCommand(Command.SIGNEDINUSER);
                    view.handleCommand();
                }
                view.handleCommand();
            }

            // - CHANGE SEARCH TYPE
            else if(view.getCommand().equals(Command.SEARCHTYPEDATABASE) || view.getCommand().equals(Command.SEARCHTYPECOLLECTION)) {
                String commandNum = input.nextLine();
                Command newCommand = Command.SEARCHDATABASE;
                if(!view.getCommand().equals(Command.SEARCHTYPEDATABASE)){
                    newCommand = Command.SEARCHCOLLECTION;
                }
            
                if(commandNum.equals("1")){
                    this.user.setSearchStrategy(new SearchBySeriesTitle());
                    view.setCommand(newCommand);
                }
                else if(commandNum.equals("2")){
                    this.user.setSearchStrategy(new SearchByPrincipleCharacter());
                    view.setCommand(newCommand);
                }
                else if(commandNum.equals("3")){
                    this.user.setSearchStrategy(new SearchByAuthor());
                    view.setCommand(newCommand);
                }
                else if(commandNum.equals("4")){
                    this.user.setSearchStrategy(new SearchByDescription());
                    view.setCommand(newCommand);
                }
                else if(commandNum.equals("5")){
                    view.setCommand(Command.SIGNEDINUSER);
                }
                else{
                    view.setCommand(Command.ERROR);
                    view.handleCommand();
                    view.setCommand(Command.SIGNEDINUSER);
                    view.handleCommand();
                }
                view.handleCommand();
            }

            // - SEARCH
            else if(view.getCommand().equals(Command.SEARCHDATABASE) || view.getCommand().equals(Command.SEARCHCOLLECTION)){
                boolean database = true;
                if(!view.getCommand().equals(Command.SEARCHDATABASE)){
                    database = false;
                }

                String search = input.nextLine();
                this.user.search(search);
                //this.user.search(search, database);
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
            else if(view.getCommand().equals(Command.UNDO)){

            }

            // - REDO COMMAND
            else if(view.getCommand().equals(Command.REDO)){

            }

            // - IMPORT EXPORT
            else if(view.getCommand().equals(Command.IMPORTEXPORT)){

            }

            else{
                running = false;
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
