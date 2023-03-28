package com.develhope.drbuddy.enums;

public enum RecordStatus {

    A ("This record is active"),
    I("This record is inactive"),
    D ("This record is deleted");

    private String descrizione;

    RecordStatus(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

}

