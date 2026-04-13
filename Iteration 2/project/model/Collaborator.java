package model;

import java.util.ArrayList;
import java.util.List;
import model.enums.CollaboratorCategory;

public class Collaborator {
    private final String name;
    private final CollaboratorCategory category;
    private final List<Task>  assignedTask;
    public final int taskLimit;

    public Collaborator(String name, CollaboratorCategory category) {
        this.name = name;
        this.category = category;
        this.assignedTask = new ArrayList<>();
        switch (category) {
            case JUNIOR -> this.taskLimit = 10;
            case INTERMEDIATE -> this.taskLimit = 5;
            case SENIOR -> this.taskLimit = 2;
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        }
    }

    public void assignTask(Task t) {
        assignedTask.add(t);
    }

    public String getName() {
        return name;
    }

    public CollaboratorCategory getCategory() {
        return category;
    }

    public List<Task> getAssignedTasks() {
        return assignedTask;
    }

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }

}
