package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.enums.PriorityLevel;
import model.enums.TaskStatus;

public class TaskPersistence {

    private static final String DB_URL = System.getenv().getOrDefault(
            "TASK_DB_URL",
            "jdbc:mysql://localhost:3306/tasks_db"
    );
    private static final String DB_USER = System.getenv().getOrDefault("TASK_DB_USER", "root");
    private static final String DB_PASSWORD = System.getenv().getOrDefault("TASK_DB_PASSWORD", "password");

    private Connection openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver not found. Add mysql-connector-j to your classpath.",
                    e
            );
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Save a task to the database
    public void saveTask(Task task) {
        try (Connection connection = openConnection()) {
            String sql = "INSERT INTO tasks (title, description, due_date, status, priority) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setDate(3, task.getDueDate() == null ? null : new java.sql.Date(task.getDueDate().getTime()));
            statement.setString(4, task.getStatus().name());
            statement.setString(5, task.getPriority().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load tasks from the database
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = openConnection()) {
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
                PriorityLevel priority = PriorityLevel.valueOf(priorityString);
                Task task = new Task(title, description, dueDate, priority);
                if (status == TaskStatus.COMPLETED) {
                    task.complete();
                } else if (status == TaskStatus.CANCELLED) {
                    task.cancel();
                }
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Error loading tasks from DB");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }
        return tasks;
    }
}
