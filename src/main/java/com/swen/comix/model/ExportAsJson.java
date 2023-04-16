package com.swen.comix.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExportAsJson implements Exporter{
    private ArrayList<ComicBookComponent> comics;
    private ObjectMapper objectMapper;
    
    public ExportAsJson(ArrayList<ComicBookComponent> comics2){
        this.comics = comics2;
    }

    @Override
    public String toFile() throws IOException{
        try{
            objectMapper.writeValue(new File("src/data/output.json"),comics);
            return "src/data/output.json";
        }
        catch(IOException fileException){
            return "failed to write to file";
        }
    }
}
