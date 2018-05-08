lexer grammar LaLa;

@header{package pp.block1.cc.antlr;}

fragment LA : 'L' 'a'+ ' '*;
fragment LI : 'L' 'i' ' '*;
SIMPLE : LA;
DOUBLE : LA LA;
LONG : LA LA LA LI;
