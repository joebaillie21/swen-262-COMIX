package com.swen.comix.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.swen.comix.model.PersonalCollection;
import com.swen.comix.persistence.PersonalCollectionDAO;
import com.swen.comix.persistence.PersonalCollectionFileDAO;
import com.swen.comix.persistence.UserDAO;
import com.swen.comix.persistence.UserFileDAO;

public class PersonalCollectionTest {

    /**
     * TODO
     * Does not test anything yet, just checks for errors
     * @throws IOException
     */
    @Test
    public void testAddManual() throws IOException{
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
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", "0", "0", null, authors, null, null);
        user.setCollection(collection);
        userFileDao.addComic(comic, user.getName());

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");

        datafile.delete();
    }
}
