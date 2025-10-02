```java
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cube implements Drawable {
    private final Point center;
    private final int size;
    private final Color color;

    public Cube(Point center, int size) {
        this.center = center;
        this.size = Math.max(1, size);
        this.color = getColor();
    }

    @Override
    public void draw(Displayable displayable) {
        Image dis = (Image) displayable;
        Graphics2D g2d = dis.getG2d();

        Color old = g2d.getColor();
        g2d.setColor(color);

        int half = size / 2;
        int offset = Math.max(4, size / 3);

        int x = center.getX() - half;
        int y = center.getY() - half; // front top-left

        int bx = x + offset;
        int by = y - offset; // back top-left

        g2d.drawRect(x, y, size, size);   // front face
        g2d.drawRect(bx, by, size, size); // back face

        int[] dx = {0, size, size, 0}, dy = {0, 0, size, size};
        for (int i = 0; i < 4; i++) {
            g2d.drawLine(
                x + dx[i], y + dy[i],
                bx + dx[i], by + dy[i]
            );
        }
        g2d.setColor(old);
    }
}
```