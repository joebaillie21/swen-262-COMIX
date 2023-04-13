package com.swen.comix.model;

public class Signed extends ComicDecorator {

    private static double signatureValueIncrease = 1.05;

    protected Signed(ComicBook comicBook) {
        super(comicBook);
    }
    
    @Override
    public double getValue() {
        int currentSignatures = super.getSignatures();
        super.setSignatures(currentSignatures += 1);
        return super.getValue()*super.getSignatures()*signatureValueIncrease;
    }
}