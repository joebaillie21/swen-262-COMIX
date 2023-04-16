package com.swen.comix.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {
    private String name; 
    public Author(@JsonProperty("name")String name){
        this.name = name; 
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Author)){
            return false;
        }
        Author other = (Author) o; 
        return (other.getName().equals(this.name)); 
    }

    @Override
    public String toString(){
        return name;
    }
}
