package com.swen.comix.model;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.swen.comix.model.SearchByAuthor;
public class SearchSortPCTest {
    
    @Test
    public void testSearchByAuthorPc() throws Exception{
        //set up 
        PersonalCollection testPc = new PersonalCollection("Lola"); 
        ArrayList<Author> author1= new ArrayList<>();
        author1.add(new Author("Joe"));

        ArrayList<Author> author2= new ArrayList<>();
        author2.add(new Author("Ash")); 
        ArrayList<Author> author3= new ArrayList<>();
        author3.add(new Author("Zach")); 
        ArrayList<Author> author4= new ArrayList<>();
        author4.add(new Author("Peyton"));
        ArrayList<Author> author5= new ArrayList<>(); 
        author5.add(new Author("Angela")); 

        ArrayList<ArrayList<Author>> authors = new ArrayList<>(); 
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authors.add(author4);
        authors.add(author5); 

        ArrayList<String> titles = new ArrayList<>(); 
        titles.add("meow meow"); 
        titles.add("laugh out loud"); 
        titles.add("kendrick lamar meow"); 
        titles.add("kirby epic land"); 
        titles.add("empire state building"); 

        Publisher pub = new Publisher("mewo"); 
        ArrayList<ComicBookComponent> pc = new ArrayList<>(); 
        for(int i = 0; i < 5; i++){
            ComicBook cb = new ComicBookComponent(pub, titles.get(i), "0", "0", "lp", authors.get(i), titles, "0");
            pc.add((ComicBookComponent) cb);
        }

        testPc.setPersonalCollection(pc);
        SearchByAuthor sba = new SearchByAuthor(testPc);

        ArrayList<ComicBook> result = sba.algorithm("Joe", false);
        Object[] expected = new Object[1];
        expected[0] = pc.get(0);

        Object[] actual = result.toArray(); 

        assertArrayEquals(expected, actual);

    }

    @Test
    public void testSearchByDescription() throws Exception{
        PersonalCollection testPc = new PersonalCollection("Lola"); 
        ArrayList<Author> author1= new ArrayList<>();
        author1.add(new Author("Joe"));

        ArrayList<Author> author2= new ArrayList<>();
        author2.add(new Author("Ash")); 
        ArrayList<Author> author3= new ArrayList<>();
        author3.add(new Author("Zach")); 
        ArrayList<Author> author4= new ArrayList<>();
        author4.add(new Author("Peyton"));
        ArrayList<Author> author5= new ArrayList<>(); 
        author5.add(new Author("Angela")); 

        ArrayList<ArrayList<Author>> authors = new ArrayList<>(); 
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authors.add(author4);
        authors.add(author5); 

        ArrayList<String> titles = new ArrayList<>(); 
        titles.add("meow meow"); 
        titles.add("laugh out loud"); 
        titles.add("kendrick lamar meow"); 
        titles.add("kirby epic land"); 
        titles.add("empire state building"); 

        ArrayList<String> descriptions = new ArrayList<>(); 
        descriptions.add("lala");
        descriptions.add("momo");
        descriptions.add("mimi");
        descriptions.add("papa");
        descriptions.add("lalalala");

        Publisher pub = new Publisher("mewo"); 
        ArrayList<ComicBookComponent> pc = new ArrayList<>(); 
        for(int i = 0; i < 5; i++){
            ComicBook cb = new ComicBookComponent(pub, titles.get(i), "0", "0", "lp", authors.get(i), titles, descriptions.get(i));
            pc.add((ComicBookComponent) cb);
        }

        testPc.setPersonalCollection(pc);
        SearchByDescription sbd = new SearchByDescription(testPc);
        ArrayList<ComicBook> result = sbd.algorithm("papa", false);
        Object[] actual = result.toArray(); 
        Object[] expected = new Object[1];
        expected[0] = pc.get(3);
        assertArrayEquals(expected, actual);


    }

