/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   differentiate():
	//		partition on rules:
	//			d(c)/d(x), d(x)/d(x), d(u+v)/d(x), d(u*v)/d(x)
	//	simplify():
	//		partition on variables:
	//			All variables in the environment and the expression
	//			Any variables in the environment but not the expression
	//			All variables not in the environment and not the expression
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Commands.differentiate() and Commands.simplify()
    
    /**
     * cover differentiate
     */
    @Test
    public void testDifferentiate() {
    	String diff = Commands.differentiate("12", "y");
    	assertEquals("expected differentiate zero", "0", diff);
    	
    	diff = Commands.differentiate("x", "y");
    	assertEquals("expected differentiate zero", "0", diff);
    	diff = Commands.differentiate("x", "x");
    	assertEquals("expected differentiate zero", "1", diff);
    	
    	diff = Commands.differentiate("x+y", "y");
    	assertEquals("expected differentiate zero", "0+1", diff);
    	
    	diff = Commands.differentiate("x*y", "z");
    	assertEquals("expected differentiate zero", "(x*0)*(y*0)", diff);
    }
    
    /**
     * cover variables
     */
    @Test
    public void testSimplify() {
    	String result = Commands.simplify("x*y", Map.of("x", 3.0, "y", 1.0));
    	assertEquals("expected simplify result", Expression.parse("3.0"), Expression.parse(result));
    	
    	result = Commands.simplify("x*y", Map.of("z", 3.0, "k", 1.0));
    	assertEquals("expected simplify result", "x*y", result);
    	
    	result = Commands.simplify("x*y", Map.of("x", 3.0, "k", 1.0));
    	assertEquals("expected simplify result", Expression.parse("3.0*y"), Expression.parse(result));
    }
    
    /**
     * cover IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void testDifferentiateExpressionInvalid() {
    	Commands.differentiate("x*", "o");
    }
    
    /**
     * cover IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void testDifferentiateVariableInvalid() {
    	Commands.differentiate("x*y", "000");
    }
    
    /**
     * cover IllegalArgumentException
     */
    @Test(expected=IllegalArgumentException.class)
    public void testSimplifyExpressionInvalid() {
    	Commands.simplify("x*", Map.of("y", 1.0));
    }

}
