package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.enums.PriorityLevel;
import model.enums.TaskStatus;

public class Task {
    private String title;
    private String description;
    private final Date dueDate;
    private final PriorityLevel priority;
    public TaskStatus status;

    public Task(String title, String description, Date dueDate, PriorityLevel priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.OPEN;
    }

    public final List<SubTask> subtasks = new ArrayList<>();
    public final List<Collaborator> collaborators = new ArrayList<>();
    public RecurringPattern recurringPattern;

    public void update(Map<String, Object> fields) {
        if (fields.containsKey("title")) this.title = (String) fields.get("title");
        if (fields.containsKey("description")) this.description = (String) fields.get("description");
    }

    public void complete() {
        this.status = TaskStatus.COMPLETED;
    }

    public void cancel() {
        this.status = TaskStatus.CANCELLED;
    }

    public void addSubtask(SubTask s) {
        subtasks.add(s);
    }

    public void addCollaborator(Collaborator c) {
        collaborators.add(c);
    }

    public void setRecurringPattern(RecurringPattern rp) {
        this.recurringPattern = rp;
    }

    public String getTitle() { return title; }

    public Date getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }
    
    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public RecurringPattern getRecurringPattern() {
        return recurringPattern;
    }

    public Task getAllTasks() {
        Task copy = new Task(this.title, this.description, this.dueDate, this.priority);
        copy.status = this.status;
        copy.subtasks.addAll(this.subtasks);
        copy.collaborators.addAll(this.collaborators);
        copy.recurringPattern = this.recurringPattern;
        return copy;
    }
}
