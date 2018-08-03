var 1..9: C;
var 0..9: A;
var 0..9: O;
var 1..9: Z;
var 0..9: H;
var 0..9: I;
var 1..9: P;
var 1..9: T;
var 0..9: E;
var 0..9: S;

var int: caozhi = C * 100 + A * 10 + O + Z * 100 + H * 10 + I;
var int: caopi = C * 100 + A * 10 + O + P * 10 + I;

include "alldifferent.mzn";
constraint alldifferent([C,A,0,Z,H,I,P,T,E,S]);
constraint caozhi + caopi = T * 1000 + E * 100 + S * 10 + T;
constraint P = Z * 3;
constraint O = 0;

solve satisfy;