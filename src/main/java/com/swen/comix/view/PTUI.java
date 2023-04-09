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
            System.out.println("Enter the number next to the command you want to select it:\n1.) Sign-in\n2.) Sign-up\n3.) Browse/Search Database\n4.) Browse Collections\n5.) Close Program");
        }

        else if(viewCommand.equals(Command.SIGNIN) || viewCommand.equals(Command.SIGNUP)){
            System.out.println("Input Username:");
        }

        else if(viewCommand.equals(Command.SIGNINPASSWORD) || viewCommand.equals(Command.SIGNUPPASSWORD)){
            System.out.println("Input Password:");
        }

        else if(viewCommand.equals(Command.SIGNINCOMPLETE)){
            System.out.println("Signin Complete\n");
        }

        else if(viewCommand.equals(Command.BROWSEDATABASE)){
            System.out.println("");
        }
        
        else if(viewCommand.equals(Command.BROWSEOTHERPC)){
            System.out.println("Who's collection would you like to view?");
        }

        else if(viewCommand.equals(Command.PCRESULT)){
            System.out.println("Their pc:\n");
        }

        else if(viewCommand.equals(Command.SIGNEDINUSER)){
            System.out.println("Enter the number next to the command you want to select it:\n1.) Browse/Search Database\n2.) Search Personal Collection\n3.) Add to Personal Collection\n4.) Remove from Personal Collection\n5.) Edit/Mark Comic In Personal Collection\n6.) Undo Command\n7.) Redo Command\n8.) Close");
        }

        else if(viewCommand.equals(Command.NEWACCERROR)){
            System.out.println("A User with this username and password already exists\nPlease Try Again:\n");
        }

        else if(viewCommand.equals(Command.CLOSING)){
            System.out.println("Closing Program");
        }

        else{
            System.out.println("Invalid Input\nPlease Try Again:\n");
        }
    }

    public Command getCommand(){return viewCommand;}

    public void setCommand(Command input){viewCommand = input;}

    public void init(){
        this.viewCommand = Command.GUEST;
        handleCommand();
    }
}
