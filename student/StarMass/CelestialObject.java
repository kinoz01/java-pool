import java.util.Objects;

public class CelestialObject {
    public static final double KM_IN_ONE_AU = 1.5e8; // 150,000,000 km

    private double x, y, z;
    private String name;
    private int mass;

    public CelestialObject() {
        this("Soleil", 0, 0, 0, 0);
    }

    public CelestialObject(String name, double x, double y, double z, int mass) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.mass = mass;
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

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public static double getDistanceBetween(CelestialObject a, CelestialObject b) {
        double dx = a.x - b.x, dy = a.y - b.y, dz = a.z - b.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double getDistanceBetweenInKm(CelestialObject a, CelestialObject b) {
        return getDistanceBetween(a, b) * KM_IN_ONE_AU;
    }

    @Override
    public String toString() {
        return String.format("%s is positioned at (%.3f, %.3f, %.3f)", name, x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CelestialObject))
            return false;
        CelestialObject c = (CelestialObject) o;
        return Double.compare(x, c.x) == 0 && Double.compare(y, c.y) == 0 &&
                Double.compare(z, c.z) == 0 && mass == c.mass && Objects.equals(name, c.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y, z, mass);
    }
}