package model.Decorator;

import model.ComicBook;

public class Signed extends ComicDecorator {

    private double signatureValueIncrease = 1.05;

    protected Signed(ComicBook comicBook) {
        super(comicBook);
    }
    
    @Override
    public double getValue() {
        return super.getValue()*super.getSignatures()*signatureValueIncrease;
    }
}
