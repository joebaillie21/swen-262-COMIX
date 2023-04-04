package com.swen.comix.model.Decorator;

import java.util.ArrayList;

import com.swen.comix.model.Author;
import com.swen.comix.model.ComicBook;
import com.swen.comix.model.Publisher;

public class ComicDecorator implements com.swen.comix.model.ComicBook{

    protected ComicBook comicBook;

    protected ComicDecorator(ComicBook comicBook){
        this.comicBook = comicBook;
    }

    @Override
    public Publisher getPublisher() {
        return comicBook.getPublisher();
    }


    @Override
    public ArrayList<Author> getAuthor() {
        return comicBook.getAuthor();
    }


    @Override
    public String getSeriesTitle() {
        return comicBook.getSeriesTitle();

    }


    @Override
    public String getPubDate() {
        return comicBook.getPubDate();
    }


    @Override
    public String getDescription() {
        return comicBook.getDescription();
    }


    @Override
    public int getVolNum() {
        return comicBook.getVolNum();
    }


    @Override
    public int getIssueNum() {
        return comicBook.getIssueNum();
    }


    @Override
    public int getGrade() {
        return comicBook.getGrade();
    }


    @Override
    public ArrayList<String> getPrincipleCharacter() {
        return comicBook.getPrincipleCharacter();
    }


    @Override
    public boolean isAuthenticated() {
        return comicBook.isAuthenticated();
    }


    @Override
    public double getValue() {
        return comicBook.getValue();
    }

    @Override
    public int getSignature() {
        return comicBook.getSignature();
    }
}
