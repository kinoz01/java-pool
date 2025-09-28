The `size()` method is used with **collections** in Java to determine the number of elements in the collection. It is **not used with arrays or strings**.

### 1\. `size()` is used with:

-   `List` (e.g., `ArrayList`, `LinkedList`)
    
-   `Set` (e.g., `HashSet`, `TreeSet`)
    
-   `Map` (to get the number of key-value pairs)
    

**Examples:**

**List:**

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
int count = names.size(); // 2
```

**Set:**

```java
Set<Integer> numbers = new HashSet<>();
numbers.add(1);
numbers.add(2);
int count = numbers.size(); // 2
```

**Map:**

```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);
int count = map.size(); // 2
```

### Summary:

| Type | Method/Field Used | Example |
| --- | --- | --- |
| Array | `.length` | `array.length` |
| String | `.length()` | `string.length()` |
| List / Set / Map | `.size()` | `collection.size()` |