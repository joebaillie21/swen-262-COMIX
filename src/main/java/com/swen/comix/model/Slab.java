package com.swen.comix.model;

public class Slab extends ComicDecorator{

    protected Slab(ComicBook comicBook) {
        super(comicBook);
    }
    
    @Override
    public double getValue() {
        return super.getValue()*2;
    }
}
