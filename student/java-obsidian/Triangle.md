```java
package shapes;

import java.awt.Color;

public class Triangle implements Drawable {
    private Point p1, p2, p3 ;
    private Color color;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = getColor();
    }

    @Override
    public void draw(Displayable displayable) {
        new Line(p1, p3, this.color).draw(displayable);
        new Line(p1, p2, this.color).draw(displayable);
        new Line(p2, p3, this.color).draw(displayable);
    }
}
```