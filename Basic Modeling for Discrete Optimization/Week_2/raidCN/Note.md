# Code & Explaination of Project Raid
```
enum WARRIOR;
WARRIOR: dummy; 
enum CLAN; % the clan they belong too

int: l; % minimum raid party size
int: u; % maximum raid party size
int: m; % limit of hatreds

array[WARRIOR] of int: strength;
array[WARRIOR] of CLAN: clan;

array[CLAN,CLAN] of 0..1: hates;
%-------------------------------------- ↑ the given part 
%-------------------------------------- ↓ my code and explaination
array[1..u] of var WARRIOR: raid; % decision variable, a team of warrior

%% constraints
% ↓ make the warriors ordered
constraint forall(i in 1..u-1)(raid[i+1] >= raid[i] + (raid[i] != dummy)); % (1)

% ↓ make sure the number of team members meet the requirement
constraint sum(i in 1..u)(raid[i] != dummy) >= l; 
% prof's version: constraint forall(i in 1..l)(raid[i] != dummy); 
% ↑ cooperate with (1) makes the solution wrong, because when i > l, raid[i] >= raid[l], 
% which means the team size will always be the max

% ↓ make sure the sum of hates meet the requirement
constraint m >= sum(i,j in 1..u where i < j) (hates[clan[raid[i]],clan[raid[j]]]);
% i,j is index -> raid[i] is WARRIOR -> clan[raid[i]] is CLAN

% ↓ our goal
var int: obj;
constraint obj = sum(i in 1..u)(strength[raid[i]]);
solve maximize obj; 

% output
output ["raid = {"] ++ [if fix(raid[i]) != dummy then show(raid[i])
++ if i < u /\ fix(raid[i+1]) != dummy then ", " else "" endif
else "" endif | i in 1..u ] ++ ["};\n"] ++["obj = \(obj)"];