package org.viktar.sci;

import java.util.List;
import java.util.stream.IntStream;

public class NuclearTransmutations {
    public static List<Double> distributionAfterTransmutation(Cluster source, Cluster product1, Cluster product2, Cluster product3) {
        double[][] matrix = {
            {product1.protons(), product2.protons(), product3.protons()},
            {product1.neutrons(), product2.neutrons(), product3.neutrons()},
            {product1.mass(), product2.mass(), product3.mass()}
        };
        double[] column = new double[]{source.protons(), source.neutrons(), source.mass()};
        List<Double> systemSolution = LinearAlgebra.solve(matrix, column);
        List<Double> masses = IntStream.range(0, 3)
                .mapToDouble(i -> systemSolution.get(i) * matrix[2][i])
                .boxed().toList();
        double sum = masses.stream().mapToDouble(Double::doubleValue).sum();
        List<Double> result = masses.stream().map(mass -> mass / sum).toList();

        if (result.stream().anyMatch(x -> x < 0 || x > 1))
            throw new IllegalArgumentException("Transmutation of " + source + " to " + product1 + ", " + product2 + " and " + product3 + " is not possible");

        return result;
    }

    public static double relativeEnergyYield(Cluster source, Cluster product) {
        if (source.protons() * product.neutrons() != source.neutrons() * product.protons()) {
            throw new IllegalArgumentException("Proton to neutron ratio must be the same for source " + source + " and product " + product);
        }

        double relativeEnergyYield = 1 -
                (product.mass() / (product.protons() + product.neutrons()))
                /
                (source.mass() / (source.protons() + source.neutrons()));

        if (relativeEnergyYield < 0) {
            throw new IllegalArgumentException("Transmutation of " + source + " to " + product + " is not energetically favorable");
        }

        return relativeEnergyYield;
    }
}
