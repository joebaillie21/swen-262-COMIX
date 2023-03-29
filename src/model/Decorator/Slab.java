package model.Decorator;

import model.ComicBook;

public class Slab extends ComicDecorator{

    protected Slab(ComicBook comicBook) {
        super(comicBook);
    }
    
    
    @Override
    public double getValue() {
        return super.getValue()*2;
    }
}
