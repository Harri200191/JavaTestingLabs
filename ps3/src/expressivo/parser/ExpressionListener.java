// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(ExpressionParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(ExpressionParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void enterExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void exitExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#sum}.
   * @param ctx the parse tree
   */
  void enterSum(ExpressionParser.SumContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#sum}.
   * @param ctx the parse tree
   */
  void exitSum(ExpressionParser.SumContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#multiplication}.
   * @param ctx the parse tree
   */
  void enterMultiplication(ExpressionParser.MultiplicationContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#multiplication}.
   * @param ctx the parse tree
   */
  void exitMultiplication(ExpressionParser.MultiplicationContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#primitive}.
   * @param ctx the parse tree
   */
  void enterPrimitive(ExpressionParser.PrimitiveContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#primitive}.
   * @param ctx the parse tree
   */
  void exitPrimitive(ExpressionParser.PrimitiveContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#parentheses}.
   * @param ctx the parse tree
   */
  void enterParentheses(ExpressionParser.ParenthesesContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#parentheses}.
   * @param ctx the parse tree
   */
  void exitParentheses(ExpressionParser.ParenthesesContext ctx);
}