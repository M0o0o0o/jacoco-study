package m0o0o0o.jacoco.jacocostudy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    @Test
    void test() {
        Calculator calculator = new Calculator();

        assertEquals(calculator.calculate(1, '+', 1), 2);

    }
}