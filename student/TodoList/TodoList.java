enum TaskStatus {
    NEW, IN_PROGRESS, COMPLETED
}

public class TodoList {
    private Task[] tasks;
    private int capacity;
    private int c = 0;

    public TodoList(int capacity) {
        this.capacity = capacity;
        tasks = new Task[capacity];
    }

    public void addTask(String description) {
        if (c < capacity) tasks[c++] = new Task(description);
    }
    
    public void setStatus(int index, TaskStatus status) {
        if (index >= 0 && index < c) tasks[index].setStatus(status);
    }
    
    public void setDescription(int index, String newDescription) {
        if (index >= 0 && index < c) tasks[index].setDescription(newDescription);
    }

    public void displayTasks() {
        System.out.println("Tasks:");
        for (int i=0; i<c; i++) System.out.println(String.format("%-34s| %s", tasks[i].getDescription() , tasks[i].getStatus()));
    }
}