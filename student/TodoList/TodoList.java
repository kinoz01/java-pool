enum TaskStatus {
    NEW, IN_PROGRESS, COMPLETED
}

public class TodoList {
    private final Task[] tasks;
    private final int capacity;
    private int count = 0;

    public TodoList(int capacity){ this.capacity = capacity; this.tasks = new Task[capacity]; }

    public void addTask(String description){ if (count < capacity) tasks[count++] = new Task(description); }

    public void setStatus(int index, TaskStatus status){
        if (index >= 0 && index < count) tasks[index].setStatus(status);
    }

    public void setDescription(int index, String newDescription){
        if (index >= 0 && index < count) tasks[index].setDescription(newDescription);
    }

    public void displayTasks(){
        System.out.println("Tasks:");
        for (int i = 0; i < count; i++) {
            System.out.println(String.format("%-34s| %s", tasks[i].getDescription(), tasks[i].getStatus()));
        }
    }
}
