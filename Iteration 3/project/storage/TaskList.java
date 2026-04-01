package storage;

import java.util.*;
import model.Task;
import model.enums.TaskStatus;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public void add(Task t) {
        if (t.getStatus() == TaskStatus.OPEN && t.getDueDate() == null) {

            if (countOpenTasksWithoutDueDate() >= 50) {
                throw new IllegalStateException(
                        "Cannot have more than 50 open tasks without due date"
                );
            }
        }

        tasks.add(t);
    }

    public Task find(int id) {
        return tasks.get(id);
    }

    public int countOpenTasksWithoutDueDate() {
        int count = 0;
        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.OPEN && t.getDueDate() == null) {
                count++;
            }
        }

        return count;
    }

    // Optional (for controller use)
    public List<Task> getOpenTasksWithoutDueDate() {
        List<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getStatus() == TaskStatus.OPEN && t.getDueDate() == null) {
                result.add(t);
            }
        }

        return result;
    }

    public List<Task> getAll() {
        return tasks;
    }

    public List<Task> filter(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTitle().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }
}
