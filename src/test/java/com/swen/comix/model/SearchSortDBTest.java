package com.swen.comix.model;

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

    Database database = new Database();

    @Before
    public void loadData() throws Exception {
        database.BuildSample();
    }

    @Test
    public void testSearchByAuthor() throws Exception {

        // Setup

        SearchByAuthor search = new SearchByAuthor(database);

        ArrayList<ComicBook> expected = new ArrayList<>();

        // Invoke

        ArrayList<ComicBook> actual = search.algorithm("Joe", true);

        // Analyze

        assertEquals(expected, actual);
    }
}
