public class Task {
    private String description;
    private TaskStatus status = TaskStatus.NEW;
    
    public Task(String d) {
        description = d;
    }

    public void setDescription(String s) {
        description = s;
    }

    public String getDescription() {
        return description;
    }
    
    public void setStatus(TaskStatus s) {
        status = s;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
}