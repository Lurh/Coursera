array[int] of int: readings;
int: start;
int: l = length(readings);

set of int: TIME = 0..l;
array[TIME] of var 25..30: temp;
array[1..l] of var ACTION: choice;

enum ACTION = {HEAT, PRO_HEAT, COOL, PRO_COOL, STILL};
array[ACTION] of int: profit = [ 1, 4, -2, -5, 0];
array[ACTION] of int: pay = [ 1, 5, 3, 9, 0];

constraint temp[0] = start;
constraint forall(t in 1..l) (temp[t] = (temp[t-1] + readings[t]) div 2 + profit[choice[t]]);

var int: cost = sum(t in 1..l) (pay[choice[t]]);
solve minimize cost;

output ["temp = \(temp);\nchoice = \(choice);\ncost = \(cost)"];