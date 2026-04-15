import controller.Tasks;
import java.util.*;
import model.Task;
import model.enums.PriorityLevel;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Tasks system = new Tasks();
        boolean running = true;

        while (running) {

            printMenu();

            int choice = getIntInput("Choose an option: ");

            switch (choice) {

                case 1:
                    createTask(system);
                    break;

                case 2:
                    searchTasks(system);
                    break;

                case 3:
                    completeTask(system);
                    break;

                case 4:
                    exportAllTasks(system);
                    break;

                case 5:
                    exportOpenTasks(system);
                    break;

                case 6:
                    importTasks(system);
                    break;

                case 7:
                    overloadedCollaborator(system);
                    break;
                case 8:
                    // Show all tasks (loads them from persistent storage)
                    showAllTasks(system);
                    break;
                
                case 9:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // ================= MENU =================
    private static void printMenu() {
        System.out.println("\n===== TASK MANAGEMENT SYSTEM =====");
        System.out.println("1. Create Task");
        System.out.println("2. Search Tasks");
        System.out.println("3. Complete Task");
        System.out.println("4. Export ALL Tasks to ICS");
        System.out.println("5. Export OPEN Tasks to ICS");
        System.out.println("6. Import Tasks (CSV)");
        System.out.println("7.  Overloaded Collaborator");
        System.out.println("8. Exit");
    }

    // ================= FEATURES =================
    private static void createTask(Tasks system) {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        PriorityLevel priority = choosePriority();

        Task t = system.createTask(title, desc, new Date(), priority);

        System.out.println("Task created: " + t.getTitle());
    }

    private static void searchTasks(Tasks system) {
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<Task> results = system.searchTasks(keyword);

        System.out.println("Results:");
        for (Task t : results) {
            System.out.println(t.getId() + " - " + t.getTitle());
        }
    }

    private static void completeTask(Tasks system) {
        int id = getIntInput("Enter task ID: ");

        try {
            system.completeTask(id);
            System.out.println("Task completed");
        } catch (Exception e) {
            System.out.println("Task not found");
        }
    }

    private static void exportAllTasks(Tasks system) {
        system.exportAllTasks("tasks.ics");
        System.out.println("Exported all tasks to tasks.ics");
    }

    private static void exportOpenTasks(Tasks system) {
        system.exportOpenTasks("open_tasks.ics");
        System.out.println("Exported open tasks to open_tasks.ics");
    }

    private static void importTasks(Tasks system) {
        system.importTasks("tasks.csv");
        System.out.println("Tasks imported from CSV");
    }

    private static void showAllTasks(Tasks system) {
        List<Task> tasks = system.getAllTasks();
        for (Task t : tasks) {
            System.out.println(t.getId() + " - " + t.getTitle());
        }
    }

    private static void overloadedCollaborator(Tasks system) {
        System.out.println("");
    }

    // ================= HELPERS =================
    private static PriorityLevel choosePriority() {
        System.out.println("Choose priority:");
        System.out.println("1. LOW");
        System.out.println("2. MEDIUM");
        System.out.println("3. HIGH");

        int choice = getIntInput("Choice: ");

        switch (choice) {
            case 1:
                return PriorityLevel.LOW;
            case 2:
                return PriorityLevel.MEDIUM;
            case 3:
                return PriorityLevel.HIGH;
            default:
                return PriorityLevel.MEDIUM;
        }
    }

    private static int getIntInput(String message) {
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Enter a number.");
            scanner.next();
        }

        int value = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return value;
    }
}