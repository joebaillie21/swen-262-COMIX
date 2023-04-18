package com.swen.comix.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.swen.comix.db.*; 
public class SearchSortDBTest {
/* 
    Database database = new Database();

    @Before
    public void loadData() throws Exception {
        database.BuildSample();
    }

    @Test
    public void testSearchByAuthor() throws Exception {

        // Setup

        SearchByAuthor search = new SearchByAuthor(database);

        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author("Joe Baillie"));

        ArrayList<String> pc = new ArrayList<>();
        pc.add("Joe");

        ArrayList<ComicBook> expectedAL = new ArrayList<>();
        expectedAL.add(new ComicBookComponent(new Publisher("262 Publishing"), "Joes Comic", "1", "24", "2003-01-02",
                authors, pc, null));
        Object[] expected = expectedAL.toArray();

        // Invoke

        ArrayList<ComicBook> actualAL = search.algorithm("Joe", true);
        Object[] actual = actualAL.toArray();

        // Analyze

        String author = actualAL.get(0).getAuthors().get(0).getName();
        String expectedAuthor = expectedAL.get(0).getAuthors().get(0).getName();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void testSearchByAuthorSingleChar() throws Exception {

        // Setup

        SearchByAuthor search = new SearchByAuthor(database);

        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author("Joe Baillie"));

        ArrayList<String> pc = new ArrayList<>();
        pc.add("Joe");

        ArrayList<ComicBook> expectedAL = new ArrayList<>();
        expectedAL.add(new ComicBookComponent(new Publisher("262 Publishing"), "Joes Comic", "1", "24", "2003-01-02",
                authors, pc, null));
        Object[] expected = expectedAL.toArray();

        // Invoke

        ArrayList<ComicBook> actualAL = search.algorithm("J", true);
        Object[] actual = actualAL.toArray();

        // Analyze

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchByPrincipleCharacter() throws Exception {

        // Setup

        SearchByPrincipleCharacter search = new SearchByPrincipleCharacter(database);

        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author("Joe Baillie"));

        ArrayList<String> pc = new ArrayList<>();
        pc.add("Joe");

        ArrayList<ComicBook> expectedAL = new ArrayList<>();
        expectedAL.add(new ComicBookComponent(new Publisher("262 Publishing"), "Joes Comic", "1", "24", "2003-01-02",
                authors, pc, null));
        Object[] expected = expectedAL.toArray();

        // Invoke

        ArrayList<ComicBook> actualAL = search.algorithm("J", true);
        Object[] actual = actualAL.toArray();

        // Analyze

        assertArrayEquals(expected, actual);

    }

    @Test
    public void testSearchBySeriesTitle() throws Exception {

        // Setup

        SearchBySeriesTitle search = new SearchBySeriesTitle(database);

        ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author("Joe Baillie"));

        ArrayList<String> pc = new ArrayList<>();
        pc.add("Joe");

        ArrayList<ComicBook> expectedAL = new ArrayList<>();
        expectedAL.add(new ComicBookComponent(new Publisher("262 Publishing"), "Joes Comic", "1", "24", "2003-01-02",
                authors, pc, null));
        Object[] expected = expectedAL.toArray();

        // Invoke

        ArrayList<ComicBook> actualAL = search.algorithm("J", true);
        Object[] actual = actualAL.toArray();

        // Analyze

        assertArrayEquals(expected, actual);

    }
*/
}
