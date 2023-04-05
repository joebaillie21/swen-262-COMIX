package com.swen.comix.view;


public class PTUI {
    public String viewCommand;

    public PTUI(){
        init();
    }

    public void handleCommand(){
        if(viewCommand.equals("Start")){
            System.out.println("Enter the number of the command u want:\n 1.) sign-in\n 2.) use as guest");
        }
        else if(viewCommand.equals("SignIn")){
            System.out.println("Password:");
        }
    }

    public void setCommand(String input){
        viewCommand = input;
    }

    public void init(){
        this.viewCommand = "Start";
        handleCommand();
    }
}
