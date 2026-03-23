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
    private Date dueDate;
    private PriorityLevel priority;
    private TaskStatus status;

    public Task(String title, String description, Date dueDate, PriorityLevel priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.OPEN;
    }

    private List<SubTask> subtasks = new ArrayList<>();
    private List<Collaborator> collaborators = new ArrayList<>();
    private RecurringPattern recurringPattern;

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
    public String getDescription() { return description; }
}
