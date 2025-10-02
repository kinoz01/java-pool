```java
// LinkedList.java
public interface LinkedList {
    int at(int index);
    void add(int value);
    void remove(int index);
    int size();
}

// CircularLinkedList.java
public class CircularLinkedList implements LinkedList {
    private final java.util.LinkedList<Integer> ll = new java.util.LinkedList<>();

    @Override
    public int at(int index) {
        if (index < 0 || ll.isEmpty()) return -1;
        for (int i = 0; i < index; i++) next();
        return ll.get(index % ll.size());
    }

    @Override
    public void add(int value) {
        for (int i = 1, s = ll.size(); i < s; i++) next();
        ll.addLast(value);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= ll.size()) return;
        for (int i = 0; i < index; i++) next();
        ll.remove(index % ll.size());
    }

    @Override
    public int size() {
        return ll.size();
    }

    private void next() {
        System.out.println("Go to next node");
    }
}
```