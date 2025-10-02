```java
package shapes;

import java.awt.Color;

public class Circle implements Drawable {
    private Point center;
    private int radius;
    private Color color;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = Math.max(1, radius);
        this.color = getColor();
    }

    @Override
    public void draw(Displayable displayable) {
        Image dis = (Image) displayable;
        var g2d = dis.getG2d();
        int x = center.getX() - radius;
        int y = center.getY() - radius;
        g2d.setColor(color);
        g2d.drawOval(x, y, 2 * radius, 2 * radius);
    }

    public static Circle random(int width, int height) {
        Point circle = Point.random(width, height);
        int max = Math.min(Math.max(1, width), Math.max(1, height));
        int radius = 5 + (int) (Math.random() * Math.max(1, max / 8));
        return new Circle(circle, radius);
    }
}
```