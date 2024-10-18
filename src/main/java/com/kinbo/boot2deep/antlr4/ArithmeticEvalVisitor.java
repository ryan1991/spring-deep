package com.kinbo.boot2deep.antlr4;

import com.kinbo.boot2deep.antlr4.calc.CalculatorBaseVisitor;
import com.kinbo.boot2deep.antlr4.calc.CalculatorParser;

/**
 * @author songjunbao
 * @date 2024-10-16
 */
public class ArithmeticEvalVisitor extends CalculatorBaseVisitor<Integer> {

    @Override
    public Integer visitMulDiv(CalculatorParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.MUL){
            return left * right;
        }
        return left / right;
    }

    @Override
    public Integer visitAddSub(CalculatorParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == CalculatorParser.ADD){
            return left + right;
        }
        return left - right;
    }

    @Override
    public Integer visitInt(CalculatorParser.IntContext ctx) {

        return Integer.valueOf(ctx.INT().getText());
    }


    @Override
    public Integer visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
