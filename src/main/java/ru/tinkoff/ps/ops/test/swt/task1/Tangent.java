package ru.tinkoff.ps.ops.test.swt.task1;

import java.util.ArrayList;
import java.util.List;

public class Tangent {

    public static Double calc(double x, int n) {
        if (n <= 0 || n > 50) {
            return null;
        }

        x = fixPeriod(x);
        if (Math.abs(x) == Math.PI / 2) {
            return null;
        }
        double xPow = x;

        double result = 0;
        double denominator = 1;

        var bernoulliNumbers = computeBernoulliNumbers(2 * n);
        var multiplier1 = 1;
        var multiplier2 = 1;
        for (int i = 1; i <= n; i++) {

            for (int j = 2 * i; j > 2 * (i-1); j--) {
                denominator *= j;
                multiplier2 *= 2;
            }

            result += multiplier1 * multiplier2 * (multiplier2 - 1) * bernoulliNumbers.get(2 * i) * x / denominator;

            multiplier1 *= -1;

            for (int j = 2 * i - 1; j > 2 * (i-1) - 1; j--) {
                x *= xPow;
            }
        }

        return result;
    }

    private static double fixPeriod(double x) {
        if (x >= 0) {
            while (x >= Math.PI / 2 && x > 0) {
                x -= Math.PI;
            }
        } else {
            while (x <= - Math.PI / 2 && x < 0) {
                x += Math.PI;
            }
        }
        return x;
    }

    public static List<Double> computeBernoulliNumbers(int n) {
        List<Double> bernoulliNumbers = new ArrayList<>();
        bernoulliNumbers.add(1d); // B₀ = 1

        for (int k = 1; k <= n; k++) {
            double sum = 0;
            for (int j = 0; j < k; j++) {
                double term = bernoulliNumbers.get(j) * binomialCoefficient(k + 1, j);
                sum = sum -= term;
            }
            bernoulliNumbers.add(sum / (k + 1));
        }

        return bernoulliNumbers;
    }

    public static double binomialCoefficient(int n, int k) {
        double numerator = factorial(n);
        double denominator = factorial(k) * factorial(n - k);
        return numerator / denominator;
    }

    public static double factorial(int n) {
        double result = 1d;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
