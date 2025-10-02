```java
// LinkedList.java
public interface LinkedList {
    int at(int index);
    void add(int value);
    void remove(int index);
    int size();
}

// DoubleLinkedList.java
public class DoubleLinkedList implements LinkedList {
    private final java.util.LinkedList<Integer> ll = new java.util.LinkedList<>();

    @Override
    public int at(int index) {
        if (index < 0 || index > ll.size()) return -1;
        traverse(ll.size(), index);
        return ll.get(index);
    }

    @Override
    public void add(int value) {
        ll.addLast(value);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= ll.size()) return;
        traverse(ll.size(), index);
        ll.remove(index);
    }

    @Override
    public int size() {
        return ll.size();
    }

    private void traverse(int n, int index) {
        int headDist = index, tailDist = n - 1 - index;
        if (headDist <= tailDist) for (int i = 0; i < headDist; i++) System.out.println("Go to next node");
        if (headDist > tailDist) for (int i = 0; i < tailDist; i++) System.out.println("Go to previous node");
    }
}
```