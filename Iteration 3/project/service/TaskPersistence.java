import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.enums.PriorityLevel;
import model.enums.TaskStatus;

public class TaskPersistence {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tasks_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Save tasks to the database
    public void saveTask(Task task) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO tasks (title, description, due_date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load tasks from the database
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM tasks";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date dueDate = resultSet.getDate("due_date");
                TaskStatus status = TaskStatus.valueOf(resultSet.getString("status"));

                // Retrieve PriorityLevel from the database (assuming it's stored as a string in the database)
                String priorityString = resultSet.getString("priority");
                PriorityLevel priority = PriorityLevel.valueOf(priorityString);  // Map to PriorityLevel enum

                tasks.add(new Task(title, description, dueDate, priority));  // Pass priority to Task constructor
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}