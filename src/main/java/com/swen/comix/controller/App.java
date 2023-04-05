package com.swen.comix.controller;

import java.util.Scanner;

import com.swen.comix.model.User;
import com.swen.comix.view.PTUI;

public class App {
    private User user;
    private PTUI view;
    private Scanner input;
    private Boolean running;

    public App(){
        init();
    }

    public void run(){
        running = true;
        while(running){
            input = new Scanner(System.in);
            if(view.viewCommand.equals("Start")){
                String newInput = input.nextLine();
                if(newInput.equals("1")){
                    view.setCommand("SignIn");
                    view.handleCommand();
                }
            }
            else if(view.viewCommand.equals("SignIn")){
                String newInput = input.nextLine();
                if(newInput.equals("1")){
                    System.out.println("woo");
                }
            }
        }
    }

    public void init(){
        this.view = new PTUI();
        run();
    }
}
