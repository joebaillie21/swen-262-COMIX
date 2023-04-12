package com.swen.comix.model;

public interface SortStrategy {
    public PersonalCollection algorithm(String toBeSorted, boolean isSortDb); 
    // debating if the sort returns a personal collection or just the setting of it...?
}
