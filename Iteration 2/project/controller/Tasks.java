package controller;

import java.util.*;
import model.Collaborator;
import model.RecurringPattern;
import model.SubTask;
import model.Task;
import model.enums.PriorityLevel;
import service.CSVService;
import storage.TaskList;

public class Tasks {

    private TaskList taskList = new TaskList();
    private CSVService csv = new CSVService();

    public Task createTask(String title, String desc, Date dueDate, PriorityLevel priority) {
        Task t = new Task(title, desc, dueDate, priority);
        taskList.add(t);
        return t;
    }

    public void updateTask(int id, Map<String, Object> fields) {
        taskList.find(id).update(fields);
    }

    public void completeTask(int id) {
        taskList.find(id).complete();
    }

    public void cancelTask(int id) {
        taskList.find(id).cancel();
    }

    public List<Task> searchTasks(String keyword) {
        return taskList.filter(keyword);
    }

    public void assignCollaborator(int taskId, Collaborator c) {
        Task t = taskList.find(taskId);
        t.addCollaborator(c);
        t.addSubtask(new SubTask("Work by " + c));
    }

    public Task createRecurringTask(String title, RecurringPattern rp) {
        Task t = new Task(title, "", new Date(), PriorityLevel.MEDIUM);
        t.setRecurringPattern(rp);
        taskList.add(t);
        return t;
    }
}