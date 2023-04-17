package com.swen.comix.view;

import java.util.ArrayList;

import com.swen.comix.controller.Command;
import com.swen.comix.model.ComicBook;
/*
 * @Author Ash Roushinko
 */
public class PTUI {
    private Command viewCommand;
    private String results;

    public PTUI(){
        init();
    }

    public void handleCommand(){
        switch(viewCommand){
            case GUEST:
                System.out.println("Enter the number next to the command you want to select it:\n1.) Sign-in\n2.) Sign-up\n3.) Browse/Search Database\n4.) Browse Collections\n5.) Close Program");
                break;
            case SIGNIN, SIGNUP:
                System.out.println("Input Username:");
                break;
            case SIGNINPASSWORD, SIGNUPPASSWORD:
                System.out.println("Input Password:");
                break;
            case SIGNINCOMPLETE:
                System.out.println("Signin Complete\n");
                break;
            case SETSEARCHTYPE:
                System.out.println("\nHow Would You Like To Search:\n1.)Series Title\n2.)Principle Character\n3.)Author\n4.)Description\n5.)Go Back");
                break;
            case SORTTYPE:
                System.out.println("Do you want to sort the data (Default is Series Title, Volume, Issue Number)\n1.)Yes\n2.)No");
                break;
            case SETSORTTYPE:
                System.out.println("How do you want to sort the data?\n1.)By Series Title\n2.)By Volume\n3.)By Issue Number\n4.)By Publication Date");
                break;
            case RESULTS:
                System.out.println("Results:\n"+this.results);
                break;
            case SEARCHING:
                System.out.println("What would you like to search?");
                break;
            case SEARCHDATABASE, SEARCHCOLLECTION:
                System.out.println("What Would You Like to Seach For?");
                break;
            case BROWSEOTHERCOLLECTION:
                System.out.println("Who's collection would you like to view?");
                break;
            case OTHERCOLLECTIONRESULT:
                System.out.println("Their pc:\n");
                break;
            case SIGNEDINUSER:
                System.out.println("Enter the number next to the command you want to select it:\n1.) Browse/Search Database\n2.) Search Personal Collection\n3.) Add to Personal Collection\n4.) Remove from Personal Collection\n5.) Edit/Mark Comic In Personal Collection\n6.) Undo Command\n7.) Redo Command\n8.) Import/Export Database\n9.) Close");
                break;
            case HOWTOADD:
                System.out.println("How do you want to add?\n1.)Add from database\n2.)Add a new comic");
                break;
            case ADDFROMINPUT:
                System.out.println("Input Comic:\nFormat should be 'Publisher; Title; Volume Number; Issue Number; Publication Date; Authors; Characters; Description'\nCharacters and Authors should be separated by commas. Date format is MM/DD/YYYY");
                break;
            case ADDED:
                System.out.println("Comic Added to Collection");
                break;
            case UNDO:
                System.out.println("Undid Last Command\n");
                break;
            case REDO:
                System.out.println("Redid Last Command\n");
                break;
            case NEWACCOUNTERROR:
                System.out.println("A User with this username and password already exists\nPlease Try Again:\n");
                break;
            case CLOSING:
                System.out.println("Closing Program");
                break;
            default:
                System.out.println("Invalid Input\nPlease Try Again:\n");
        }
    }

    public Command getCommand(){return viewCommand;}

    public void setCommand(Command input){viewCommand = input;}

    public void setResults(ArrayList<ComicBook> Results){
        this.results = "";
        for (ComicBook comic : Results) {
            this.results += comic.toString()+"\n";
        }
    }

    public void init(){
        this.viewCommand = Command.GUEST;
        handleCommand();
    }
}
