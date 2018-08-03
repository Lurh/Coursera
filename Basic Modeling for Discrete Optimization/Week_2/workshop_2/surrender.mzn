% workshop 2
% select a set of trade negotiation party
enum NEGOTIATOR;
NEGOTIATOR: dummy; 

int: l; % minimum party size
int: u; % maximum party size
int: m; % minimum joint ability

array[NEGOTIATOR] of int: honor;
array[NEGOTIATOR,NEGOTIATOR] of int: joint;

var set of NEGOTIATOR: attends;
constraint m <= sum(i,j in attends where i < j) (joint[i,j]);
constraint card(attends) >= l;
constraint card(attends) <= u;
var opt int: min_honor = min(i in attends) (honor[i]);

solve maximize min_honor;

output ["party = \(attends)\nobj = \(min_honor)"];