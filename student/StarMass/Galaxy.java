import java.util.*;

public class Galaxy {
    private List<CelestialObject> celestialObjects;

    public Galaxy() {
        this.celestialObjects = new ArrayList<>();
    }

    public List<CelestialObject> getCelestialObjects() {
        return celestialObjects;
    }

    public void addCelestialObject(CelestialObject o) {
        celestialObjects.add(o);
    }

    public Map<String, Integer> computeMassRepartition() {
        Map<String, Integer> m = new HashMap<>();
        m.put("Star", 0);
        m.put("Planet", 0);
        m.put("Other", 0);
        for (CelestialObject o : celestialObjects) {
            String k = (o instanceof Star) ? "Star" : (o instanceof Planet) ? "Planet" : "Other";
            m.put(k, m.get(k) + o.getMass());
        }
        return m;
    }
}