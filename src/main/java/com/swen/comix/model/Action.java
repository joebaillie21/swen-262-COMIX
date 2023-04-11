package com.swen.comix.model;

public interface Action {
    public void execute(ComicBook comic); 
    public boolean isReversible(); 
    public void unexecute(); 
    public void redo();
}
