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
import com.swen.comix.model.Authenticate;
import com.swen.comix.model.AuthenticatedAction;
import com.swen.comix.model.Author;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.ComicBookComponent;
import com.swen.comix.model.ComixLogin;
import com.swen.comix.model.ComixMediator;
import com.swen.comix.model.Converter;
import com.swen.comix.model.ExportAsCSV;
import com.swen.comix.model.FileType;
import com.swen.comix.model.GradeAction;
import com.swen.comix.model.Guest;
import com.swen.comix.model.ImportFromCSV;
import com.swen.comix.model.ImportFromJson;
import com.swen.comix.model.ImportFromSQL;
import com.swen.comix.model.ImportFromXML;
import com.swen.comix.model.Importer;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.Publisher;
import com.swen.comix.model.RemoveAction;
import com.swen.comix.model.SearchByAuthor;
import com.swen.comix.model.SearchByDescription;
import com.swen.comix.model.SearchByIssueNumber;
import com.swen.comix.model.SearchByPrincipleCharacter;
import com.swen.comix.model.SearchBySeriesTitle;
import com.swen.comix.model.SearchBySigned;
import com.swen.comix.model.SearchForGaps;
import com.swen.comix.model.SearchForGraded;
import com.swen.comix.model.SearchForRuns;
import com.swen.comix.model.SearchForSlabbed;
import com.swen.comix.model.SignedAction;
import com.swen.comix.model.SignedInUser;
import com.swen.comix.model.SlabbedAction;
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
    private Boolean running, isDatabase, skipInput;
    private String username, password;
    private int grade;
    private PersonalCollection collection;
    private Database database;
    private ArrayList<ComicBook> currentResult;
    private FileType fileType;

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
                    }
                    break;

                case BROWSEOTHERCOLLECTION:
                    String name = input.nextLine();
                    try {
                        this.guest.searchForPersonalCollection(name);
                        String collectionResult = this.guest.getCurrentlySelectedPC().toString();
                        view.setCollectionResults(collectionResult);
                        view.setCommand(Command.OTHERCOLLECTIONRESULT);
                    } catch (IOException|IllegalArgumentException|NullPointerException e) {
                        view.setCommand(Command.ERROR);
                    }
                    break;
                case OTHERCOLLECTIONRESULT:
                    this.view.setCommand(Command.GUEST);
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
                        case "8":
                            view.setCommand(Command.CHOOSEIMPORT);
                            break;
                        case "9":
                            view.setCommand(Command.CHOOSEEXPORT);
                            break;
                        case "10":
                            view.setCommand(Command.CLOSING);
                            break;
                        default:
                            view.setCommand(Command.ERROR);                        
                    }
                    break;
                
                case SETSEARCHTYPE:
                    String searchNum = input.nextLine();
                    Command searchCommand = Command.SORTTYPE;
                    this.skipInput = false;
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
                        if(!isDatabase){
                            this.user.setSearchStrategy(new SearchByIssueNumber(this.collection));
                            this.skipInput=true;
                            this.view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            this.view.setCommand(Command.NOTAPLICABLE);
                        }
                    }
                    else if(searchNum.equals("6")){
                        if(!isDatabase){
                            this.user.setSearchStrategy(new SearchForGraded(this.collection));
                            this.skipInput=true;
                            this.view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            this.view.setCommand(Command.NOTAPLICABLE);
                        }
                    }
                    else if(searchNum.equals("7")){
                        if(!isDatabase){
                            this.user.setSearchStrategy(new SearchForSlabbed(this.collection));
                            this.skipInput=true;
                            this.view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            this.view.setCommand(Command.NOTAPLICABLE);
                        }
                    }
                    else if(searchNum.equals("8")){
                        if(!isDatabase){
                            this.user.setSearchStrategy(new SearchBySigned(this.collection));
                            this.skipInput=true;
                            this.view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            this.view.setCommand(Command.NOTAPLICABLE);
                        }
                    }
                    else if(searchNum.equals("9")){
                        if(!isDatabase){
                            this.user.setSearchStrategy(new SearchForRuns(this.collection));
                            this.skipInput=true;
                            this.view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            this.view.setCommand(Command.NOTAPLICABLE);
                        }
                    }
                    else if(searchNum.equals("10")){
                        if(!isDatabase){
                            this.user.setSearchStrategy(new SearchForGaps(this.collection));
                            this.skipInput=true;
                            this.view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            this.view.setCommand(Command.NOTAPLICABLE);
                        }
                    }
                    else if(searchNum.equals("11")){
                        if(this.signedInUser != null){
                            view.setCommand(Command.SORTTYPE);
                        }
                        else{
                            view.setCommand(Command.GUEST);
                        }
                    }
                    else{
                        view.setCommand(Command.ERROR);
                    }
                    break;
                
                case SORTTYPE:
                    String ifSort = input.nextLine();
                    if(ifSort.equals("1")){
                        view.setCommand(Command.SETSORTTYPE);
                    }
                    else if(ifSort.equals("2")){
                        this.user.setSortStrategy(new SortByDefault());
                        if(skipInput){this.view.setCommand(Command.COLLECTIONSEARCH);;}
                        else{view.setCommand(Command.SEARCHING);}
                    }
                    else{
                        view.setCommand(Command.ERROR);
                    }
                    break;

                case SETSORTTYPE:
                    String chooseSort = input.nextLine();
                    if(chooseSort.equals("1")){
                        this.user.setSortStrategy(new SortBySeriesTitle());
                    }
                    else if(chooseSort.equals("2")){
                        this.user.setSortStrategy(new SortByVolume());
                    }
                    else if(chooseSort.equals("3")){
                        this.user.setSortStrategy(new SortByIssueNumber());
                        
                    }
                    else if(chooseSort.equals("4")){
                        this.user.setSortStrategy(new SortByPublicationDate());
                    }

                    if(skipInput){this.view.setCommand(Command.COLLECTIONSEARCH);;}
                    else{view.setCommand(Command.SEARCHING);}

                    break;
                
                case SEARCHING:
                    this.currentResult = this.user.search(input.nextLine(), isDatabase);
                    if(isDatabase){this.user.setSortStrategy(new SortByDefault());}
                    this.currentResult = this.user.sort(this.currentResult);
                    this.view.setResults(this.currentResult);
                    this.view.setCommand(Command.RESULTS);
                    break;

                case COLLECTIONSEARCH:
                    this.currentResult = this.user.search(null, isDatabase);
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
                    }
                    break;
                case ADDFROMINPUT:
                    String comicInput = input.nextLine();
                    String[] separatedComic = comicInput.split(";");
                    if(separatedComic.length == 8){
                        Publisher pub = new Publisher(separatedComic[0]);
                        ArrayList<Author> authors = new ArrayList<Author>();
                        for(String a:separatedComic[5].split(",")){
                            authors.add(new Author(a));
                        }
                        ArrayList<String> characters = new ArrayList<String>(Arrays.asList(separatedComic[6].split(",")));
                        ComicBookComponent comicBook = new ComicBookComponent(pub, separatedComic[1], separatedComic[2], separatedComic[3], separatedComic[4], authors, characters, separatedComic[7]);
                        this.signedInUser.setCommand(new AddAction(signedInUser, userDAO));
                        this.signedInUser.executeCommand(comicBook);
                        this.view.setCommand(Command.ADDED);
                    }
                    else{
                        this.view.setCommand(Command.ERROR);
                    }
                    break;

                case ADDED:
                    this.view.setCommand(Command.SIGNEDINUSER);
                    break;
                case REMOVED:
                    this.view.setCommand(Command.SIGNEDINUSER);
                    break;
             
                case ADDFROMDB, REMOVEFROMCOLLECTION, SLAB, GRADE, SIGN, AUTHENTICATE:
                    String comicSelection = input.nextLine();
                    String[] addRemove = comicSelection.split(";");
                    if(addRemove.length == 3){
                        this.isDatabase = false;
                        Command finalCommand = Command.EDITED;
                        if(this.view.getCommand().equals(Command.REMOVEFROMCOLLECTION)){
                            finalCommand = Command.REMOVED;
                        }
                        else if(this.view.getCommand().equals(Command.ADDFROMDB)){
                            this.isDatabase = true;
                            finalCommand = Command.ADDED;
                        }
                        user.setSearchStrategy(new SearchBySeriesTitle(collection, database));

                        ArrayList<ComicBook> results = user.search(addRemove[0], this.isDatabase);
                        ComicBook realResult = null;

                        for(ComicBook book:results){
                            if(book.getVolNum().equals(addRemove[1])){
                                if(book.getIssueNumber().equals(addRemove[2])){
                                    realResult = book;
                                }
                            }
                        }
                        if(this.view.getCommand().equals(Command.REMOVEFROMCOLLECTION)){
                            this.signedInUser.setCommand(new RemoveAction(signedInUser, userDAO));
                        }
                        else if(this.view.getCommand().equals(Command.SLAB)){
                            this.signedInUser.setCommand(new SlabbedAction(signedInUser, userDAO));
                        }
                        else if(this.view.getCommand().equals(Command.GRADE)){
                            this.signedInUser.setCommand(new GradeAction(signedInUser, userDAO, this.grade));
                        }
                        else if(this.view.getCommand().equals(Command.SIGN)){
                            this.signedInUser.setCommand(new SignedAction(signedInUser, userDAO));
                        }
                        else if(this.view.getCommand().equals(Command.AUTHENTICATE)){
                            this.signedInUser.setCommand(new AuthenticatedAction(signedInUser, userDAO));
                        }
                        else{
                            this.signedInUser.setCommand(new AddAction(signedInUser, userDAO));
                        }

                        if(realResult != null){
                            this.signedInUser.executeCommand(realResult);
                            this.view.setCommand(finalCommand);
                        }
                        else{
                            this.view.setCommand(Command.ERROR);
                        }
                        System.out.println(this.collection.toString());
                        System.out.println(this.database.toString());
                    }
                    else{
                        this.view.setCommand(Command.ERROR);
                    }
                    break;

                case EDITMARKSELECTION:
                    String chooseEdit = input.nextLine();
                    if(chooseEdit.equals("1")){
                        this.view.setCommand(Command.CHOOSEGRADE);
                    }
                    else if(chooseEdit.equals("2")){
                        this.view.setCommand(Command.SLAB);
                    }
                    else if(chooseEdit.equals("3")){
                        this.view.setCommand(Command.SIGN);
                    }
                    else if(chooseEdit.equals("4")){
                        this.view.setCommand(Command.AUTHENTICATE);
                    }
                    else if(chooseEdit.equals("5")){
                        this.view.setCommand(Command.SIGNEDINUSER);
                    }
                    else{
                        this.view.setCommand(Command.ERROR);
                    }
                    break;

                case CHOOSEGRADE:
                    this.grade = Integer.parseInt(input.nextLine());
                    this.view.setCommand(Command.GRADE);
                    break;
                
                case CHOOSEIMPORT:
                    String chooseImport = input.nextLine();
                    if(chooseImport.equals("1")){
                        this.fileType = FileType.CSV;
                        this.view.setCommand(Command.IMPORT);
                    }
                    else if(chooseImport.equals("2")){
                        this.fileType = FileType.JSON;
                        this.view.setCommand(Command.IMPORT);
                    }
                    else if(chooseImport.equals("3")){
                        this.fileType = FileType.XML;
                        this.view.setCommand(Command.IMPORT);
                    }
                    else if(chooseImport.equals("4")){
                        this.view.setCommand(Command.SIGNEDINUSER);
                    }
                    else{
                        this.view.setCommand(Command.ERROR);
                    }
                    break;
                
                case CHOOSEEXPORT:
                    String chooseExport = input.nextLine();
                    if(chooseExport.equals("1")){
                        this.fileType = FileType.CSV;
                        this.view.setCommand(Command.EXPORT);
                    }
                    else if(chooseExport.equals("2")){
                        this.fileType = FileType.JSON;
                        this.view.setCommand(Command.EXPORT);
                    }
                    else if(chooseExport.equals("3")){
                        this.fileType = FileType.XML;
                        this.view.setCommand(Command.EXPORT);
                    }
                    else if(chooseExport.equals("4")){
                        this.view.setCommand(Command.SIGNEDINUSER);
                    }
                    else{
                        this.view.setCommand(Command.ERROR);
                    }
                    break;

                case IMPORT:
                    String fileNameIn = input.nextLine();
                    Converter converterIN = new Converter(FileType.SQL, fileType, database);
                    this.database.loadData(converterIN.convertFileToJava(fileNameIn));
                    this.view.setCommand(Command.IMPORTED);
                    break;
                    
                case EXPORT:
                    //String fileNameOut = input.nextLine();
                    Converter converterOUT = new Converter(fileType, FileType.SQL, database);
                    ArrayList<ComicBookComponent> comicsOUT = converterOUT.convertFileToJava(null);
                    converterOUT.convertJavaToFile(comicsOUT);
                    this.view.setCommand(Command.EXPORTED);
                    break;
                
                case IMPORTED, EXPORTED:
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

                case NOTAPLICABLE, ERROR, NEWACCOUNTERROR:
                    if(this.signedInUser == null){view.setCommand(Command.GUEST);}
                    else{view.setCommand(Command.SIGNEDINUSER);}
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
        //this.database.loadData(FileType.CSV, "src/data/comics(1).csv");
        //this.database.BuildSample();
        this.user = new User();
        this.view = new PTUI();
        run();
    }
}
