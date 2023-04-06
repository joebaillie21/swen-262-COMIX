package com.swen.comix.model;

public class SortBySeriesTitle implements SortStrategy{
    private PersonalCollection personalCollection; 
    public SortBySeriesTitle(PersonalCollection personalCollection){
        this.personalCollection = personalCollection; 
    }
    @Override
    public PersonalCollection algorithm() {
       
    }
    
}
