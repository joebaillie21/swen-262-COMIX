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
import com.swen.comix.model.SignedInUser;
import com.swen.comix.model.User;
import com.swen.comix.persistence.UserDAO;
import com.swen.comix.persistence.UserFileDAO;
import com.swen.comix.view.PTUI;

public class App {
    private User user;
    private Guest guest;
    private UserFileDAO userDAO;
    private ComixMediator mediator;
    private PTUI view;
    private Scanner input;
    private Boolean running;
    private String username;
    private String password;

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
                    view.handleCommand();
                }
                else if(newInput.equals("2")){
                    view.setCommand(Command.SIGNUP);
                    view.handleCommand();
                }
                else if(newInput.equals("3")){
                    view.setCommand(Command.BROWSECOLLECTIONS);
                    view.handleCommand();
                }
                else if(newInput.equals("4")){
                    view.setCommand(Command.BROWSECOLLECTIONS);
                    view.handleCommand();
                }
                else if(newInput.equals("5")){
                    view.setCommand(Command.CLOSING);
                    view.handleCommand();
                    running = false;
                }
                else{
                   view.setCommand(Command.ERROR);
                   view.handleCommand();
                   view.setCommand(Command.GUEST);
                   view.handleCommand();
                }
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
                    view.setCommand(Command.SIGNEDINUSER);
                    view.handleCommand();
                } catch (Exception e) {
                    view.setCommand(Command.NEWACCERROR);
                    view.handleCommand();
                    view.setCommand(Command.GUEST);
                    view.handleCommand();
                }
            }
            //USER COMMANDS

        }
    }

    public void init() throws IOException{
        ObjectMapper mockMapper = new ObjectMapper();
        mockMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.userDAO = new UserFileDAO("src/data/temp.json", mockMapper);
        this.mediator = new ComixLogin(this.userDAO);
        this.guest = new Guest(mediator);
        this.view = new PTUI();
        run();
    }
}
