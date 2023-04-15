package com.swen.comix.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExportAsJson implements Exporter{
    private ArrayList<ComicBookComponent> comics;
    private ObjectMapper objectMapper;
    
    public ExportAsJson(ArrayList<ComicBookComponent> comics){
        this.comics = comics;
    }

    @Override
    public String export(String fileName) throws IOException{
        objectMapper.writeValue(new File(fileName),comics);
        return fileName;
    }
}
