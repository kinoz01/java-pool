public class CelestialObject {
    public double x, y, z;
    public String name;

    public CelestialObject() { this("Soleil", 0, 0, 0); }
    public CelestialObject(String name, double x, double y, double z) {
        this.name = name; this.x = x; this.y = y; this.z = z;
    }
}