```java
public class CelestialObject {
    private double x, y, z;
    private String name;

    public CelestialObject() {
        this("Soleil", 0, 0, 0);
    }

    public CelestialObject(String name, double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double n) {
        this.x = n;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        CelestialObject defaultStar = new CelestialObject();
        System.out.println(defaultStar.getX());
        System.out.println(defaultStar.getY());
        System.out.println(defaultStar.getZ());
        System.out.println(defaultStar.getName());

        defaultStar.setName("Terre");
        defaultStar.setX(0.43);
        defaultStar.setY(0.98);
        defaultStar.setZ(1.43);
        System.out.println(defaultStar.getX());
        System.out.println(defaultStar.getY());
        System.out.println(defaultStar.getZ());
        System.out.println(defaultStar.getName());
    }
}
```

You can write `return this.x;` inside your getter. Both `return x;` and `return this.x;` mean the same thing in this case.

The keyword `this` refers to the **current object** (the instance on which the method was called).

Example:

```java
public double getX() {
    return this.x;  // same as return x;
}
```

When you call:

```java
CelestialObject star = new CelestialObject();
System.out.println(star.getX());
```

`this` inside `getX()` refers to `star`, so `this.x` means “the `x` field of this `star` object.”

---

**When is `this` necessary?**  
Most of the time you don’t need `this.` in getters, because there is no ambiguity — the compiler knows that `x` refers to the instance field.

But it becomes necessary when a **method parameter** or **local variable** has the same name as the field.

To remove `this`  in the setter you can put:

```java
public void setX(double n) {
    x = n;
}
```

`x` is the instance field, and `n` is the parameter. The compiler resolves `x` to the field of the current object, so `x = n;` assigns the parameter value to that field. Writing `this.x = n;` is equivalent here and sometimes preferred for clarity, but not required.