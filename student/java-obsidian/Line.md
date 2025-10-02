```java
package shapes;

import java.awt.Color;

public class Line implements Drawable {
    private Point point1;
    private Point point2;
    private Color color;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.color = getColor();
    }

    public Line(Point point1, Point point2, Color color) {
        this.point1 = point1;
        this.point2 = point2;
        this.color = color;
    }

    @Override
    public void draw(Displayable displayable) {
        Image dis = (Image) displayable;
        var g2d = dis.getG2d();
        g2d.setColor(color);
        g2d.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }

    public static Line random(int width, int height) {
        Point p1 = Point.random(width, height);
        Point p2 = Point.random(width, height);
        return new Line(p1, p2);
    }
}

```