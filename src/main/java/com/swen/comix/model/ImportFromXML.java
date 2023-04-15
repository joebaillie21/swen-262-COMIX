package com.swen.comix.model;

import java.util.ArrayList;

public class ImportFromXML implements Importer{

    private String fileName;

    public ImportFromXML(String fileName){
        this.fileName = fileName;
    }
    
    @Override
    public ArrayList<ComicBookComponent> importToJava(){
        return null;
    }
}