    @Test
    public void testSearchBySeriesTitle() throws Exception{
        PersonalCollection testPc = new PersonalCollection("Lola"); 
        ArrayList<Author> author1= new ArrayList<>();
        author1.add(new Author("Joe"));

        ArrayList<Author> author2= new ArrayList<>();
        author2.add(new Author("Ash")); 
        ArrayList<Author> author3= new ArrayList<>();
        author3.add(new Author("Zach")); 
        ArrayList<Author> author4= new ArrayList<>();
        author4.add(new Author("Peyton"));
        ArrayList<Author> author5= new ArrayList<>(); 
        author5.add(new Author("Angela")); 

        ArrayList<ArrayList<Author>> authors = new ArrayList<>(); 
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authors.add(author4);
        authors.add(author5); 

        ArrayList<String> titles = new ArrayList<>(); 
        titles.add("meow meow"); 
        titles.add("laugh out loud"); 
        titles.add("kendrick lamar meow"); 
        titles.add("kirby epic land"); 
        titles.add("empire state building"); 

        ArrayList<String> descriptions = new ArrayList<>(); 
        descriptions.add("lala");
        descriptions.add("momo");
        descriptions.add("mimi");
        descriptions.add("papa");
        descriptions.add("lalalala");

        Publisher pub = new Publisher("mewo"); 
        ArrayList<ComicBookComponent> pc = new ArrayList<>(); 
        for(int i = 0; i < 5; i++){
            ComicBook cb = new ComicBookComponent(pub, titles.get(i), "0", "0", "lp", authors.get(i), titles, descriptions.get(i));
            pc.add((ComicBookComponent) cb);
        }

        testPc.setPersonalCollection(pc);
        SearchBySeriesTitle sbd = new SearchBySeriesTitle(testPc);
        ArrayList<ComicBook> result = sbd.algorithm("kirby epic land", false);
        Object[] actual = result.toArray(); 
        Object[] expected = new Object[1];
        expected[0] = pc.get(3);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchByPrincipleCharacter() throws Exception{
        PersonalCollection testPc = new PersonalCollection("Lola"); 
        ArrayList<Author> author1= new ArrayList<>();
        author1.add(new Author("Joe"));

        ArrayList<Author> author2= new ArrayList<>();
        author2.add(new Author("Ash")); 
        ArrayList<Author> author3= new ArrayList<>();
        author3.add(new Author("Zach")); 
        ArrayList<Author> author4= new ArrayList<>();
        author4.add(new Author("Peyton"));
        ArrayList<Author> author5= new ArrayList<>(); 
        author5.add(new Author("Angela")); 

        ArrayList<ArrayList<Author>> authors = new ArrayList<>(); 
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authors.add(author4);
        authors.add(author5); 

        ArrayList<String> titles = new ArrayList<>(); 
        titles.add("meow meow"); 
        titles.add("laugh out loud"); 
        titles.add("kendrick lamar meow"); 
        titles.add("kirby epic land"); 
        titles.add("empire state building"); 

        ArrayList<ArrayList<String>> characters = new ArrayList<>(); 
        ArrayList<String> character1 = new ArrayList<>(); 
        character1.add("lala");
        characters.add(character1);
        ArrayList<String> character2 = new ArrayList<>(); 
        character2.add("momo");
        characters.add(character2);
        ArrayList<String> character3 = new ArrayList<>(); 
        character3.add("mimi");
        characters.add(character3);
        ArrayList<String> character4 = new ArrayList<>(); 
        character4.add("papa");
        characters.add(character4);
        ArrayList<String> character5 = new ArrayList<>(); 
        character5.add("lalalala");
        characters.add(character5);

        Publisher pub = new Publisher("mewo"); 
        ArrayList<ComicBookComponent> pc = new ArrayList<>(); 
        for(int i = 0; i < 5; i++){
            ComicBook cb = new ComicBookComponent(pub, titles.get(i), "0", "0", "lp", authors.get(i), characters.get(i), titles.get(i));
            pc.add((ComicBookComponent) cb);
        }

        testPc.setPersonalCollection(pc);
        SearchBySeriesTitle sbd = new SearchBySeriesTitle(testPc);
        ArrayList<ComicBook> result = sbd.algorithm("kirby epic land", false);
        Object[] actual = result.toArray(); 
        Object[] expected = new Object[1];
        expected[0] = pc.get(3);
        assertArrayEquals(expected, actual);
    }




    public static void main(String [] args) throws Exception{
        
    }
}
