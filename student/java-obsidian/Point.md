```java
package shapes;

import java.awt.Color;
import java.util.Random;

public class Point implements Drawable {
    private int x, y;
    private Color color;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = getColor();
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public void draw(Displayable displayable) {
        displayable.display(x, y, this.color);
    }

    public static Point random(int width, int height) {
        Random random = new Random();
        int x = random.nextInt(Math.max(1, width));
        int y = random.nextInt(Math.max(1, height));
        return new Point(x, y);
    }
}
```

 Note here that `draw` implementation in `Point` is the only shape using the image `display` method, we can remove the display method or even the displayable interface all together if we put in the point `draw` method a `g2d` direct implementation:

```java
@Override
    public void draw(Displayable displayable) {
        Image dis = (Image) displayable; // casting
        var g2d = dis.getG2d();
        g2d.setColor(color);
        g2d.drawLine(x, y, x, y);
    }
```

in this case we might just remove the displayable interface and pass `Image` directly.