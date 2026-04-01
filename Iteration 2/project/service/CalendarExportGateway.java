package service;

import model.SubTask;
import model.Task;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class CalendarExportGateway {

    private static final SimpleDateFormat formatter
            = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

    public void exportTasks(List<Task> tasks, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {

            // ICS HEADER
            writer.write("BEGIN:VCALENDAR\n");
            writer.write("VERSION:2.0\n");

            for (Task t : tasks) {

                // ignore tasks without due date
                if (t.getDueDate() == null) {
                    continue;
                }

                writer.write("BEGIN: VEVENT\n");

                writer.write("SUMMARY: " + t.getTitle() + "\n");

                // Build description (including subtasks)
                String description = buildDescription(t);
                writer.write("DESCRIPTION: " + description + "\n");

                writer.write("DTEND: " + formatter.format(t.getDueDate()) + "\n");

                writer.write("STATUS: " + t.getStatus() + "\n");

                writer.write("PRIORITY: " + t.getPriority() + "\n");

                writer.write("END:VEVENT\n");
            }

            writer.write("END:VCALENDAR\n");

            System.out.println("ICS file created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Subtask handling (IMPORTANT REQUIREMENT)
    private String buildDescription(Task t) {

        StringBuilder desc = new StringBuilder();

        desc.append(t.getDescription());

        if (!t.getSubtasks().isEmpty()) {
            desc.append("\\nSubtasks:");

            for (SubTask s : t.getSubtasks()) {
                desc.append("\\n- ").append(s.getTitle());
            }
        }

        return desc.toString();
    }
}
