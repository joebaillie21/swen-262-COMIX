package com.swen.comix.model;

import java.util.ArrayList;
/*
 * @Author Angela 
 * For each of the classes that inherit this interface it will be creating the new filetype of each 
 * XML, JSON, or CSV
 */
public interface Exporter {
    public String toFile();
}
