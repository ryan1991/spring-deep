// Generated from Hello.g4 by ANTLR 4.9.2
 package com.kinbo.boot2deep.antlr4.hello;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(HelloParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(HelloParser.SContext ctx);
}