The distance methods (`getDistanceBetween` and `getDistanceBetweenInKm`) can be instance methods instead of static methods. That way, you call them **on a specific object**, passing the other object as a parameter. This is more object-oriented in style, because the calculation is expressed as “this object’s distance to another.”

Here’s your class rewritten with instance methods:

```java
public class CelestialObject {
    private double x, y, z;
    private String name;
    public static final double KM_IN_ONE_AU = 1.5e8;

    public CelestialObject() {
        this("Soleil", 0, 0, 0);
    }

    public CelestialObject(String name, double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Instance method: distance in AU
    public double getDistanceTo(CelestialObject other) {
        return Math.hypot(Math.hypot(this.x - other.x, this.y - other.y), this.z - other.z);
    }

    // Instance method: distance in km
    public double getDistanceToInKm(CelestialObject other) {
        return getDistanceTo(other) * KM_IN_ONE_AU;
    }
}
```

Now you would use it like this:

```java
public class ExerciseRunner {
    public static void main(String[] args) {
        CelestialObject sun = new CelestialObject("Soleil", 0, 0, 0);
        CelestialObject earth = new CelestialObject("Terre", 1, 0, 0);

        System.out.println(sun.getDistanceTo(earth));        // 1.0 (AU)
        System.out.println(sun.getDistanceToInKm(earth));   // 150000000.0 (km)
    }
}
```

Here, instead of writing `CelestialObject.getDistanceBetween(sun, earth)`, you write `sun.getDistanceTo(earth)`.

## instance vs static methods

When you use static methods like `CelestialObject.getDistanceBetween(a, b)`, the logic is written as a utility function. It doesn’t depend on one particular object’s state, but instead takes all required data as parameters. This can make sense if the operation is very general, doesn’t conceptually “belong” to one object, or if you want to keep it in a utility/helper class.

When you use instance methods like `a.getDistanceTo(b)`, the method is called on one object and takes the other object as input. The code then naturally reads like: “the distance from this object to that one.” This style is more object-oriented, because the behavior is attached to the object itself, and it feels closer to how you think in terms of objects interacting.

So in your example, both are valid. With static, you write:

```java
CelestialObject.getDistanceBetween(sun, earth);
```

With instance, you write:

```java
sun.getDistanceTo(earth);
```

Static methods can be useful if:

-   The method doesn’t really conceptually belong to a single object (it uses two or more objects equally).
    
-   You want to keep all related “utility” functions together.
    
-   You want to call them without creating an object.
    

Instance methods are more natural if:

-   The operation clearly expresses something about one object in relation to another.
    
-   You want to use polymorphism later (e.g., if different subclasses compute distance differently).
    
-   You want the code to read fluently and be easier to understand.
    

In practice, distance calculations between two points are often written as instance methods (`pointA.distanceTo(pointB)`), but utility-style static methods are also common in math-heavy libraries.