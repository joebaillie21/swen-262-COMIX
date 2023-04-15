package com.swen.comix.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private UserDAO userFileDao;
    private ComixMediator mediator;
    private Guest guest;
    private SignedInUser user;
    private ArrayList<Author> authors;
    private PersonalCollection collection;
    private ComicBook comic;

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
        user = guest.createAccount("user", "pass");
        collection = new PersonalCollection("user");
        authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", "0", "0", "15", authors, null, null);
        user.setCollection(collection);
    }

    @After
    public void tearDown() throws IOException{
        dataFile.delete();
    }
    /* 
    @Test
    public void testAddComic() throws IOException{user.setCollection(collection);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
    }

    @Test
    public void testAddComicUndo() throws IOException{
        ArrayList<Author> authors2 = new ArrayList<Author>();
        authors2.add(new Author("Man Bat"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", "0", "0", "15", authors, null, null);
        ComicBook comic2 = new ComicBookComponent(new Publisher("DC"), "BatMan", "0", "0", "12", authors2, null, null);
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
        authors.add(new Author("Stan Lee"));
        ComicBook comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", "0", "0", "15", authors, null, null);
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.setCommand(new RemoveAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals("Collection [userName=user, collection=[]]", user.getPersonalCollection().toString());
    }

    @Test
    public void testRemoveComicUndo() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.setCommand(new RemoveAction(user.getPersonalCollection()));
        user.executeCommand(comic);
        user.unexecuteCommand();

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
    }


    @Test
    public void testGradeComic() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        comic.setValue(10);
        user.setCommand(new GradeAction(user.getPersonalCollection(), 10));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        assertEquals(10, comic.getGrade());
        assertEquals(10, comic.getValue(), 0);
    }

    @Test
    public void testGradeComicUndo() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        comic.setValue(10);
        user.setCommand(new GradeAction(user.getPersonalCollection(), 10));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        assertEquals(10, comic.getGrade());

        user.unexecuteCommand();

        assertEquals(1, comic.getGrade());
        assertEquals(10, comic.getValue(), 0);
    }

    @Test
    public void testSlabComic() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        comic.setValue(10);
        user.setCommand(new GradeAction(user.getPersonalCollection(), 10));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        assertEquals(10, comic.getGrade());

        user.setCommand(new SlabbedAction(collection));
        user.executeCommand(comic);

        assertEquals(20, comic.getValue(), 0);
    }

    @Test
    public void testSlabComicUndo() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        comic.setValue(10);
        user.setCommand(new GradeAction(user.getPersonalCollection(), 10));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");
        
        assertEquals(10, comic.getGrade());

        user.setCommand(new SlabbedAction(collection));
        user.executeCommand(comic);

        assertEquals(20, comic.getValue(), 0);

        user.unexecuteCommand();
        assertEquals(10, comic.getValue(), 0);
    }

    @Test
    public void testSignComic() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");

        comic.setValue(10);

        user.setCommand(new SignedAction(collection));
        user.executeCommand(comic);

        assertEquals(10.5, comic.getValue(), 0);
    }

    @Test
    public void testSignComicUndo() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");

        comic.setValue(10);

        user.setCommand(new SignedAction(collection));
        user.executeCommand(comic);

        assertEquals(10.5, comic.getValue(), 0);

        user.unexecuteCommand();
        assertEquals(10, comic.getValue(), 0);
    }

    @Test
    public void testAuthenticateComic() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");

        comic.setValue(10);

        user.setCommand(new AuthenticatedAction(collection));
        user.executeCommand(comic);

        assertEquals(10, comic.getValue(), 0);

        user.setCommand(new SignedAction(collection));
        user.executeCommand(comic);

        assertEquals(10.5, comic.getValue(), 0);

        user.setCommand(new AuthenticatedAction(collection));
        user.executeCommand(comic);

        assertEquals(12.6, comic.getValue(), 0);
    }

    @Test
    public void testAuthenticateComicUndo() throws IOException{
        user.setCommand(new AddAction(user.getPersonalCollection()));
        user.executeCommand(comic);

        assertEquals(user.getPersonalCollection().toString(), "Collection [userName=user, collection=[[Publisher=Marvel, Author=[Stan Lee], Title=Spiderman, Description=null, VolNum=0, IssueNum=0, Characters=null]]]");

        comic.setValue(10);

        user.setCommand(new AuthenticatedAction(collection));
        user.executeCommand(comic);

        assertEquals(10, comic.getValue(), 0);

        user.setCommand(new SignedAction(collection));
        user.executeCommand(comic);

        assertEquals(10.5, comic.getValue(), 0);

        user.setCommand(new AuthenticatedAction(collection));
        user.executeCommand(comic);

        assertEquals(12.6, comic.getValue(), 0);

        user.unexecuteCommand();

        assertEquals(10.5, comic.getValue(), 0);

        user.unexecuteCommand();

        assertEquals(10, comic.getValue(), 0);
    }
    */
}
