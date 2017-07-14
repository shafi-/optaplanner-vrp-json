************************************************************************
file with basedata            : mm16_.bas
initial value random generator: 1735214583
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  12
horizon                       :  73
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     10      0       19        0       19
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          1           5
   3        3          3           6   7  10
   4        3          3           7   8  11
   5        3          1           8
   6        3          1          11
   7        3          1           9
   8        3          2           9  10
   9        3          1          12
  10        3          1          12
  11        3          1          12
  12        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     6       0    7    9    4
         2     6       5    0    9    7
         3     7       2    0    8    2
  3      1     4       5    0    5    8
         2     5       0   10    4    4
         3     6       0    6    4    2
  4      1     1       0    8    8    5
         2     7       7    0    7    4
         3    10       0    5    4    3
  5      1     1       0    8   10    4
         2     4       0    6    8    4
         3     7       1    0    7    4
  6      1     1       4    0   10    3
         2     1       0    6   10    3
         3     3       0    6   10    2
  7      1     5      10    0    8    8
         2     6       7    0    8    3
         3    10       0    2    8    2
  8      1     4       0    6    4    5
         2     6       6    0    4    3
         3    10       0    6    3    2
  9      1     8      10    0    6    7
         2     9       0    7    4    7
         3    10       4    0    4    6
 10      1     2       0    8    5    6
         2     2       7    0    6    9
         3     9       0    8    1    2
 11      1     1       4    0    8    7
         2     1       0   10    6    4
         3     1       0    7    5    9
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
    9   13   74   65
************************************************************************
