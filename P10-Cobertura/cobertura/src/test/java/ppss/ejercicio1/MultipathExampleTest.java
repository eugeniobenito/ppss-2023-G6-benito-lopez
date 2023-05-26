package ppss.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MultipathExampleTest {
    int a, b, c, resultExp, result;
    MultipathExample multiPath;

    @BeforeEach
    void setUp() {
        c = 0;
        multiPath = new MultipathExample();
    }

    @Test
    void multiPathC1() {
        a = 6;
        b = 6;
        resultExp = 12;

        result = multiPath.multiPath1(a, b, c);

        assertEquals(resultExp, result);
    }

    @Test
    void multiPathC2() {
        a = 4;
        b = 4;
        resultExp = 0;

        result = multiPath.multiPath1(a, b, c);

        assertEquals(resultExp, result);
    }

    @Test
    void multiPathC3() {
        a = 3;
        b = 6;
        c = 2;
        resultExp = 8;

        result = multiPath.multiPath1(a, b, c);

        assertEquals(resultExp, result);
    }

    @ParameterizedTest
    @MethodSource("CasosDePruebaMultiPath2")
    void testParametrizadoCasosDePruebaMultiPath2(int a, int b, int c, int resultExp) {
        int res = multiPath.multiPath2(a, b, c);

        assertEquals(resultExp, res);
    }

    private static Stream<Arguments> CasosDePruebaMultiPath2() {
        return Stream.of(
                Arguments.of(6, 4, 6, 16),
                Arguments.of(4, 0, 4, 4),
                Arguments.of(6, 6, 6, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("CasosDePruebaMultiPath2")
    void testParametrizadoCasosDePruebaMultiPath3(int a, int b, int c, int resultExp) {
        int res = multiPath.multiPath3(a, b, c);

        assertEquals(resultExp, res);
    }

    private static Stream<Arguments> CasosDePruebaMultiPath3() {
        return Stream.of(
                Arguments.of(6, 4, 6, 16),
                Arguments.of(4, 4, 4, 4),
                Arguments.of(6, 6, 4, 4),
                Arguments.of(4, 6, 4, 4)
        );
    }
}