package com.swen.comix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.model.Author;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.ComicBookComponent;
import com.swen.comix.model.ComixLogin;
import com.swen.comix.model.ComixMediator;
import com.swen.comix.model.Guest;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.model.Publisher;
import com.swen.comix.model.User;
import com.swen.comix.persistence.PersonalCollectionDAO;
import com.swen.comix.persistence.PersonalCollectionFileDAO;
import com.swen.comix.persistence.UserDAO;
import com.swen.comix.persistence.UserFileDAO;

public class Comix {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        File datafile = new File("src/data/temp.json");
        if (datafile.exists()) {
            datafile.delete();
        }
        
        FileWriter writer = new FileWriter(datafile);
        writer.write("[]");
        writer.close();

        ObjectMapper mockMapper = new ObjectMapper();
        UserDAO userFileDao = new UserFileDAO("src/data/temp.json", mockMapper);
        ComixMediator mediator = new ComixLogin(userFileDao);
        Guest guest = new Guest(mediator);
        User user = guest.createAccount("user", "pass");

        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", "0", "0", null, authors, null, null);
        user.setCollection(collection);
        userFileDao.addComic(comic, user.getName());

        System.out.println(user.getPersonalCollection());

        datafile.delete();
    }
}