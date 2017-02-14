package com.coesfr.comoescolherfrutas;

public final class FruitEntry {

    private final String _titleUpCase;
    private final int _image;
    private final int _tip;

    public FruitEntry(final String titleUpCase, final int image, final int tip) {
        this._titleUpCase = titleUpCase;
        this._image = image;
        this._tip = tip;
    }

    public String getTitleUpCase() {
        return _titleUpCase;
    }

    public int getImage() {
        return _image;
    }

    public int getTip() {
        return _tip;
    }
}