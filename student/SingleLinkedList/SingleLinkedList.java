public class SingleLinkedList implements LinkedList {
    private final java.util.LinkedList<Integer> ll = new java.util.LinkedList<>();

    @Override
    public int at(int index) {
        if (index < 0 || index >= ll.size())
            return -1;
        for (int i = 0; i < index; i++)
            next();
        return ll.get(index);
    }

    @Override
    public void add(int value) {
        for (int i = 1; i < ll.size(); i++)
            next();
        ll.add(value);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= ll.size())
            return;
        for (int i = 0; i < index; i++)
            next();
        ll.remove(index);
    }

    @Override
    public int size() {
        return ll.size();
    }

    private void next() {
        System.out.println("Go to next node");
    }
}

// public class SingleLinkedList implements LinkedList {
// private Node head;
// private int n;

// private static class Node {
// int value;
// Node next;

// Node(int v) {
// value = v;
// }
// }

// @Override
// public int at(int index) {
// if (index < 0 || index >= n)
// return -1;
// Node cur = head;
// for (int i = 0; i < index; i++)
// cur = next(cur); // prints per step
// return cur.value;
// }

// @Override
// public void add(int value) {
// Node x = new Node(value);
// if (head == null)
// head = x;
// else {
// Node cur = head;
// for (int i = 1; i < n; i++)
// cur = next(cur); // walk to tail: n-1 prints
// cur.next = x;
// }
// n++;
// }

// @Override
// public void remove(int index) {
// if (index < 0 || index >= n)
// return;
// if (index == 0) {
// head = head.next;
// n--;
// return;
// } // no prints for head removal
// Node prev = null, cur = head;
// for (int i = 0; i < index; i++) {
// prev = cur;
// cur = next(cur);
// } // print 'index' times
// prev.next = cur.next;
// n--;
// }

// @Override
// public int size() {
// return n;
// }

// private Node next(Node node) {
// System.out.println("Go to next node");
// return node.next;
// }
// }
