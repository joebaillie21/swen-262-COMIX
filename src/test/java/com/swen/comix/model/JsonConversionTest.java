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

public class JsonConversionTest {
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
        user = guest.createAccount("userTest", "passTest");
        collection = new PersonalCollection("userTest");
        authors = new ArrayList<Author>();
        authors.add(new Author("Stan Lee"));
        comic = new ComicBookComponent(new Publisher("Marvel"), "Spiderman", "0", "0", "15", authors, null, null);
        user.setCommand(new AddAction(user, userFileDao));
        user.executeCommand(comic);
    }

    @After
    public void tearDown(){
        dataFile.delete();
    }

    @Test
    public void testImportJson() throws Exception{
        user.importFile("src/data/jsonTestComics.json", FileType.JAVA, FileType.JSON, userFileDao);

        assertEquals("Collection [userName=userTest, collection=[[Publisher=262 Publishing, Author=[Joe Baillie], Title=Joes Comic, Description=null, VolNum=1, IssueNum=24, Characters=[Joe]]]]", user.getPersonalCollection().toString());
    }

    @Test
    public void testExportJson() throws Exception{
        user.exportFile(FileType.JSON, FileType.JAVA, null);

        File outputDataFile = new File("src/data/output.json");
        if (outputDataFile.exists()) {
            outputDataFile.delete();
        }
    }
}
