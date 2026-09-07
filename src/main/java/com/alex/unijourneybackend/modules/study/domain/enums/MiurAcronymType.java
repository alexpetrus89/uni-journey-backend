package com.alex.unijourneybackend.modules.study.domain.enums;


/**
 * Enum representing MIUR acronyms used for identifying courses.
 */
public enum MiurAcronymType {

    MAT("Matematica"),
    FIS("Fisica"),
    CHI("Chimica"),
    INF("Informatica"),
    ING("Ingegneria"),
    BIO("Biologia"),
    MED("Medicina"),
    LET("Lettere"),
    ECO("Economia"),
    GIU("Giurisprudenza"),
    PSI("Psicologia");

    private final String description;

    MiurAcronymType(String description) {
        this.description = description;
    }

    public String getCode() {
        return this.name(); // es. "INF"
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getCode() + " - " + description;
    }


}

