package expressivo.parser;

import expressivo.Expression;
import expressivo.Addition;
import expressivo.Constant;

public class ExpressionVisitor extends ExpressionBaseVisitor {
    // Visit a "sum" node
    @Override
    public Expression visitSum(ExpressionParser.SumContext ctx) {
        // Recursively visit left and right operands
        Expression left = visit(ctx.primitive(0));  // First operand (primitive)
        Expression right = visit(ctx.primitive(1)); // Second operand (primitive)

        // Return an addition expression for sum
        return new Addition(left, right);
    }

    // Visit a "primitive" node (either a number or a nested sum)
    @Override
    public Expression visitPrimitive(ExpressionParser.PrimitiveContext ctx) {
        if (ctx.NUMBER() != null) {
            // If it's a number, create a Constant with its value
            return new Constant(Double.parseDouble(ctx.NUMBER().getText()));
        } else {
            // Otherwise, visit the sub-expression inside parentheses
            return visit(ctx.sum());
        }
    }

    // The visitRoot method for the root node (optional in this case, since we can start from sum)
    @Override
    public Expression visitRoot(ExpressionParser.RootContext ctx) {
        // Start visiting from the sum node
        return visit(ctx.sum());
    }
}
