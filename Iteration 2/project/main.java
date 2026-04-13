import controller.Tasks;
import java.util.*;
import model.Task;
import model.enums.PriorityLevel;

public class Main {
    public static void main(String[] args) {

        Tasks system = new Tasks();

        System.out.println("=== CREATE TASK ===");
        Task t1 = system.createTask("Task1", "create a homepage", new Date(), PriorityLevel.HIGH);
        Task t2 = system.createTask("Task2", "fix bugs", new Date(), PriorityLevel.MEDIUM);
        Task t3 = system.createTask("Task3", "write report", new Date(), PriorityLevel.LOW);
        
        // System.out.println("Task created: " + t1.getTitle());

        System.out.println("\n=== COMPLETE TASK ===");
        system.completeTask(0);
        System.out.println("Task completed");

        System.out.println("\n=== SEARCH TASK ===");
        List<Task> results = system.searchTasks("Task1");

        for (Task task : results) {
            System.out.println("- " + task.getTitle());
        }

        System.out.println("\nTotal found: " + results.size());

        // === IMPORT TASKS FROM CSV ===
        system.importTasks("tasksImported.csv");
        for (Task task : system.getAllTasks()) {
            // System.out.println(task.getTitle() + task.getDescription());
            System.out.println(
                    "Title: " + task.getTitle() +
                            " | Description: " + task.getDescription() +
                            " | Priority: " + task.getPriority());
        }
        System.out.println("Tasks imported from tasks.csv");

        // === EXPORT TASKS TO CSV ===
        system.exportTasks("tasksExported.csv");
        System.out.println("Tasks exported to tasks.csv");
    }
}
