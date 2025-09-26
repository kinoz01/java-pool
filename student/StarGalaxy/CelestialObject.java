
import java.util.Objects;

public class CelestialObject {
    private double x, y, z;
    private String name = "Soleil";
    public static final double KM_IN_ONE_AU = 1.5e8;

    public CelestialObject() {
        // default constructor
    }

    public CelestialObject(String name, double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static double getDistanceBetween(CelestialObject a, CelestialObject b) {
        double dx = a.x - b.x, dy = a.y - b.y, dz = a.z - b.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double getDistanceBetweenInKm(CelestialObject a, CelestialObject b) {
        return getDistanceBetween(a, b) * KM_IN_ONE_AU;
    }

    public String toString() {
        return String.format("%s is positioned at (%.3f, %.3f, %.3f)", name, x, y, z);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CelestialObject))
            return false;
        CelestialObject c = (CelestialObject) o;
        return Double.compare(x, c.x) == 0 && Double.compare(y, c.y) == 0 &&
                Double.compare(z, c.z) == 0 && Objects.equals(name, c.name);
    }

    public int hashCode() {
        return Objects.hash(name, x, y, z);
    }
}
