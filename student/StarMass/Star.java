import java.util.Objects;

public class Star extends CelestialObject {
    private double magnitude;

    public Star() {
        super();
        this.magnitude = 0;
    }

    public Star(String name, double x, double y, double z, double magnitude, int mass) {
        super(name, x, y, z, mass);
        this.magnitude = magnitude;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double m) {
        this.magnitude = m;
    }

    @Override
    public String toString() {
        return String.format("%s shines at the %.3f magnitude", getName(), magnitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Star))
            return false;
        Star s = (Star) o;
        return super.equals(s) && Double.compare(magnitude, s.magnitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), magnitude);
    }
}