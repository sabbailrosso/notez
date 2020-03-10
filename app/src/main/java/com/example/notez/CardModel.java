package com.example.notez;

public class CardModel {
    private String autore,titolo, testo, data;

    public CardModel(String autore,String titolo, String testo, String data) {
        this.titolo = titolo;
        this.testo = testo;
        this.autore = autore;
        this.data = data;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
