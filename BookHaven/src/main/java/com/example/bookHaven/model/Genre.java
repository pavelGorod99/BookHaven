package com.example.bookHaven.model;

public enum Genre {
    FICTION("Fiction"),
    NOVEL("Novel"),
    MYSTERY("Mystery"),
    NARRATIVE("Narrative"),
    HISTORICAL_FICTION("Historical fiction"),
    HISTORICAL_FANTASY("Historical fantasy"),
    LITERARY_FICTION("Literary fiction"),
    ROMANCE_NOVEL("Romance novel"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    HISTORY("History"),
    FANTASY("Fantasy"),
    BIOGRAPHY("Biography"),
    AUTOBIOGRAPHY("Autobiography"),
    SCIENCE("Science"),
    HUMOR("Humor"),
    SPIRITUALITY("Spirituality"),
    ACTION("Action");

    public final String label;

    Genre(String label) {
        this.label = label;
    }
}
