package com.coesfr.comoescolherfrutase;

public final class Fruta {

    private final String nome;
    private final int imagem;
    private final int dica;

    public Fruta(final String nome, final int imagem, final int dica) {
        this.nome = nome;
        this.imagem = imagem;
        this.dica = dica;
    }

    public String getTitleUpCase() {
        return nome;
    }

    public int getImage() {
        return imagem;
    }

    public int getTip() {
        return dica;
    }
}