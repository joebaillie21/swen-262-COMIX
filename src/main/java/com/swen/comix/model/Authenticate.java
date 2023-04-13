package com.swen.comix.model;

public class Authenticate extends ComicDecorator{
    private static double authenticateValueIncrease = 1.20;

    protected Authenticate(ComicBook comicBook) {
        super(comicBook);
    }
    
    @Override
    public double getValue() {
        return super.getValue()*authenticateValueIncrease;
    }
}
