```java
public interface LinkedList {
    int at(int index);
    void add(int value);
    void remove(int index);
    int size();
}

public class CircularLinkedList implements LinkedList {
    private Node head, tail;
    private int size;

    private static class Node {
        int value;
        Node next;
        Node(int v) { 
	        value = v; 
	    }
    }

    @Override
    public int at(int index) {
        if (head == null || index < 0) return -1;
        Node cur = head;
        for (int i = 0; i < index; i++) cur = next(cur);
        return cur.value;
    }

    @Override
    public void add(int value) {
        Node x = new Node(value);
        if (head == null) {
            head = tail = x;
            x.next = x;
        } else {
            Node cur = head;
            for (int i = 1; i < size; i++) cur = next(cur);
            x.next = head;
            cur.next = x;
            tail = x;
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (head == null || index < 0) return;
        if (size == 1) {           
            head = tail = null;
            size = 0;
            return;
        }
        int steps = index % size;
        if (steps == 0) {
            tail.next = head.next;
            head = head.next;
            size--;
            return;
        }

        Node prev = tail;
        Node cur = head;
        for (int i = 0; i < steps; i++) {
            prev = cur;
            cur = next(cur);
        }

        prev.next = cur.next;

        if (cur == tail) tail = prev;
        if (cur == head) head = cur.next;

        size--;
    }

    @Override
    public int size() { return size; }

    private Node next(Node node) {
        System.out.println("Go to next node");
        return node.next;
    }
}
```

# Structure and invariants

-   `Node { int value; Node next; }` — each node points to the next node.
    
-   `head` — first node; `tail` — last node.
    
-   **Circular invariant:** when the list is non-empty, `tail.next == head`. That creates a loop instead of ending with `null`.
    
-   `size` — number of elements.
    

Because the list is circular, traversals can keep walking past `tail` back to `head` without hitting `null`.

# Helper: next(...)

```java
private Node next(Node node) {
    System.out.println("Go to next node");
    return node.next;
}
```

This prints a trace on every hop, then returns the next node. In a circular list, `next(tail)` returns `head`.

# Reading: at(int index)

```java
public int at(int index) {
    if (head == null || index < 0) return -1;
    Node cur = head;
    for (int i = 0; i < index; i++) cur = next(cur);
    return cur.value;
}
```

-   Rejects empty list and negative index.
    
-   Starts at `head` and advances `index` steps. Because the list is circular, large `index` values just keep looping around (no need for `% size`, though using it could reduce steps).
    
-   Prints once per step.
    
-   Returns `-1` for invalid requests (note: `-1` could also be a valid stored value—throwing an exception or using `OptionalInt` would avoid ambiguity).
    

**Time:** O(index) (or O(index % size) if you add a modulo).

# Inserting: add(int value)

```java
public void add(int value) {
    Node x = new Node(value);
    if (head == null) {
        head = tail = x;
        x.next = x;          // single node points to itself
    } else {
        Node cur = head;
        for (int i = 1; i < size; i++) cur = next(cur); // walk to tail
        x.next = head;       // new node points to head to keep it circular
        cur.next = x;        // old tail points to new node
        tail = x;            // update tail
    }
    size++;
}
```

-   **Empty case:** create a 1-node ring (`x.next = x`).
    
-   **General case:** walks from `head` to the current tail, then links:
    
    -   `x.next = head` to maintain circularity,
        
    -   `tail.next` (held in `cur`) to `x`,
        
    -   update `tail = x`.
        
-   Prints `size-1` times due to the traversal.
    

**Note:** You already store `tail`; you could append in **O(1)** without walking:

```java
x.next = head;
tail.next = x;
tail = x;
size++;
```

(keep the empty-list branch the same)

**Time:** O(n) as written; O(1) with the optimization above.

# Deleting: remove(int index)

```java
public void remove(int index) {
    if (head == null || index < 0) return;

    if (size == 1) {
        head = tail = null;
        size = 0;
        return;
    }

    int steps = index % size;
    if (steps == 0) {                // remove head
        tail.next = head.next;       // new ring skips old head
        head = head.next;            // move head forward
        size--;
        return;
    }

    Node prev = tail;
    Node cur  = head;
    for (int i = 0; i < steps; i++) {
        prev = cur;
        cur  = next(cur);            // print per hop
    }

    prev.next = cur.next;            // unlink cur

    if (cur == tail) tail = prev;    // if we removed tail, move tail back
    if (cur == head) head = cur.next;// (defensive; steps==0 already handled)

    size--;
}
```

-   Rejects empty or negative index.
    
-   **Single node:** clearing both pointers breaks the ring.
    
-   Uses `steps = index % size` so the index wraps around the circle (e.g., removing index 7 in a size-3 list removes position `1`).
    
-   **Removing head (`steps == 0`):**
    
    -   Point `tail.next` to `head.next` to keep the ring intact.
        
    -   Advance `head = head.next`.
        
-   **General case:**
    
    -   Start with `prev = tail`, `cur = head`. After `steps` hops, `cur` is the target, `prev` is the node before it (this works even when the target is `head`, but that case is already handled).
        
    -   Unlink with `prev.next = cur.next` (the singly linked-list “bypass”).
        
    -   Fix `tail` if you removed it; fix `head` if needed.
        
-   Prints exactly `steps` times.
    

**Time:** O(steps) = O(index % size).

# Example trace

Start: empty

1.  `add(10)`
    

-   Empty case: `head = tail = [10]`, `[10].next = [10]`.
    
-   Prints: none.
    
-   size = 1; ring: `[10] -> (back to itself)`
    

2.  `add(20)`
    

-   Walk to tail: from `head` (size=1), the loop runs 0 times (already at tail).
    
-   Link: `x.next = head` (20 → 10), `tail.next = x` (10 → 20), `tail = x` (tail=20).
    
-   Prints: none.
    
-   size = 2; ring: `10 -> 20 -> 10 -> ...`
    

3.  `add(30)`
    

-   Walk to tail: loop runs once; print:
    
    ```vbnet
    Go to next node
    ```
    
-   Link: `30 -> 10`, `20 -> 30`, `tail = 30`.
    
-   size = 3; ring: `10 -> 20 -> 30 -> 10 -> ...`
    

4.  `at(4)`
    

-   Steps: 4 hops from head: prints 4 times; sequence of nodes visited: 10 → 20 → 30 → 10 → 20; returns `20`.
    

5.  `remove(1)` (remove the node after head: value 20)
    

-   `steps = 1 % 3 = 1`.
    
-   Start `prev=tail(30)`, `cur=head(10)`.
    
    -   Hop once: print “Go to next node”; `prev=10`, `cur=20`.
        
-   Unlink: `prev.next = cur.next` → `10.next = 30`.
    
-   `cur == tail?` no. `cur == head?` no.
    
-   size = 2; ring: `10 -> 30 -> 10 -> ...`
    

6.  `remove(0)` (remove head: value 10)
    

-   `steps = 0`.
    
-   `tail.next = head.next` → `30.next = 30`; `head = head.next` → `head = 30`.
    
-   size = 1; ring: single node `30 -> 30`.
    

7.  `remove(0)` (remove last remaining)
    

-   `size == 1` branch: `head = tail = null`, `size = 0`.