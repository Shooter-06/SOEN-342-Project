package model;

import java.util.ArrayList;
import java.util.List;
import model.enums.CollaboratorCategory;

public class Collaborator {
    private String name;
    private CollaboratorCategory category;
    private List<Task>  assignedTask;
    private int taskLimit;

    public Collaborator(String name, CollaboratorCategory category) {
        this.name = name;
        this.category = category;
        this.assignedTask = new ArrayList<>();
        if( category == CollaboratorCategory.JUNIOR) {
            this.taskLimit = 10;
        } else if (category == CollaboratorCategory.INTERMEDIATE) {
            this.taskLimit = 5;
        } else {
            this.taskLimit = 2;
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
