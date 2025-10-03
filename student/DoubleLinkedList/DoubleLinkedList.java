public class DoubleLinkedList implements LinkedList {
    private final java.util.LinkedList<Integer> ll = new java.util.LinkedList<>();

    @Override
    public int at(int index) {
        if (index < 0 || index >= ll.size()) return -1;
        traverse(ll.size(), index);
        return ll.get(index);
    }

    @Override
    public void add(int value) {
        ll.add(value);
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

    private void traverse(int size, int index) {
        int headDist = index, tailDist = size - 1 - index;
        if (headDist <= tailDist) for (int i=0; i<headDist; i++) System.out.println("Go to next node");
        else for (int i=0; i<tailDist; i++) System.out.println("Go to previous node"); 
    }
}
// public class DoubleLinkedList implements LinkedList {
// private Node head, tail;
// private int n;

// private static class Node {
// int value;
// Node next, prev;

// Node(int v) {
// value = v;
// }
// }

// @Override
// public int at(int index) {
// Node x = nodeAt(index);
// return x == null ? -1 : x.value;
// }

// @Override
// public void add(int value) {
// Node x = new Node(value);
// if (tail == null)
// head = tail = x;
// else {
// tail.next = x;
// x.prev = tail;
// tail = x;
// }
// n++;
// }

// @Override
// public void remove(int index) {
// Node x = nodeAt(index);
// if (x == null)
// return;
// Node a = x.prev, b = x.next;
// if (a != null)
// a.next = b;
// else
// head = b;
// if (b != null)
// b.prev = a;
// else
// tail = a;
// n--;
// }

// @Override
// public int size() {
// return n;
// }

// // pick direction by distance: headDist = idx; tailDist = (n-1-idx); tie =>
// from
// // head
// private Node nodeAt(int idx) {
// if (idx < 0 || idx >= n)
// return null;
// int headDist = idx, tailDist = (n - 1 - idx);
// if (headDist <= tailDist) { // from head (prints "next")
// Node cur = head;
// for (int i = 0; i < headDist; i++)
// cur = next(cur);
// return cur;
// } else { // from tail (prints "prev")
// Node cur = tail;
// for (int i = 0; i < tailDist; i++)
// cur = prev(cur);
// return cur;
// }
// }

// private Node next(Node node) {
// System.out.println("Go to next node");
// return node.next;
// }

// private Node prev(Node node) {
// System.out.println("Go to previous node");
// return node.prev;
// }
// }
