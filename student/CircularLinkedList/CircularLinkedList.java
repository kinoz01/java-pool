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

// public class CircularLinkedList implements LinkedList {
// private Node head, tail;
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
// if (head == null || index < 0)
// return -1;
// Node cur = head;
// for (int i = 0; i < index; i++)
// cur = next(cur); // print exactly index times
// return cur.value;
// }

// @Override
// public void add(int value) {
// Node x = new Node(value);
// if (head == null) {
// head = tail = x;
// x.next = x;
// } else {
// Node cur = head;
// for (int i = 1; i < n; i++)
// cur = next(cur); // walk to tail: n-1 prints
// // cur is tail
// x.next = head;
// cur.next = x;
// tail = x;
// }
// n++;
// }

// @Override
// public void remove(int index) {
// if (head == null || index < 0)
// return;
// if (n == 1) {
// head = tail = null;
// n = 0;
// return;
// }
// int steps = index % n; // normalize for correctness; prints still done per
// loop
// if (steps == 0) { // remove head (no prints expected by tests)
// tail.next = head.next;
// head = head.next;
// n--;
// return;
// }
// Node prev = tail, cur = head;
// for (int i = 0; i < steps; i++) {
// prev = cur;
// cur = next(cur);
// } // print 'steps' times
// prev.next = cur.next;
// if (cur == tail)
// tail = prev;
// if (cur == head)
// head = cur.next;
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
