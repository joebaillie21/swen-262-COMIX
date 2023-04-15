package com.swen.comix.model;

import java.util.ArrayList;

public class ExportAsXML implements Exporter{
    private ArrayList<ComicBookComponent> comics;
    
    public ExportAsXML(ArrayList<ComicBookComponent> comics){
        this.comics = comics;
    }

    @Override
    public String toFile(){
        return null; 
    } 
}
