package org.viktar.sci;

public class Cluster {
    private final double mass;
    private final int p;
    private final int n;
    private final String name;

    public double mass() {
        return mass;
    }

    public int protons() {
        return p;
    }

    public int neutrons() {
        return n;
    }

    Cluster(double mass, int p, int n, String name) {
        if (mass <= 0) {
            throw new IllegalArgumentException("Mass must be positive");
        }

        if (p <= 0) {
            throw new IllegalArgumentException("Protons count must be positive");
        }

        if (n < 0) {
            throw new IllegalArgumentException("Neutrons count must be non-negative");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must be non-empty");
        }

        this.mass = mass;
        this.p = p;
        this.n = n;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cluster cluster)) return false;
        return mass == cluster.mass &&
                p == cluster.p &&
                n == cluster.n &&
                name.equals(cluster.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public Cluster add(Cluster other) {
        return new Cluster(mass + other.mass, p + other.p, n + other.n, name + "+" + other.name);
    }

    public Cluster times(int count) {
        return new Cluster(mass * count, p * count, n * count,
                (name.contains("+") ? "(" + name + ")" : name) + "*" + count);
    }

    public Cluster renamed(String newName) {
        return new Cluster(mass, p, n, newName);
    }
}
