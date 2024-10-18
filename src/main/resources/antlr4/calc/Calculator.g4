grammar Calculator;

expr: expr op=('*'|'/') expr # MulDiv
    | expr op=('+'|'-') expr # AddSub
    | INT                    # int
    | '(' expr ')'           # parens
    ;

INT  : [0-9]+ ; //匹配整数

MUL    : '*' ;
DIV    : '/' ;
ADD    : '+' ;
SUB    : '-' ;