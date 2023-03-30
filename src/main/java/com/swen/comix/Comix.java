package com.swen.comix;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.model.Author;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.ComicBookComponent;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.Publisher;
import com.swen.comix.persistence.PersonalCollectionDAO;
import com.swen.comix.persistence.PersonalCollectionFileDAO;

public class Comix {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
    }
}