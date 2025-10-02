```java
package shapes;

import java.awt.Color;

public class Rectangle implements Drawable {
    private Point p1, p2;
    private Color color;

    public Rectangle(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = getColor();
    }

    public int getWidth() {
        return Math.abs(p2.getX() - p1.getX());
    }

    public int getHeight() {
        return Math.abs(p2.getY() - p1.getY());
    }

    @Override
    public void draw(Displayable displayable) {
        Image dis = (Image) displayable;
        var g2d = dis.getG2d();
        g2d.setColor(color);
        int x = Math.min(p1.getX(), p2.getX());
        int y = Math.min(p1.getY(), p2.getY());
        g2d.drawRect(x, y, getWidth(), getHeight());
    }
}
```

> In Java AWT/Swing `Graphics2D`, `(x, y)` is **screen-style** (like Mozilla/Canvas): origin at **top-left**, `+x` → right, `+y` → **down**. Not math-like.

