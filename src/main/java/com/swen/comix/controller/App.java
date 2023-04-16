package com.swen.comix.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.KeySelector.Purpose;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.swen.comix.db.Database;
import com.swen.comix.model.AddAction;
import com.swen.comix.model.Author;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.ComicBookComponent;
import com.swen.comix.model.ComixLogin;
import com.swen.comix.model.ComixMediator;
import com.swen.comix.model.Guest;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.Publisher;
import com.swen.comix.model.SearchByAuthor;
import com.swen.comix.model.SearchByDescription;
import com.swen.comix.model.SearchByPrincipleCharacter;
import com.swen.comix.model.SearchBySeriesTitle;
import com.swen.comix.model.SignedInUser;
import com.swen.comix.model.SortByDefault;
import com.swen.comix.model.SortByIssueNumber;
import com.swen.comix.model.SortByPublicationDate;
import com.swen.comix.model.SortBySeriesTitle;
import com.swen.comix.model.SortByVolume;
import com.swen.comix.model.User;
import com.swen.comix.persistence.UserDAO;
import com.swen.comix.persistence.UserFileDAO;
import com.swen.comix.view.PTUI;
/*
 * @Author Ash Roushinko
 */
public class App {
    private User user;
    private SignedInUser signedInUser;
    private Guest guest;
    private UserFileDAO userDAO;
    private ComixMediator mediator;
    private PTUI view;
    private Scanner input;
    private Boolean running, isDatabase;
    private String username, password, searchResult;
    private PersonalCollection collection;
    private Database database;
    private ArrayList<ComicBook> currentResult;

    public App() throws Exception{
        init();
    }

