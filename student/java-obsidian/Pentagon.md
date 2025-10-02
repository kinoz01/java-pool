```java
package shapes;

import java.awt.Color;

public class Pentagon implements Drawable {
    private Point center;
    private int radius;
    private Color color;

    public Pentagon(Point center, int radius) {
        this.center = center;
        this.radius = Math.max(1, radius);
        this.color = getColor();
    }

    @Override
    public void draw(Displayable displayable) {
        Image dis = (Image) displayable;
        var g2d = dis.getG2d();
        g2d.setColor(color);

        int sides = 5;
        int[] xs = new int[sides];
        int[] ys = new int[sides];
        for (int i = 0; i < sides; i++) {
            double angle = 2.0 * Math.PI * i / sides - Math.PI / 2.0;
            xs[i] = center.getX() + (int) Math.round(radius * Math.cos(angle));
            ys[i] = center.getY() + (int) Math.round(radius * Math.sin(angle));
        }
        g2d.drawPolygon(xs, ys, sides);
    }
}
```