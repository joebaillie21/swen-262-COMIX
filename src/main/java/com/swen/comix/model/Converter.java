package com.swen.comix.model;

import java.util.ArrayList;

/**
 * @author Joe 
 * Joe is doing this not really sure how this works and am just writing out what was on the board yesterday 
 */
public class Converter {
    private String toType, fromType; 
    private boolean db; 

    /**
     * this calls the appropriate importer based on switch statement 
     * and it also calls the appropriate exporter based on switch statement 
     * convert then after returns the filepath of the new exported file 
     * @param filename
     * @return
     */
    public String convert(String filename){
        return ""; 
    }

    public ArrayList<ComicBookComponent> convertFileToJava(){
        //switch based on from type (the toType will be java if this method is called)
            //return the new java object which will replace the caller's personal colelction
        return null;
    }
}
