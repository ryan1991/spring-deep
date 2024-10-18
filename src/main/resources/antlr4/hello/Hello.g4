grammar Hello;
@header { package com.kinbo.boot2deep.antlr; }

s : 'hello' ID ;
ID : [a-z]+ ;
WS : [\t\r\n]+ -> skip ;