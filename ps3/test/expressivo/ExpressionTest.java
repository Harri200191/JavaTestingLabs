package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy:
    //   - Test constants (numbers) and their toString(), equals(), and hashCode().
    //   - Test variables and their toString(), equals(), and hashCode().
    //   - Test addition and multiplication expressions.
    //   - Test equality of expressions with the same value but different structures.

    @Test
    public void testConstantToString() {
        Expression e = new Constant(5.0);
        assertEquals("5.0", e.toString());
    }

    @Test
    public void testVariableToString() {
        Expression e = new Variable("x");
        assertEquals("x", e.toString());
    }

    @Test
    public void testAdditionToString() {
        Expression e = new Addition(new Variable("x"), new Constant(2.0));
        assertEquals("x + 2.0", e.toString());
    }

    @Test
    public void testMultiplicationToString() {
        Expression e = new Multiplication(new Variable("x"), new Constant(3.0));
        assertEquals("x * 3.0", e.toString());
    }

    @Test
    public void testConstantEquals() {
        Expression e1 = new Constant(5.0);
        Expression e2 = new Constant(5.0);
        assertTrue(e1.equals(e2));
    }

    @Test
    public void testDifferentConstantEquals() {
        Expression e1 = new Constant(5.0);
        Expression e2 = new Constant(10.0);
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testVariableEquals() {
        Expression e1 = new Variable("x");
        Expression e2 = new Variable("x");
        assertTrue(e1.equals(e2));
    }

    @Test
    public void testDifferentVariableEquals() {
        Expression e1 = new Variable("x");
        Expression e2 = new Variable("y");
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testAdditionEquals() {
        Expression e1 = new Addition(new Variable("x"), new Constant(2.0));
        Expression e2 = new Addition(new Variable("x"), new Constant(2.0));
        assertTrue(e1.equals(e2));
    }

    @Test
    public void testDifferentAdditionEquals() {
        Expression e1 = new Addition(new Variable("x"), new Constant(2.0));
        Expression e2 = new Addition(new Variable("y"), new Constant(2.0));
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testMultiplicationEquals() {
        Expression e1 = new Multiplication(new Variable("x"), new Constant(3.0));
        Expression e2 = new Multiplication(new Variable("x"), new Constant(3.0));
        assertTrue(e1.equals(e2));
    }

    @Test
    public void testDifferentMultiplicationEquals() {
        Expression e1 = new Multiplication(new Variable("x"), new Constant(3.0));
        Expression e2 = new Multiplication(new Variable("x"), new Constant(4.0));
        assertFalse(e1.equals(e2));
    }

    @Test
    public void testHashCode() {
        Expression e1 = new Constant(5.0);
        Expression e2 = new Constant(5.0);
        assertTrue(e1.hashCode() == e2.hashCode());
    }

    @Test
    public void testDifferentHashCode() {
        Expression e1 = new Constant(5.0);
        Expression e2 = new Constant(10.0);
        assertFalse(e1.hashCode() == e2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        Expression e1 = new Addition(new Variable("x"), new Constant(2.0));
        Expression e2 = new Addition(new Variable("x"), new Constant(2.0));
        assertTrue(e1.hashCode() == e2.hashCode());
    }

    @Test
    public void testHashCodeInequality() {
        Expression e1 = new Addition(new Variable("x"), new Constant(2.0));
        Expression e2 = new Addition(new Variable("y"), new Constant(2.0));
        assertFalse(e1.hashCode() == e2.hashCode());
    }

    // Edge case: testing equality between expressions with different structures
    @Test
    public void testAdditionMultiplicationInequality() {
        Expression e1 = new Addition(new Variable("x"), new Constant(2.0));
        Expression e2 = new Multiplication(new Variable("x"), new Constant(2.0));
        assertFalse(e1.equals(e2));
    }

    // Check for null or different class types
    @Test
    public void testEqualsNull() {
        Expression e = new Constant(5.0);
        assertFalse(e.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        Expression e = new Constant(5.0);
        String str = "5.0";
        assertFalse(e.equals(str));
    }
}
