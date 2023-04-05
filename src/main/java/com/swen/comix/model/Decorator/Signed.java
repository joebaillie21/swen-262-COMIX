package com.swen.comix.model.Decorator;

import com.swen.comix.model.ComicBook;

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
