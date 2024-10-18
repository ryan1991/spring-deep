package com.kinbo.boot2deep.antlr4.hello;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author songjunbao
 * @date 2024-10-16
 */
public class Main {
    public static void main(String[] args) {
        HelloLexer lexer = new HelloLexer(CharStreams.fromString("hello world"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.s();
        System.out.println(tree.toStringTree(parser));

    }
}
