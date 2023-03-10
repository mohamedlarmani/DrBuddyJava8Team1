package com.develhope.drbuddy.enums;

public enum RecordStatus {

    A ("This reservation is active"),
    D ("This reservation is deleted");

    private String descrizione;

    RecordStatus(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

}

