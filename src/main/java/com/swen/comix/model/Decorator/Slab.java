package com.swen.comix.model.Decorator;

import com.swen.comix.model.ComicBook;

public class Slab extends ComicDecorator{

    protected Slab(ComicBook comicBook) {
        super(comicBook);
    }
    
    
    @Override
    public double getValue() {
        return super.getValue()*2;
    }
}
