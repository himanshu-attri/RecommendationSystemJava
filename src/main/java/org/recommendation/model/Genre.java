package org.recommendation.model;

import java.util.HashMap;

public enum Genre {
    UNKNOWN("unknown"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    CHILDREN("Children's"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    FILMNOIR("Film-Noir"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIFI("Sci-Fi"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    public final String label;
    private static final HashMap<String, Genre> LABEL_MAPING = new HashMap<>();

    private Genre(String label) {
        this.label = label;
    }
    static {
        for (Genre g: values()) {
            LABEL_MAPING.put(g.label, g);
        }
    }
    public static Genre valueOfLabel(String label) {
        return LABEL_MAPING.get(label);
    }
}
