package com.swen.comix.model;

import java.io.IOException;

public interface Action {
    public void execute(ComicBook comic) throws IOException; 
    public boolean isReversible(); 
    public void unexecute() throws IOException; 
    public void redo() throws IOException;
}
