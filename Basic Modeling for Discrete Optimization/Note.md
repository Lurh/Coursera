# 课堂笔记 与 相关素材
## Week 1
Minizinc 基础知识 http://www.minizinc.org/tutorial/minizinc-tute-cn.pdf

## Week 2
主要讲解了集合set: `var set of OBJ: set_name;`
`OBJ`为表示定义域的`enum`变量
* 固定势集合
    1. 使用约束筛选集合大小 `card(set_name) = size;`
        * 适用情况：求解器本身支持集合 且 `OBJ`含有的元素数不是太大。
    2. 使用固定长度的数组表示 `array[1..size] of OBJ: array_name;`
        * 使用`forall(i in 1..size-1) (x[i] < x[i+1])` 保证有序,即保证不重复，
        避免出现`[1, 2]`与`[2, 1]`这样表示同个集合的情况。
        * 适用情况：当`size`比较小。
* 有界势集合
    1. 使用约束筛选集合大小 `card(set_name) <= size;`
    2. 使用固定长度的数组表示 `array[1..size] of OBJ: array_name;`
        * 对于`OBJ`需要加入一个表示空的值`dummy`，其相关的参数均置为0。
        * 一样要求有序，但是需要加入对空值的判断，修改为：
        `forall(i in 1..size-1) (x[i] + (x[i] != dummy))<= x[i+1])`
        使得前面的若干个可以表示为`dummy`，非`dummy`值后的值均为有效值。

## Week 3
1. `alldifferent`用于保证输入变量中的值都不同（唯一），使用前需`include "alldifferent.mzn"`
    前提是建立一个单射函数来表示输入变量
2. 使用中间变量存储，避免在`constraint`中反复进行计算，降低求解复杂度。
    ```
        array[DAY] of var 1..u: onEve;
        constraint onEve = [sum(s in SOLDIER) (roster[s,d]=EVE) | d in DAY];
    ```
    表示约束每天在傍晚值班的人数在1到u之间。
3. `global_cardinality(x, v, c)`是另一个约束划分类的大小的方法
    * `v` 和 `c`的长度一样
    * 约束在`x`中出现的$v_i$ 的次数为 $c_i$
    ```
        array[DAY] of var 1..u: onEve;
        constraint onEve = [sum(s in SOLDIER) (roster[s,d]=EVE) | d in DAY];
        constraint forall(d in DAY) 
        (global_cardinality([roster[s,d] | s in SOLDIER], [NIGHT, EVE], [o, onEve[d]]));
    ```
    约束每天值夜班和傍晚班的人数分别为`[o, onEve[d]]`
    * 相关的函数还有
        * `global_cardinality_closed(x, v, c)` 表示只能使用`v`中的值生成`x`
        * `global_cardinality_low_up(x, v, lo, hi)` 表示`v`中值的出现次数可以在范围内波动
        * `global_cardinality_low_up_closed(x, v, lo, hi)` 表示只能使用`v`中的值生成`x`
    
4. `value_precede_chain(array[int] of int: c, array[int] of var int: x)`是用于去值对称的全局约束
    该函数可以强制`c[i]`在x中的**第一次**出现先于`c[i+1]`在x中**第一次**出现，和Week 2提到的有序约束类似。
    因此可以改写成`value_precede_chain([i | i in CLUSTER], ans)`
5. 纯划分问题可能的情况
    * 不多于k个类
    * 已知k个类
    * 类的数量未知（可令与样本数量相同）
6. 自动机
    ``` 
    include "regular.mzn";
    % regular(list of variables, number of states, number of valid states, transition matrix, start state, final state)
    % the main point is to construct the transition matrix
    constraint regular(taste[dish[i]] | i in COURSE, 7, 6, d, 1, {5});
    array[1..7,TASTE] of 0..7: d = 
        [| 0,0,4,0,0,0
        | 0,0,0,5,0,7
        | 0,0,0,0,6,7
        | 2,3,0,5,6,7
        | 0,3,4,0,0,7
        | 2,3,4,5,0,7
        | 2,3,4,5,6,0 |];
    ```

## Week 4
* **完全匹配**：一个双射函数有两个视角，使用目标函数与其逆函数都可以解决，但是可能效率不同，选择效率高的角度来更快解决问题
* 不同视角=>约束的表达方式不同，在一个模型中不好表达的，在对称的问题中可以轻松表达
* 排列问题总是会有至少两个视角
* 通过使用**连通约束**，可以使两个函数一致化：
`forall(w in WINE, f in FOOD) (eat[w] = f <-> drink[f] = w);`
* 也可以使用全局约束来描述：`constraint inverse(eat, drink)` 或 `inverse(drink, eat)`
`inverse`已经内在含有`alldifferent`的要求
* `include "global.mzn";` 包含了所有的全局约束
* 同一个视角但是使用不同的表示方法
```
    固定势集合表示
    集合变量
    var set of OBJ: s;
    card(s) = u;
    整形变量数组（每个值各有一个）
    array[1..u] of OBJ: x;
    forall(i in 1..u-1) (x[i] > x[i+1]);
    
    连通
    forall(o in OBJ) (o in s -> exists(i in 1..u) (x[i] = o));
    forall(i in 1..u) (x[i] in s);
```






