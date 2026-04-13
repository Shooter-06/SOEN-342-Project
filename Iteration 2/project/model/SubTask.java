package model;

public class SubTask {
    private final String title;
    private final boolean isCompleted;

    public SubTask(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public String getTitle() { return title; }

    public boolean isCompleted() { return isCompleted; }
}
