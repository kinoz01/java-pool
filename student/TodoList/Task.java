public class Task {
    private String description;
    private TaskStatus status = TaskStatus.NEW;

    public Task(String d) {
        this.description = d;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}