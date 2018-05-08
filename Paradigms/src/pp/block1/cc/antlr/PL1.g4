lexer grammar PL1;

@header{package pp.block1.cc.antlr;}

WORD : '"' (LETTER | INSERTION)* '"';
fragment LETTER : ~('"');
fragment INSERTION : '""';