    public void run() throws Exception{
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
                            this.isDatabase = true;
                            view.setCommand(Command.SETSEARCHTYPE);
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
                        this.signedInUser = (SignedInUser) mediator.login(this.username, this.password);
                        this.collection = this.signedInUser.getPersonalCollection();
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
                        this.signedInUser = guest.createAccount(this.username, this.password);
                        this.collection = new PersonalCollection(this.username);
                        this.signedInUser.setCollection(collection);
                        view.setCommand(Command.SIGNEDINUSER);
                    } catch (IOException e) {
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
                            this.isDatabase = true;
                            view.setCommand(Command.SETSEARCHTYPE);
                            break;
                        case "2":
                            this.isDatabase = false;
                            view.setCommand(Command.SETSEARCHTYPE);
                            break;
                        case "3":
                            view.setCommand(Command.HOWTOADD);
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
                
            
                case EDITMARKSELECTION:
                    break;
                case IMPORTEXPORT:
                    break;
                case OTHERCOLLECTIONRESULT:
                    break;
                /*    
                case SEARCHCOLLECTION, SEARCHDATABASE:
                    boolean database = true;
                    if(!view.getCommand().equals(Command.SEARCHDATABASE)){
                        database = false;
                    }

                    String search = input.nextLine();
                    this.currentResult = this.user.search(search, database);
                    break;
                    */
                case SETSEARCHTYPE, ADDFROMDB, REMOVEFROMCOLLECTION:
                    String searchNum = input.nextLine();
                    Command searchCommand = Command.SORTTYPE;
                    if(isDatabase){searchCommand = Command.SEARCHING;}
                    
                    if(searchNum.equals("1")){
                        this.user.setSearchStrategy(new SearchBySeriesTitle(collection, this.database));
                        this.view.setCommand(searchCommand);
                    }
                    else if(searchNum.equals("2")){
                        this.user.setSearchStrategy(new SearchByPrincipleCharacter(this.collection, this.database));
                        this.view.setCommand(searchCommand);
                    }
                    else if(searchNum.equals("3")){
                        this.user.setSearchStrategy(new SearchByAuthor(this.collection, this.database));
                        this.view.setCommand(searchCommand);
                    }
                    else if(searchNum.equals("4")){
                        this.user.setSearchStrategy(new SearchByDescription(this.collection, this.database));
                        this.view.setCommand(searchCommand);
                    }
                    else if(searchNum.equals("5")){
                        view.setCommand(Command.SIGNEDINUSER);
                    }
                    else{
                        view.setCommand(Command.ERROR);
                        view.handleCommand();
                        view.setCommand(Command.SIGNEDINUSER);
                    }
                    break;
                
                case SORTTYPE:
                    String ifSort = input.nextLine();
                    if(ifSort.equals("1")){
                        view.setCommand(Command.SETSORTTYPE);
                    }
                    else if(ifSort.equals("2")){
                        this.user.setSortStrategy(new SortByDefault());
                        view.setCommand(Command.SEARCHING);
                    }
                    else{
                        view.setCommand(Command.ERROR);
                        view.handleCommand();
                        view.setCommand(Command.SORTTYPE);
                    }
                    break;

                case SETSORTTYPE:
                    String chooseSort = input.nextLine();
                    if(chooseSort == "1"){
                        this.user.setSortStrategy(new SortBySeriesTitle());
                        view.setCommand(Command.SEARCHING);
                    }
                    else if(chooseSort == "2"){
                        this.user.setSortStrategy(new SortByVolume());
                        view.setCommand(Command.SEARCHING);
                    }
                    else if(chooseSort == "3"){
                        this.user.setSortStrategy(new SortByIssueNumber());
                        view.setCommand(Command.SEARCHING);
                    }
                    else if(chooseSort == "4"){
                        this.user.setSortStrategy(new SortByPublicationDate());
                        view.setCommand(Command.SEARCHING);
                    }
                    break;
                
                case SEARCHING:
                    this.currentResult = this.user.search(input.nextLine(), isDatabase);
                    if(isDatabase){this.user.setSortStrategy(new SortByDefault());}
                    this.currentResult = this.user.sort(this.currentResult);
                    this.view.setResults(this.currentResult);
                    this.view.setCommand(Command.RESULTS);
                    break;
                
                case RESULTS:
                    if(this.signedInUser == null){this.view.setCommand(Command.GUEST);}
                    else{this.view.setCommand(Command.SIGNEDINUSER);}
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
                        case "3":
                            view.setCommand(Command.SIGNEDINUSER);
                            break;
                        default:
                            view.setCommand(Command.ERROR);
                            view.handleCommand();
                            view.setCommand(Command.SIGNEDINUSER);
                    }
                    break;
                case ADDFROMINPUT:
                    String comicInput = input.nextLine();
                    String[] separatedComic = comicInput.split(";");
                    if(separatedComic.length == 7){
                        Publisher pub = new Publisher(separatedComic[0]);
                        ArrayList<Author> authors = new ArrayList<Author>();
                        for(String a:separatedComic[4].split(",")){
                            authors.add(new Author(a));
                        }
                        ArrayList<String> characters = new ArrayList<String>(Arrays.asList(separatedComic[5].split(",")));
                        ComicBookComponent comicBook = new ComicBookComponent(pub, separatedComic[1], separatedComic[2], separatedComic[3], separatedComic[4], authors, characters, separatedComic[6]);
                        this.signedInUser.setCommand(new AddAction(signedInUser, userDAO));
                        this.signedInUser.executeCommand(comicBook);
                        this.view.setCommand(Command.ADDED);
                    }
                    else{
                        this.view.setCommand(Command.ERROR);
                        this.view.handleCommand();
                        this.view.setCommand(Command.HOWTOADD);
                    }
                    break;
                case ADDED:
                    this.view.setCommand(Command.SIGNEDINUSER);
                    break;

                case REDO:
                    this.signedInUser.redoCommand();
                    view.setCommand(Command.SIGNEDINUSER);
                    break;
                case UNDO:
                    this.signedInUser.unexecuteCommand();
                    view.setCommand(Command.SIGNEDINUSER);
                    break;
                    
                case CLOSING:
                    running = false;
                    break;
                default:
                    break;

            }
            if(running){view.handleCommand();}
        }
    }

    public void init() throws Exception{
        ObjectMapper mockMapper = new ObjectMapper();
        this.userDAO = new UserFileDAO("src/data/users.json", mockMapper);
        this.mediator = new ComixLogin(this.userDAO);
        this.guest = new Guest(mediator);
        this.database = new Database();
        this.user = new User();
        this.view = new PTUI();
        run();
    }
}
