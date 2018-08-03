% Beauty Trap
% Perform the most seductive dance

enum LEGS = {spin, leap, waltz, curtsey, prep, stand};
enum ARMS = {beckon, out, up, wrapped, neutral};
enum FACE = {smile, wink, batt, think, glow, blank};

int: maxlen;
set of int: STEP = 1..maxlen;
array[LEGS] of int: maxlegs;
array[ARMS] of int: maxarms;
array[FACE] of int: maxface;
constraint assert(maxlegs[stand] >= maxlen, "maxlegs[stand] smaller than maxlen");
constraint assert(maxarms[neutral] >= maxlen, "maxarms[neutral] smaller than maxlen");
constraint assert(maxface[blank] >= maxlen, "maxface[blank] smaller than maxlen");
array[LEGS,ARMS] of int: dance_value;
array[ARMS,FACE] of int: entice_value;
constraint assert(dance_value[stand,neutral] = 0, "incorrect dance_value array");
constraint assert(entice_value[neutral,blank] = 0, "incorrect entice_value array");
int: boredom; % how bored each step make the viewer

var STEP: len;
array[STEP] of var LEGS: legs;
array[STEP] of var ARMS: arms;
array[STEP] of var FACE: face;

include "global_cardinality_low_up_closed.mzn";

constraint global_cardinality_low_up_closed(legs,LEGS,[0 | i in LEGS],maxlegs);
constraint global_cardinality_low_up_closed(arms,ARMS,[0 | i in ARMS],maxarms);
constraint global_cardinality_low_up_closed(face,FACE,[0 | i in FACE],maxface);

include "regular.mzn";
constraint regular(legs, 7, 6, d, 1, {7});
array[1..7,LEGS] of 0..7: d = 
  [| 2,0,4,5,6,7
   | 0,0,0,5,6,7
   | 2,0,4,0,0,7
   | 2,0,4,0,6,7
   | 2,0,4,5,0,7
   | 2,3,4,5,6,7
   | 2,0,4,5,6,7 |];
constraint forall(i in 1..len-3)(legs[i] = waltz /\ legs[i+1] = waltz /\ legs[i+2] = waltz -> legs[i+3] != waltz);
constraint forall(i in 1..maxlen-1)(legs[i] = waltz -> forall(j in i+1..len)(legs[j] = curtsey -> exists(k in i+1..j-1)(legs[k] = stand)));

constraint regular(arms, 6, 5, td, 1, {6});
array[1..6,ARMS] of 0..6: td = 
  [| 0,3,4,0,6
   | 0,3,4,5,6
   | 2,0,4,0,0
   | 2,3,0,0,6
   | 0,3,4,0,0
   | 0,3,4,5,6 |];
   
constraint regular(face, 7, 6, ttd, 1, {7});
array[1..7,FACE] of 0..7: ttd = 
  [| 2,3,4,5,6,7
   | 0,3,4,0,6,7
   | 2,0,4,5,6,7
   | 2,3,0,0,6,7
   | 2,3,4,0,6,7
   | 2,3,4,5,0,7
   | 2,3,4,5,6,7 |];
   
constraint forall(i in len+1..maxlen)(legs[i] = stand);
constraint forall(i in len+1..maxlen)(arms[i] = neutral);
constraint forall(i in len+1..maxlen)(face[i] = blank);

var int: obj;
constraint obj = sum(i in 1..len)(dance_value[legs[i],arms[i]]) + sum(i in 1..len)(entice_value[arms[i],face[i]]) - boredom * len;

solve maximize obj;