package expressivo.parser;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link ExpressionVisitor}.
 * It can be extended to create a visitor that only needs to handle a subset
 * of the available methods.
 */
public class ExpressionBaseVisitor extends AbstractParseTreeVisitor<Expression> implements ExpressionVisitor {
    @Override 
    public Expression visitRoot(ExpressionParser.RootContext ctx) { 
        return visitChildren(ctx); 
    }

    @Override 
    public Expression visitSum(ExpressionParser.SumContext ctx) { 
        return visitChildren(ctx); 
    }

    @Override 
    public Expression visitPrimitive(ExpressionParser.PrimitiveContext ctx) { 
        return visitChildren(ctx); 
    }
}
