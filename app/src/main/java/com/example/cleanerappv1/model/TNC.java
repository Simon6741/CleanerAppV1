package com.example.cleanerappv1.model;

public class TNC {

    private String Term, id;

    public TNC(String term, String id) {
        Term = term;
        this.id = id;
    }

    public TNC() {
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
