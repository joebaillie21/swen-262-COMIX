package com.swen.comix.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImportFromJson implements Importer{
    private String fileName;
    private ObjectMapper objectMapper;

    public ImportFromJson(String fileName){
        this.fileName = fileName;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ArrayList<ComicBookComponent> importToJava() throws IOException{
        ArrayList<ComicBookComponent> comics = objectMapper.readValue(new File(this.fileName), new TypeReference<ArrayList<ComicBookComponent>>(){});
        return comics;
    }
}
