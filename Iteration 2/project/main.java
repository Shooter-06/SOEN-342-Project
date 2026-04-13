import controller.Tasks;
import java.util.*;
import model.Task;
import model.enums.PriorityLevel;

public class main {
    public static void main(String[] args) {

        Tasks system = new Tasks();

        System.out.println("=== CREATE TASK ===");
        Task t = system.createTask("SOEN 342", "Software Engineering Class in 3th Year", new Date(), PriorityLevel.HIGH);
        System.out.println("Task created: " + t.getTitle());

        System.out.println("\n=== COMPLETE TASK ===");
        system.completeTask(0);
        System.out.println("Task completed");

        System.out.println("\n=== SEARCH TASK ===");
        List<Task> results = system.searchTasks("SOEN 342");

        for (Task task : results) {
            System.out.println("- " + task.getTitle());
        }

        System.out.println("\nTotal found: " + results.size());

        // === EXPORT TASKS TO CSV ===
        system.exportTasks("tasks.csv");
        System.out.println("Tasks exported to tasks.csv");

        // === IMPORT TASKS FROM CSV ===
        system.importTasks("tasks.csv");
        for(Task task : system.getAllTasks()) {
            System.out.println(task.getTitle());
        }
        System.out.println("Tasks imported from tasks.csv");
    }
}
