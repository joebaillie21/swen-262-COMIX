package com.swen.comix.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swen.comix.persistence.UserDAO;
import com.swen.comix.persistence.UserFileDAO;

public class PersonalCollectionTest {
    private File dataFile;
    private ObjectMapper mockMapper;
    private UserFileDAO userFileDao;
    private ComixMediator mediator;
    private Guest guest;

    @Before
    public void setup() throws IOException{
        dataFile = new File("src/data/temp.json");
        if (dataFile.exists()) {
            dataFile.delete();
        }
        
        FileWriter writer = new FileWriter(dataFile);
        writer.write("[]");
        writer.close();

        mockMapper = new ObjectMapper();
        userFileDao = new UserFileDAO("src/data/temp.json", mockMapper);
        mediator = new ComixLogin(userFileDao);
        guest = new Guest(mediator);
    }

    @After
    public void tearDown() throws IOException{
        dataFile.delete();
    }

    @Test
    public void testAddComic() throws IOException{
        SignedInUser user = guest.createAccount("user", "pass");
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, "15", authors, null, null);
        user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
    }

    @Test
    public void testAddComicUndo() throws IOException{
        SignedInUser user = guest.createAccount("user", "pass");
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ArrayList<Author> authors2 = new ArrayList<Author>();
        authors2.add(new Author("Man Bat"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, "15", authors, null, null);
        ComicBook comic2 = new ComicBookComponent(new Publisher("DC"), "BatMan", 0, 0, "12", authors2, null, null);
        user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic2);
        user.unexecuteCommand();

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        user.unexecuteCommand();
        assertEquals("Collection [userName=user, collection=[]]", user.getPersonalCollection().toString());

        user.setCommand(new AddAction(user.getPersonalCollection()));

        user.executeCommand(comic);
        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
    }

    @Test
    public void testRemoveComic() throws IOException{
        ObjectMapper mockMapper = new ObjectMapper();
        UserDAO userFileDao = new UserFileDAO("src/data/temp.json", mockMapper);
        ComixMediator mediator = new ComixLogin(userFileDao);
        Guest guest = new Guest(mediator);
        SignedInUser user = guest.createAccount("user", "pass");
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, "15", authors, null, null);
        user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.setCommand(new RemoveAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals("Collection [userName=user, collection=[]]", user.getPersonalCollection().toString());
    }

    @Test
    public void testRemoveComicUndo() throws IOException{
        ObjectMapper mockMapper = new ObjectMapper();
        UserDAO userFileDao = new UserFileDAO("src/data/temp.json", mockMapper);
        ComixMediator mediator = new ComixLogin(userFileDao);
        Guest guest = new Guest(mediator);
        SignedInUser user = guest.createAccount("user", "pass");
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, "15", authors, null, null);
        user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.setCommand(new RemoveAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.unexecuteCommand();

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
    }


    @Test
    public void testGradeComic() throws IOException{
        ObjectMapper mockMapper = new ObjectMapper();
        UserDAO userFileDao = new UserFileDAO("src/data/temp.json", mockMapper);
        ComixMediator mediator = new ComixLogin(userFileDao);
        Guest guest = new Guest(mediator);
        SignedInUser user = guest.createAccount("user", "pass");
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, "15", authors, null, null);
        user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        user.setCommand(new GradeAction(user.getPersonalCollection(), 10));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        assertEquals(10, comic.getGrade());
    }

    @Test
    public void testGradeComicUndo() throws IOException{
        ObjectMapper mockMapper = new ObjectMapper();
        UserDAO userFileDao = new UserFileDAO("src/data/temp.json", mockMapper);
        ComixMediator mediator = new ComixLogin(userFileDao);
        Guest guest = new Guest(mediator);
        SignedInUser user = guest.createAccount("user", "pass");
        ArrayList<Author> Authors = new ArrayList<Author>();
        Authors.add(new Author("Stan Lee"));
        PersonalCollection collection = new PersonalCollection("user");
        ArrayList<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", 0, 0, "15", authors, null, null);
        user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        user.setCommand(new GradeAction(user.getPersonalCollection(), 10));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        assertEquals(10, comic.getGrade());

        user.unexecuteCommand();

        assertEquals(1, comic.getGrade());
    }
}
