// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, NUMBER=5, VARIABLE=6, SPACES=7;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_sum = 2, RULE_multiplication = 3, 
    RULE_primitive = 4, RULE_parentheses = 5;
  public static final String[] ruleNames = {
    "root", "expression", "sum", "multiplication", "primitive", "parentheses"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, "NUMBER", "VARIABLE", "SPACES"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(12);
      expression();
      setState(13);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExpressionContext extends ParserRuleContext {
    public MultiplicationContext multiplication() {
      return getRuleContext(MultiplicationContext.class,0);
    }
    public SumContext sum() {
      return getRuleContext(SumContext.class,0);
    }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public ExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expression; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
    }
  }

  public final ExpressionContext expression() throws RecognitionException {
    ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_expression);
    try {
      setState(18);
      switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(15);
        multiplication();
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(16);
        sum();
        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        setState(17);
        primitive();
        }
        break;
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class SumContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public MultiplicationContext multiplication() {
      return getRuleContext(MultiplicationContext.class,0);
    }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public SumContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_sum; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterSum(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitSum(this);
    }
  }

  public final SumContext sum() throws RecognitionException {
    SumContext _localctx = new SumContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_sum);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(22);
      switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
      case 1:
        {
        setState(20);
        multiplication();
        }
        break;
      case 2:
        {
        setState(21);
        primitive();
        }
        break;
      }
      setState(24);
      match(T__0);
      setState(25);
      expression();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MultiplicationContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public MultiplicationContext multiplication() {
      return getRuleContext(MultiplicationContext.class,0);
    }
    public MultiplicationContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_multiplication; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultiplication(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultiplication(this);
    }
  }

  public final MultiplicationContext multiplication() throws RecognitionException {
    MultiplicationContext _localctx = new MultiplicationContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_multiplication);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(27);
      primitive();
      setState(28);
      match(T__1);
      setState(31);
      switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
      case 1:
        {
        setState(29);
        multiplication();
        }
        break;
      case 2:
        {
        setState(30);
        primitive();
        }
        break;
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class PrimitiveContext extends ParserRuleContext {
    public ParenthesesContext parentheses() {
      return getRuleContext(ParenthesesContext.class,0);
    }
    public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
    public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
    public PrimitiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_primitive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPrimitive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPrimitive(this);
    }
  }

  public final PrimitiveContext primitive() throws RecognitionException {
    PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_primitive);
    try {
      setState(36);
      switch (_input.LA(1)) {
      case T__2:
        enterOuterAlt(_localctx, 1);
        {
        setState(33);
        parentheses();
        }
        break;
      case NUMBER:
        enterOuterAlt(_localctx, 2);
        {
        setState(34);
        match(NUMBER);
        }
        break;
      case VARIABLE:
        enterOuterAlt(_localctx, 3);
        {
        setState(35);
        match(VARIABLE);
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ParenthesesContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public ParenthesesContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_parentheses; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterParentheses(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitParentheses(this);
    }
  }

  public final ParenthesesContext parentheses() throws RecognitionException {
    ParenthesesContext _localctx = new ParenthesesContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_parentheses);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(38);
      match(T__2);
      setState(39);
      expression();
      setState(40);
      match(T__3);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\t-\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\3\3\5\3\25"+
      "\n\3\3\4\3\4\5\4\31\n\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5\"\n\5\3\6"+
      "\3\6\3\6\5\6\'\n\6\3\7\3\7\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2,\2\16"+
      "\3\2\2\2\4\24\3\2\2\2\6\30\3\2\2\2\b\35\3\2\2\2\n&\3\2\2\2\f(\3\2"+
      "\2\2\16\17\5\4\3\2\17\20\7\2\2\3\20\3\3\2\2\2\21\25\5\b\5\2\22\25"+
      "\5\6\4\2\23\25\5\n\6\2\24\21\3\2\2\2\24\22\3\2\2\2\24\23\3\2\2\2\25"+
      "\5\3\2\2\2\26\31\5\b\5\2\27\31\5\n\6\2\30\26\3\2\2\2\30\27\3\2\2\2"+
      "\31\32\3\2\2\2\32\33\7\3\2\2\33\34\5\4\3\2\34\7\3\2\2\2\35\36\5\n"+
      "\6\2\36!\7\4\2\2\37\"\5\b\5\2 \"\5\n\6\2!\37\3\2\2\2! \3\2\2\2\"\t"+
      "\3\2\2\2#\'\5\f\7\2$\'\7\7\2\2%\'\7\b\2\2&#\3\2\2\2&$\3\2\2\2&%\3"+
      "\2\2\2\'\13\3\2\2\2()\7\5\2\2)*\5\4\3\2*+\7\6\2\2+\r\3\2\2\2\6\24"+
      "\30!&";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}