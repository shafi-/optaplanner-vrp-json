************************************************************************
file with basedata            : mm13_.bas
initial value random generator: 2494
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
    1     10      0       16        4       16
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           5   9  11
   3        3          2           6   8
   4        3          2           8  11
   5        3          2           6   7
   6        3          1          10
   7        3          1           8
   8        3          1          10
   9        3          1          12
  10        3          1          12
  11        3          1          12
  12        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     1       9    0    2    6
         2     2       5    0    2    6
         3     2       6    0    2    5
  3      1     3       9    0    8    4
         2     5       4    0    8    2
         3     5       0    3    8    4
  4      1     1       0    5   10    7
         2     2       4    0    4    7
         3     9       0    4    2    4
  5      1     3       0    2    8    8
         2     8       0    2    7    4
         3     9       0    1    6    2
  6      1     2       4    0    7    3
         2     4       0    6    5    2
         3     6       2    0    4    1
  7      1     6       8    0    6   10
         2     8       7    0    4    6
         3     9       0    8    4    5
  8      1     4       4    0    5    8
         2     6       0    9    5    7
         3    10       0    7    5    4
  9      1     5       8    0    8    9
         2     7       5    0    8    6
         3     9       3    0    8    5
 10      1     2       0    6    4    5
         2     2       8    0    4    5
         3     5       6    0    3    5
 11      1     3       0    2    4    5
         2     9       0    1    4    3
         3     9       8    0    4    2
 12      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   17    8   49   41
************************************************************************
