lexer grammar ID6;

@header{package pp.block1.cc.antlr;}

ID : ALPHABETICAL ALPHANUMERICAL ALPHANUMERICAL ALPHANUMERICAL ALPHANUMERICAL ALPHANUMERICAL ;
fragment ALPHABETICAL: 'a'..'z'|'A'..'Z';
fragment ALPHANUMERICAL: ALPHABETICAL | '0'..'9';