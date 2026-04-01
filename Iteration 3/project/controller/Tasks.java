package controller;

import java.util.*;
import model.Collaborator;
import model.RecurringPattern;
import model.SubTask;
import model.Task;
import model.enums.PriorityLevel;
import model.enums.TaskStatus;
import service.CSVService;
import service.CalendarExportGateway;
import storage.TaskList;
import java.text.SimpleDateFormat;

public class Tasks {

    private TaskList taskList = new TaskList();
    private CSVService csv = new CSVService();

    private CalendarExportGateway calendarGateway = new CalendarExportGateway();

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
        c.assignTask(t);
        t.addCollaborator(c);
        t.addSubtask(new SubTask("Work by " + c));
    }

    public Task createRecurringTask(String title, RecurringPattern rp) {
        Task t = new Task(title, "", new Date(), PriorityLevel.MEDIUM);
        t.setRecurringPattern(rp);
        taskList.add(t);
        return t;
    }

    public void importTasks(String filePath) {
        try {
            List<String[]> rows = csv.readFile(filePath);
            SimpleDateFormat csvFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (String[] row : rows) {

                String title = row[0];
                String description = row[1];

                Date dueDate = null;
                try {
                    dueDate = csvFormat.parse(row[2]);
                } catch (Exception e) {
                    System.out.println("Invalid date format");
                }

                PriorityLevel priority = PriorityLevel.valueOf(row[3]);
                TaskStatus status = TaskStatus.valueOf(row[4]);

                Task t = new Task(title, description, dueDate, priority);

                if (status == TaskStatus.COMPLETED) {
                    t.complete();
                }

                taskList.add(t);
            }

            System.out.println("Imported tasks from CSV");

        } catch (Exception e) {
        }
    }

    public void exportTasks(String filePath) {
        try {
            csv.writeFile(taskList.getAll(), filePath);
            System.out.println("Exported tasks to CSV");
        } catch (Exception e) {
        }
    }

    public void exportSingleTask(int id, String filePath) {
        Task t = taskList.find(id);
        calendarGateway.exportTasks(List.of(t), filePath);
    }

    public void exportAllTasks(String filePath) {
        calendarGateway.exportTasks(taskList.getAll(), filePath);
    }

    public void exportOpenTasks(String filePath) {
        List<Task> filtered = new ArrayList<>();

        for (Task t : taskList.getAll()) {
            if (t.getStatus() == TaskStatus.OPEN) {
                filtered.add(t);
            }
        }

        calendarGateway.exportTasks(filtered, filePath);
    }

    public List<Collaborator> getOverloadedCollaborators(List<Collaborator> collaborators) {

        List<Collaborator> overloaded = new ArrayList<>();

        for (Collaborator c : collaborators) {

            int count = 0;

            for (Task t : c.getTasks()) {
                if (t.getStatus() == TaskStatus.OPEN) {
                    count++;
                }
            }

            if (count > c.getMaxTasks()) {
                overloaded.add(c);
            }
        }

        return overloaded;
    }

    public void displayOverloadedCollaborators(List<Collaborator> collaborators) {

        List<Collaborator> overloaded = getOverloadedCollaborators(collaborators);

        for (Collaborator c : overloaded) {
            System.out.println("Overloaded: " + c.getName());
        }
    }

    public List<Task> getAllTasks() {
        return taskList.getAll();
    }
}
