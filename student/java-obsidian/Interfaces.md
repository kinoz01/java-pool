```java
import java.awt.Color;

public interface Displayable {
    void display(int x, int y, Color color);
    void save(String filename);
}

public interface Drawable {
    void draw(Displayable displayable);

    default Color getColor() {
        int R = (int) (Math.random() * 255);
        int G = (int) (Math.random() * 255);
        int B = (int) (Math.random() * 255);
        return new Color(Math.max(0, R), Math.max(0, G), Math.max(0, B));
    }
}
```

We use `default` when we want to give the *interface contract method* a default body this body will be created by default whenever we create a new instance implementation of the interface.

Note that if we don't want to create the method body with each instance created we would use the keyword `static`.

> Here these interface aren't used to there full potential oop, since we kinda only using them as a middle man. I mean we don't use them as input arguments that much.