package kamal.calculator;

/**
 * Created by navneetgarg on 2016-01-30.
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorMethodsTest {
    private static final double DELTA = 1e-15;
    OperatorMethods inst1 = OperatorMethods.getInstance();

    @Test
    public void createsOneInstance() throws Exception {
        OperatorMethods inst2 = OperatorMethods.getInstance();
        assertTrue(inst1 == inst2);
    }

    @Test
    public void additionIsCorrect() throws Exception {
        assertEquals(10.0, inst1.exec("+", 3, 7), DELTA);
    }

    @Test
    public void additionHasPriority2() throws Exception {
        assertEquals(2, inst1.priority("+"));
    }

    @Test
    public void subtractionIsCorrect() throws Exception {
        assertEquals(5.0, inst1.exec("-", 10, 5), DELTA);
    }

    @Test
    public void subtractionHasPriority2() throws Exception {
        assertEquals(2, inst1.priority("-"));
    }

    @Test
    public void multiplicationIsCorrect() throws Exception {
        assertEquals(10.0, inst1.exec("x", 5, 2), DELTA);
    }

    @Test
    public void multiplicationHasPriority1() throws Exception {
        assertEquals(1, inst1.priority("x"));
    }

    @Test
    public void divisionIsCorrect() throws Exception {
        assertEquals(5.0, inst1.exec("/", 10, 2), DELTA);
    }

    @Test
    public void divisionHasPriority1() throws Exception {
        assertEquals(1, inst1.priority("/"));
    }

    @Test
    public void modulusIisCorrect() throws Exception {
        assertEquals(1.0, inst1.exec("%", 10, 3), DELTA);
    }

    @Test
    public void modulusHasPriority1() throws Exception {
        assertEquals(1, inst1.priority("%"));
    }

    @Test
    public void exponentIsCorrect() throws Exception {
        assertEquals(8, inst1.exec("^", 2, 3), DELTA);
    }

    @Test
    public void exponentHasPriority0() throws Exception {
        assertEquals(0, inst1.priority("^"));
    }

    @Test
    public void logarithmIsCorrect() throws Exception {
        assertEquals(2, inst1.exec("log", 25, 5), DELTA);
    }

    @Test
    public void logarithmHasPriority0() throws Exception {
        assertEquals(0, inst1.priority("log"));
    }

}