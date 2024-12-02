package expressivo;

import java.util.Map;

public class MultiplicationExpression implements Expression {
	/**
	 * Abstraction function:
	 * 	AF(exp1, exp2) = exp1 * exp2
	 * Rep invariant:
	 * 	exp1 and exp2 keep its rep invariant
	 * Safety from rep exposure:
	 * 	all fields are private and final
	 */
	
	private final Expression exp1, exp2;
	
	public MultiplicationExpression(Expression exp1, Expression exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public boolean isPrimitive() {
		return false;
	}
	
    @Override public Expression differentiate(String variable) {
        return new MultiplicationExpression(
        		new MultiplicationExpression(exp1, exp2.differentiate(variable)),
        		new MultiplicationExpression(exp2, exp1.differentiate(variable)));
    }
    
	@Override public Expression simplify(Map<String, Double> environment) {
		Expression left = exp1.simplify(environment);
		Expression right = exp2.simplify(environment);
		
		if (left.constant() && right.constant()) {
			Double value = Double.parseDouble(left.toString()) *
					Double.parseDouble(right.toString());
			return new NumberExpression(value.toString());
		}
		return new MultiplicationExpression(left, right);
	}
	
	@Override public String toString() {
		String left = exp1.isPrimitive() ? exp1.toString() :
			"(" + exp1.toString() + ")";
		String right = exp2.isPrimitive() ? exp2.toString() :
			"(" + exp2.toString() + ")";
		return left + "*" + right;
	}
	
	@Override public boolean equals(Object thatObject) {
		if (!(thatObject instanceof MultiplicationExpression)) {
			return false;
		}
		MultiplicationExpression that = (MultiplicationExpression) thatObject;
		return exp1.equals(that.exp1) && exp2.equals(that.exp2);
	}
	
	@Override public int hashCode() {
		return (exp1.hashCode() * exp2.hashCode()) % 1000;
	}
}