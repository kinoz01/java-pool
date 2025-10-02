```java
import java.lang.Math;

public class AreaCalculator {
    public double calculate(double radius) {
        return Math.PI * radius * radius;
    }

    public double calculate(double width, double height) {
        return width * height;    
    }

    public double calculate(double base, double height, boolean isTriangle) {
        return 0.5 * base * height;
    }

    public double calculate(double side, boolean isSquare) {
        return side * side;
    }
}



public class ExerciseRunner {
    public static void main(String[] args) {
        AreaCalculator calculator = new AreaCalculator();

        // Test calculate area of a circle
        double circleArea = calculator.calculate(5.0);
        System.out.println("Area of circle: " + circleArea);

        // Test calculate area of a rectangle
        double rectangleArea = calculator.calculate(4.0, 6.0);
        System.out.println("Area of rectangle: " + rectangleArea);

        // Test calculate area of a triangle
        double triangleArea = calculator.calculate(4.0, 6.0, true);
        System.out.println("Area of triangle: " + triangleArea);

        // Test calculate area of a square
        double squareArea = calculator.calculate(4.0, true);
        System.out.println("Area of square: " + squareArea);
    }
}
```
