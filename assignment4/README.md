# AP_assignment

Assignment 4

Aayush Gakhar
2020006

CSE
2024

Assignment Description

Although we are providing a simple test case, it is really simple to play this game. The rules of
the game are exactly as shown in the above picture.
You must use the following OOP concepts in this assignment to obtain marks: a) classes and
objects, b) class relationships, c) exception handling at all appropriate places, d) object
cloning and e) generic programming. Whenever an exception is generated, you should
catch that exception and redo the execution step that generated the exception.
This is a single-player game with a really simple objective: the player gets 5 chances to hop and
land on the carpet of tiles. Every time the player lands on a random tile, s/he may win a soft toy.
The player stores all the soft toys he has won in a bucket. Once the player has hopped five
times, the game ends after listing out the details of the soft toys won by the player.
1. Tile Carpet
As shown in the picture, the carpet has 20 tiles. Each tile contains a unique soft toy. You can
use your imagination to have a unique soft toy stored at each tile. If the player lands on an
even-numbered tile, he directly wins the soft toy stored at that tile, otherwise, the player has to
solve a question. In this case, the soft toy is won only in case the player’s answer was correct.
There is also a possibility that the player is too energetic and jumps really long to land into the
muddy puddles at the end of the tile carpet.
2. Question Solving using a Calculator
The game contains a generic calculator for calculating either the result of the division of two
integers or for calculating the result of the concatenation of two strings. It doesn’t work on any
other data types. If the player lands on an odd-numbered tile, the game asks the player if he
would like to answer a question based on integer operation or string operation. If the player’s
choice is an integer operation, the game will generate a pair of random integers (all integers
supported). The game will then ask the player to enter the result of the division of these two
integers. The game will then pass these two integers to the generic calculator to verify the
player’s result. If the player’s choice is string operation, the game will generate a pair of random
strings of length four. The game will then ask the player to enter the result of the concatenation
of these two strings. The game will then pass these two strings to the generic calculator to verify
the player’s result. If the player’s calculation matches that of the calculator, the player will win
the soft toy.
3. Bucket to Store Soft Toys
Every time the player wins a soft toy at any tile, the tile will create a clone of its soft toy and then
hand it over to the player to store it in his bucket. At the end of the game, the game will simply
display all the soft toys collected by the player.