package model;

import java.util.ArrayList;
import java.util.List;
import model.enums.CollaboratorCategory;
import model.enums.TaskStatus;

public class Collaborator {

    private String name;
    private CollaboratorCategory category;
    private int maxTasks;

    private List<Task> tasks = new ArrayList<>();

    public Collaborator(String name, CollaboratorCategory category, int maxTasks) {
        if (maxTasks <= 0) {
            throw new IllegalArgumentException("maxTasks must be positive");
        }
        this.name = name;
        this.category = category;
        this.maxTasks = maxTasks;
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
