package org.viktar.sci;

import java.util.List;
import java.util.stream.IntStream;

public class LinearAlgebra {
    private static double[][] replaceColumn(double[][] originalMatrix, int index, double[] column) {
        double[][] matrix = new double[3][3];

        for (int i = 0; i < 3; i++) {
            System.arraycopy(originalMatrix[i], 0, matrix[i], 0, 3);
            matrix[i][index] = column[i];
        }

        return matrix;
    }

    private static double det(double[][] matrix) {
        if (matrix.length != 3 || matrix[0].length != 3 || matrix[1].length != 3 || matrix[2].length != 3) {
            throw new IllegalArgumentException("Matrix must be 3x3");
        }

        return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) -
                matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) +
                matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);
    }

    public static List<Double> solve(double[][] leftHandMatrix, double[] rightHandVector) {
        double determinant = det(leftHandMatrix);

        if (rightHandVector.length != 3) {
            throw new IllegalArgumentException("Right hand column must have length 3");
        }

        if (determinant == 0) {
            throw new IllegalArgumentException("Left hand matrix must be invertible");
        }

        List<Double> result = IntStream.range(0, 3)
                .mapToDouble(i -> det(replaceColumn(leftHandMatrix, i, rightHandVector)) / determinant)
                .boxed().toList();

        if (result.stream().anyMatch(x -> Double.isNaN(x) || Double.isInfinite(x))) {
            throw new IllegalArgumentException("No solution or infinite solutions");
        }

        return result;
    }
}
