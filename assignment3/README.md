# AP_assignment

Assignment 3

Aayush Gakhar
2020006

CSE
2024

Assignment Description
 Although we are providing a simple test case, it is really simple to play this game. The rules of the game are exactly as shown in the above picture.
You must use the following OOP concepts in this assignment to obtain marks: a) classes and objects, b) class relationships, c) inheritance, and d) polymorphism. Interfaces should NOT be used.
1. Player
This is a single player game with a really simple objective: how many game points the player obtained when he completed the game. The player has a name and s/he must remember his position on the game floor after every move.
2. Game
As shown in the picture, the game would have 14 floors and it counts the total points collected during the game play. Negative points are also allowed in the game. There are different types of floors: a) empty floor, b) snake floor, and c) ladder floor. There are two types of snake floor that you can ever encounter: a) normal and b) king cobra. Likewise, there are two types of ladders that you can ever encounter: a) normal, and b) elevator. The location of each snake floor, ladder floor, and empty floor is fixed as shown in the picture. Each floor must remember its location in the game.
Whenever a player moves to any floor, s/he has to jump on the floor to know the type of floor:
* Empty floor rewards the player with one point and displays the message that player has
reached an empty floor.
* Normal snake floor would deduct two points from total and would display the message
that player has reached normal snake floor. After this it will move the player’s position to
the first floor as shown in the picture.
* King cobra snake would deduct four points from total and would display the message
that player has reached king cobra. After this it will move the player’s position to the third
floor as shown in the picture.
* Ladder floor rewards the player with two points and displays the message that the player
has reached a ladder floor. After this it will move the player’s position to the twelfth floor
as shown in the picture.
* Elevator floor rewards the player with four points and displays the message that the
player has reached the elevator floor. After this it will move the player’s position to the tenth floor as shown in the picture.
3. Dice
This game has a special dice that has only two faces, 1 and 2. When a dice is thrown, it returns a randomly chosen face value. To make this assignment even more simpler, you can reuse the Dice class from Lecture 01.
The game is started only when the player gets 1 on the Dice.
