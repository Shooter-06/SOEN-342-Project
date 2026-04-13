package storage;

import java.util.*;
import model.Task;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void add(Task t) {
        tasks.add(t);
    }

    public Task find(int index) {
        return tasks.get(index);
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