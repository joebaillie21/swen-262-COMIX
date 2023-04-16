package com.swen.comix.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Publisher {
    private String name; 
    public Publisher(@JsonProperty("name")String name){
        this.name = name; 
    }

    public String getName(){
        return name;
    }
}
