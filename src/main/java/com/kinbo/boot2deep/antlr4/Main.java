package com.kinbo.boot2deep.antlr4;


import com.kinbo.boot2deep.antlr4.calc.CalculatorLexer;
import com.kinbo.boot2deep.antlr4.calc.CalculatorParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author songjunbao
 * @date 2024-10-16
 */
public class Main {

    public static void main(String[] args) {
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString("1*3+2*3"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expr();
        ArithmeticEvalVisitor eval = new ArithmeticEvalVisitor();
        Integer result = eval.visit(tree);
        System.out.println(result);
    }
}
