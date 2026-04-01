package model;

public class SubTask {
    private String title;
    private String description;
    private boolean isCompleted;

    public SubTask(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
