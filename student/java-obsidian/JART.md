```java
import shapes.*;

public class Main {
    public static void main(String[] args) {
        Image image = new Image(1000, 1000);

        Point.random(image.getWidth(), image.getHeight()).draw(image);
        Line.random(image.getWidth(), image.getHeight()).draw(image);

        Rectangle rectangle = new Rectangle(new Point(50, 50), new Point(300, 200));
        rectangle.draw(image);

        Triangle triangle = new Triangle(new Point(100, 100), new Point(900, 900), new Point(100, 900));
        triangle.draw(image);

        for (int i = 0; i < 50; i++) {
            Circle circle = Circle.random(image.getWidth(), image.getHeight());
            circle.draw(image);
        }

        Pentagon pent = new Pentagon(new Point(700, 300), 80);
        pent.draw(image);

        Cube cube = new Cube(new Point(200, 400), 140);
        cube.draw(image);

        image.save("image.png");
        System.out.println("Saved image.png");
    }
}
```

`Image image = new Image(1000, 1000);` -> new [[image instance creation]].