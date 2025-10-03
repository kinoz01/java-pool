You’re deleting a node from a **singly linked list**. Each node has only one outgoing reference: `next`. To remove a node `cur`, you must make the previous node `prev` skip over `cur` and point directly to `cur.next`. That’s exactly what:

```java
prev.next = cur.next;
```

does.

Step-by-step logic

1.  Before removal (deleting `cur`):
    

```rust
prev -> cur -> after
```

Here:

-   `prev` is the node at index `k-1`
    
-   `cur` is the node at index `k`
    
-   `after` is `cur.next` (the node at index `k+1`, or null if `cur` is the tail)
    

2.  Execute the link change:
    

```java
prev.next = cur.next; // i.e., prev.next = after
```

3.  After removal:
    

```pgsql
prev -----> after
        X  cur   (no one points to it anymore)
```

-   The chain is now continuous without `cur`.
    
-   Because nothing references `cur` anymore (and you don’t store it elsewhere), it becomes **eligible for garbage collection**.
    

Why this unlinks the node

-   A singly linked list is just nodes connected by `next` references. Removing a node means **changing exactly one reference**: the `next` of the node before it.
    
-   You can’t “fix” `cur` to remove itself; you must update the pointer that leads into it. That’s why you needed to track `prev` while traversing.
    

Why the order matters

-   You must read `cur.next` **before** overwriting anything, or you’d lose the reference to the rest of the list.
    
-   The usual pattern keeps both `prev` and `cur` during traversal:
    
    ```java
    prev = cur;
    cur = cur.next;
    ```
    
    so at the end of the loop you have:
    
    -   `prev` at `k-1`
        
    -   `cur` at `k`  
        and you still know `cur.next`.
        

Edge cases

-   Removing head (`index == 0`): there is no `prev`, so you do:
    
    ```java
    head = head.next;
    ```
    
    Same idea: move the “incoming” reference (here, `head`) to skip the first node.
    
-   Removing tail (`cur.next == null`): `prev.next = null` works—`prev` becomes the last node.
    
-   Out-of-bounds: you return early to avoid touching references.
    

Tiny concrete example  
List: `[10] -> [20] -> [30] -> null`, remove index 1 (`20`)

Traversal results in:

-   `prev` = node(10)
    
-   `cur` = node(20)
    
-   `cur.next` = node(30)
    

Link change:

```java
prev.next = cur.next; // node(10).next = node(30)
```

Result: `[10] -> [30] -> null`. Node(20) is unreferenced and can be collected.

Complexity

-   Finding `prev` and `cur` takes O(k) hops.
    
-   The unlink itself (`prev.next = cur.next`) is O(1).
    

Common mistakes to avoid

-   Updating `cur = cur.next` first and then losing track of the node to unlink.
    
-   Forgetting to decrement `size`.
    
-   Not handling the head case separately (no `prev` exists).
    

That’s the “how” and the “why”: you change the one incoming pointer to the node so the list bypasses it, leaving it unreachable.