package expressivo;

import java.util.Map;

public class VariablesExpression implements Expression {
	/**
	 * Abstraction function:
	 * 	AF(var) = variable name
	 * Rep invariant:
	 * 	var contain case-sensitive nonempty sequences of letters
	 * Safety from rep exposure:
	 * 	all fields are private, final and immutable
	 */
	
	private final String name;
	
	public VariablesExpression(String v) {
		if (!v.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("invalid variable name: " + v);
		}
		this.name = v;
		checkRep();
	}
	
	private void checkRep() {
		assert name.matches("[a-zA-Z]+");
	}
	
	public boolean isPrimitive() {
		return true;
	}
	
	@Override public Expression differentiate(String variable) {
        return differentiate(new VariablesExpression(variable));
	}
	
	@Override public Expression simplify(Map<String, Double> environment) {
		if (environment.containsKey(name)) {
			return new NumberExpression(environment.get(name).toString());
		}
		return this;
	}
	
	/**
	 * @param variable VariablesExpression to differentiate by
	 * @return expression's derivative with respect to variable.
	 */
	public Expression differentiate(VariablesExpression variable) {
        if (this.equals(variable)) {
            return new NumberExpression("1");
        } 
        return new NumberExpression("0");
	}
	
	@Override public String toString() {
		return name;
	}
	
	@Override public boolean equals(Object thatObject) {
		if (!(thatObject instanceof VariablesExpression)) {
			return false;
		}
		VariablesExpression that = (VariablesExpression) thatObject;
		return name.equals(that.name);
	}
	
	@Override public int hashCode() {
		return name.hashCode();
	}
}