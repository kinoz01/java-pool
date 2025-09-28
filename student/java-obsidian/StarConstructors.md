```java
public class CelestialObject {
    public double x, y, z;
    public String name;

    public CelestialObject() { this("Soleil", 0, 0, 0); }
    public CelestialObject(String name, double x, double y, double z) {
        this.name = name; this.x = x; this.y = y; this.z = z;
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        CelestialObject defaultStar = new CelestialObject();
        System.out.println(defaultStar.x);
        System.out.println(defaultStar.y);
        System.out.println(defaultStar.z);
        System.out.println(defaultStar.name);

        CelestialObject earth = new CelestialObject("Terre", 0.43, 0.98, 1.43);
        System.out.println(earth.x);
        System.out.println(earth.y);
        System.out.println(earth.z);
        System.out.println(earth.name);
    }
}
```

`this("Soleil", 0, 0, 0);` is a call from one [[constructor]] to another **overloaded** constructor of the same class. It does not set fields by itself; it **delegates** to the other constructor:

```java
public CelestialObject() {
    this("Soleil", 0, 0, 0);   // delegate
}

public CelestialObject(String name, double x, double y, double z) {
    this.name = name;          // assignment happens here
    this.x = x;
    this.y = y;
    this.z = z;
}
```

How it “knows” which field gets which value: by **parameter order** and the assignments in the target constructor. The call passes four arguments in this order: `(name, x, y, z)`. Inside the target constructor, you explicitly map each parameter to a field with `this.field = parameter;`. If you changed the order of parameters or assignments, you would change which value goes into which field.

`this(...)` rules you should know:

-   It must be the **first statement** in the constructor that calls it.
    
-   It avoids **duplication** of initialization logic by centralizing it in one constructor.
    
-   You can chain further (e.g., `this(...) -> this(...)`), but the chain must end in a constructor that actually sets the fields.
    

About the numeric literals: the `0` arguments are `int` literals, but Java **widens** them implicitly to `double` for the `double` parameters, so they become `0.0` automatically.

Yes, you can “declare Soleil at the beginning” using **field initializers** instead of constructor delegation:

```java
public class CelestialObject {
    public double x = 0, y = 0, z = 0;
    public String name = "Soleil";

    public CelestialObject() { }  // optional; the compiler will add a no-arg constructor if none exist

    public CelestialObject(String name, double x, double y, double z) {
        this.name = name; this.x = x; this.y = y; this.z = z;
    }
}
```

Execution/initialization order when you use field initializers and constructors:

1.  **Field initializers** run first for the new object (setting `name = "Soleil"`, `x = 0`, etc.).
    
2.  Then the **constructor body** runs and may **override** those field values with the parameters you pass.
    

So if you create:

```java
CelestialObject defaultStar = new CelestialObject();
```

you will get `x=0.0`, `y=0.0`, `z=0.0`, `name="Soleil"` from the field initializers.

If you create:

```java
CelestialObject earth = new CelestialObject("Terre", 0.43, 0.98, 1.43);
```

the constructor body will overwrite the initialized defaults, resulting in `name="Terre"`, `x=0.43`, `y=0.98`, `z=1.43`.

Comparing the two approaches:

-   Constructor delegation with `this("Soleil", 0, 0, 0);`
    
    -   Centralizes all initialization logic in a single “primary” constructor.
        
    -   Keeps defaults close to where the parameters are defined and enforces consistent setup.
        
    -   Good when defaults are logically the same kind of configuration as the non-default constructor.
        
-   Field initializers
    
    -   Make defaults visible at the declaration site.
        
    -   Still allow parameterized constructors to override them.
        
    -   Handy for simple or constant defaults.
        

Both are valid. Prefer one primary constructor and delegate with `this(...)` when initialization is non-trivial or you want to avoid duplication. Prefer field initializers for simple constants.