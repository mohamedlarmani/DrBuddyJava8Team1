package com.develhope.drbuddy.enums;

public enum RecordStatus {

    A ("This record is active"),
    D ("This record is deleted");

    private String descrizione;

    RecordStatus(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

}

