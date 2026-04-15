package model;

import java.util.ArrayList;
import java.util.List;
import model.enums.CollaboratorCategory;
import model.enums.TaskStatus;

public class Collaborator {
    private final String name;
    private final CollaboratorCategory category;
    private final List<Task>  assignedTask;
    public final int taskLimit;
    private int maxTasks;
    private List<Task> tasks = new ArrayList<>();
    
    public Collaborator(String name, CollaboratorCategory category, int maxTasks) {
        this.name = name;
        this.category = category;
        this.maxTasks = maxTasks;
        this.assignedTask = new ArrayList<>();
        switch (category) {
            case JUNIOR -> this.taskLimit = 10;
            case INTERMEDIATE -> this.taskLimit = 5;
            case SENIOR -> this.taskLimit = 2;
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        }
        if (maxTasks <= 0) {
            throw new IllegalArgumentException("maxTasks must be positive");
        }
    }

    public void assignTask(Task t) {
        if (countOpenTasks() >= maxTasks) {
            throw new IllegalStateException("Collaborator overloaded");
        }
        tasks.add(t);
    }

    public void setMaxTasks(int maxTasks) {
        if (maxTasks <= 0) {
            throw new IllegalArgumentException("maxTasks must be positive");
        }
        this.maxTasks = maxTasks;
    }

    private int countOpenTasks() {
        int count = 0;
        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.OPEN) {
                count++;
            }
        }
        return count;
    }

    public int getMaxTasks() {
        return maxTasks;
    }

    public String getName() {
        return name;
    }

    public CollaboratorCategory getCategory() {
        return category;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
