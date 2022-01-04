package com.example.swapbook;

public class BookModel {
    String Titre, Bprop, Burl;

    BookModel() {

    }

    public BookModel(String titre, String bprop, String burl) {
        Titre = titre;
        Bprop = bprop;
        Burl = burl;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getBprop() {
        return Bprop;
    }

    public void setBprop(String bprop) {
        Bprop = bprop;
    }

    public String getBurl() {
        return Burl;
    }

    public void setBurl(String burl) {
        Burl = burl;
    }
}

