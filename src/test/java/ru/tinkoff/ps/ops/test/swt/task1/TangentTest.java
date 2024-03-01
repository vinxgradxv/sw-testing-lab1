package ru.tinkoff.ps.ops.test.swt.task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

public class TangentTest {

    @ParameterizedTest(name = "Вычисление тангенса для значений кратных Pi, а именно {0}")
    @MethodSource("PiValues")
    public void testPiValues(double value) {
        var result = Tangent.calc(value, 50);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result, 0.0001);
    }

    @ParameterizedTest(name = "Вычисление тангенса для некорректных значений, а именно x = {0}, n = {1}")
    @MethodSource("invalidValues")
    public void testInvalidNs(double x, int n) {
        Assertions.assertNull(Tangent.calc(x, n));
    }

    @ParameterizedTest(name = "Вычисление тангенса для значений близких к Pi/2, а именно {0}")
    @MethodSource("valuesNearBreakpoints")
    public void testValuesNearBreakpoints(double value) {
        Assertions.assertNull(Tangent.calc(value, 55));
    }

    @ParameterizedTest(name = "Вычисление тангенса для значений из области определения функции, а именно {0}")
    @MethodSource("nonPiValues")
    public void testNonPiValues(double value) {
        var result = Tangent.calc(value, 50);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Math.tan(value), result, 0.0001);
    }

    private static Stream<Arguments> invalidValues() {
        return Stream.of(Arguments.of(0, 0), Arguments.of(0, -1), Arguments.of(0, 100),
                Arguments.of(Math.PI / 2, 10));
    }

    private static Stream<Arguments> valuesNearBreakpoints() {
        double epsilon = 1E-10;
        return Stream.of(
                Arguments.of(Math.PI / 2 - epsilon),
                Arguments.of(-Math.PI / 2 + epsilon),
                Arguments.of(Math.PI / 2 + epsilon),
                Arguments.of(-Math.PI / 2 - epsilon)
        );
    }

    private static Stream<Arguments> PiValues() {
        return Stream.of(Arguments.of(0), Arguments.of(Math.PI), Arguments.of(-Math.PI),
                Arguments.of(-2 * Math.PI), Arguments.of(2 * Math.PI));
    }

    private static Stream<Arguments> nonPiValues() {
        return Stream.of(Arguments.of(Math.PI / 8), Arguments.of(Math.PI / 5), Arguments.of(Math.PI / 4),
                Arguments.of(-Math.PI / 4), Arguments.of(9 * Math.PI / 8), Arguments.of(6 * Math.PI / 5),
                Arguments.of(5 * Math.PI / 4), Arguments.of(-Math.PI / 8), Arguments.of(-Math.PI / 5), Arguments.of(-Math.PI / 4),
                Arguments.of(-9 * Math.PI / 8), Arguments.of(-6 * Math.PI / 5), Arguments.of(-5 * Math.PI / 4));
    }
}
