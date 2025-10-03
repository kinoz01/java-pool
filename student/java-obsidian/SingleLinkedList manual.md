```java
public class SingleLinkedList implements LinkedList {
    private Node head;
    private int size;

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public int at(int index) {
        if (index < 0 || index >= size) return -1;
        Node cur = head;
        for (int i=0; i < index; i++) cur = next(cur);
        return cur.value;
    }

    @Override
    public void add(int value) {
        Node x = new Node(value);
        if (head == null) head = x;
        else {
            Node cur = head;
            for (int i = 1; i < size; i++) cur = next(cur);
            cur.next = x;
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        Node prev = null, cur = head;
        for (int i = 0; i < index; i++) {
            prev = cur;
            cur = next(cur);
        }
        prev.next = cur.next;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    private Node next(Node node) {
        System.out.println("Go to next node");
        return node.next;
    }
}
```

## Explanation

What the structure is

-   The list is a chain of `Node` objects. Each node stores an `int value` and a reference to the next node, called `next`. If `next` is `null`, that node is the tail.
    
-   `head` points to the first node (or `null` when the list is empty).
    
-   `size` counts how many nodes are in the list.
    

> Where `.next` comes from and how it’s used?

-   In your `Node` class you declared `Node next;`. That field is the link to the next node.
    
-   When you create a node with `new Node(value)`, its `next` is initially `null`.
    
-   You build the chain by assigning someone’s `next` to point at a node:
    
    -   Appending: `tail.next = newNode;`
        
    -   Removing: `prev.next = cur.next;` (skip `cur`)
        

> Why `next(node)` returns the next element?

```java
private Node next(Node node) {
    System.out.println("Go to next node");
    return node.next;
}
```

This helper simply returns the `next` field of the node you pass in. 
The print is educational: you see one line each time you advance a step in the list.

> How `add(int value)` works and why?

```java
public void add(int value) {
    Node x = new Node(value);   // new element, x.next == null
    if (head == null) {
        head = x;               // first element
    } else {
        Node cur = head;
        for (int i = 1; i < size; i++)
            cur = next(cur);    // walk to current tail, printing per hop
        cur.next = x;           // link tail -> new node
    }
    size++;
}
```

-   If the list is empty, the new node becomes `head`.
    
-   Otherwise you must find the current tail. In a singly linked list without a `tail` pointer, you walk from `head` until you’ve taken `size-1` steps. Each `next(cur)` hop prints “Go to next node”.
    
-   Set the tail’s `next` to the new node, which appends it.
    
-   Complexity is O(n) because you traverse to the tail. If you kept a `tail` field, appending would be O(1).

> How `remove(int index)` works and why?

```java
public void remove(int index) {
    if (index < 0 || index >= size) return;  // invalid: do nothing
    if (index == 0) {                        // remove head
        head = head.next;                    // move head forward
        size--;
        return;
    }
    Node prev = null, cur = head;
    for (int i = 0; i < index; i++) {
        prev = cur;                          // trail one behind
        cur = next(cur);                     // hop forward, prints per hop
    }
    prev.next = cur.next;                    // bypass cur
    size--;
}
```

-   Singly linked lists only have a forward pointer. To remove `cur`, you must update the pointer that leads into it: `prev.next`.
-   The loop moves `cur` to the node at `index` and `prev` to the node at `index-1`. There are exactly `index` hops, so you’ll see `index` prints.
-   The key [[unlinking line]] is:

```java
prev.next = cur.next;
```

Before: `prev -> cur -> after`. After: `prev -> after`. No one points to `cur` anymore, so it’s eligible for garbage collection.
    
-   Removing the head is a special case because there is no `prev`; you reassign `head = head.next`.
    
-   Complexity is O(index) (O(n) worst case).

> Behavior of `at(int index)`

-   Checks bounds and returns `-1` if invalid. This is a design choice; it collides with real data. Alternatives: throw `IndexOutOfBoundsException` or return `OptionalInt`.
    
-   Otherwise walks `index` steps with `next(cur)` and returns `cur.value`. Prints exactly `index` times.

#### What the prints show

-   `add` on non-empty list prints `size-1` times (walk to tail).
    
-   `remove(k)` prints `k` times, except removing head prints nothing.
    
-   `at(k)` prints `k` times.
    
-   These prints are for tracing; production code usually removes them to avoid I/O overhead.

#### Concrete example with step-by-step prints

Start: empty list  
`head = null`, `size = 0`

Add 10

-   Empty, so `head = [10]->null`.
    
-   Prints: none.
    
-   State: `[10] -> null`, size = 1.
    

Add 20

-   Non-empty; loop `for (i=1; i<size=1)` runs 0 times. Already at tail.
    
-   Link: `[10].next = [20]`.
    
-   Prints: none.
    
-   State: `[10] -> [20] -> null`, size = 2.
    

Add 30

-   Non-empty; loop runs once (`i=1`): `cur = next(cur)` prints:
    
    ```vbnet
    Go to next node
    ```
    
-   Now `cur` is `[20]` (tail). Link: `[20].next = [30]`.
    
-   Prints: one line.
    
-   State: `[10] -> [20] -> [30] -> null`, size = 3.
    

Remove index 1 (remove value 20)

-   Bounds OK, not head.
    
-   Traverse:
    
    -   `i=0`: `prev = [10]`, `cur = next([10])` prints:
        
        ```vbnet
        Go to next node
        ```
        
        Now `cur = [20]`.
        
-   Unlink: `prev.next = cur.next` → `[10].next = [30]`.
    
-   size = 2.
    
-   State: `[10] -> [30] -> null`.
    
-   Prints: one line.
    

Remove index 0 (remove head 10)

-   `head = head.next` → `head = [30]`.
    
-   size = 1.
    
-   State: `[30] -> null`.
    
-   Prints: none.

## Note about adding `static` to  `class node`

-   Making `Node` **static** does **not** change when `Node` objects are created. You still create one `Node` each time you call `add(...)`. Creating a new `SingleLinkedList()` never creates nodes by itself.
    
-   What `static` actually changes is the relationship between each `Node` and the outer `SingleLinkedList`:
    
    -   **Non-static inner `Node`**
        
        -   Each node carries a hidden reference to the specific `SingleLinkedList` that created it (a synthetic `this$0` field).
            
        -   Nodes in list A each hold a reference to A; nodes in list B each hold a reference to B.
            
        -   Costs one extra reference per node and can accidentally keep the list alive if a node leaks.
            
    -   **Static nested `Node`**
        
        -   Nodes don’t hold any back-reference to a `SingleLinkedList`.
            
        -   You still create one `Node` per `add(...)`.
            
        -   Slightly smaller memory per node (no outer reference) and no accidental retention of the outer list.
            
-   Practical rule: make `Node` `static` when it doesn’t need to read fields of `SingleLinkedList`. That’s true in your code, so marking it `private static class Node` is a clean improvement. (Your `next(Node)` helper could also be `static`; it doesn’t use instance fields.)
