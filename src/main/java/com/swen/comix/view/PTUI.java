package com.swen.comix.view;

import javax.lang.model.util.ElementScanner14;

import com.swen.comix.controller.Command;
public class PTUI {
    private Command viewCommand;

    public PTUI(){
        init();
    }

    public void handleCommand(){
        if(viewCommand.equals(Command.GUEST)){
            System.out.println("Enter the number of the command u want:\n 1.) sign-in\n 2.) Browse/Search Database\n 3.) Browse Collections\n 4.) Close Program");
        }

        else if(viewCommand.equals(Command.SIGNIN)){
            System.out.println("Input Username:");
        }

        else if(viewCommand.equals(Command.SIGNINPASSWORD)){
            System.out.println("Input Password:");
        }

        else if(viewCommand.equals(Command.BROWSEDATABASE)){
            System.out.println("");
        }
        
        else if(viewCommand.equals(Command.BROWSECOLLECTIONS)){
            System.out.println("Who's collection would you like to view?");
        }

        else if(viewCommand.equals(Command.SIGNEDINUSER)){
            System.out.println("Enter the number of the command u want:\n1.) Browse/Search Database\n2.) Search Personal Collection\n3.) Add to Personal Collection\n4.) Remove from Collection\n5.) Edit Comic In Collection\n6.) Mark Comic In Collection\n7.) Undo Command\n8.) Redo Command\n9.) Close");
        }

        else if(viewCommand.equals(Command.CLOSING)){
            System.out.println("Closing Program");
        }

        else{
            System.out.println("Invalid Input\nTry Again:");
        }
    }

    public Command getCommand(){return viewCommand;}

    public void setCommand(Command input){
        viewCommand = input;
    }

    public void init(){
        this.viewCommand = Command.GUEST;
        handleCommand();
    }
}
