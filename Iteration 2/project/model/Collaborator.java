package model;

import model.enums.CollaboratorCategory;

public class Collaborator {
    private String name;
    private CollaboratorCategory category;

    public Collaborator(String name, CollaboratorCategory category) {
        this.name = name;
        this.category = category;
    }
}
