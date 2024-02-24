# COP4520Assignment2

There are two java files. The MinotaurParty.java represents the Minotaur's Birthday Party problem, and the MinotaurCrystal.java represents the Minotaur's Crystal Vase problem.

Problem 1 Solution:
- Ask the user for the N amount of guests
- Create threads for each guest and execute them when the Minotaur chooses one at random
- If the guest has not eaten, go into the maze and either eat the cupcake found, or request one if missing and then eat it
- If guest is chosen again, either don't eat the cupcake found, or request one and leave it in the maze
- Terminate program when all guests have eaten a cupcake

Problem 1 Efficiency:
In terms of efficiency, the first solution didn't really have any issues, appart from the fact that the Minotaur 
could choose a person that had already eaten to go into the maze again. That is the only thing that could prevent
the code from running faster.

Problem 1 Testing:
The solution was tested with different numbers of Guests and it performed well, from 10 guests all the way to 100.



Problem 2 Solution:
- I chose to implement the second strategy provided
- Ask the user for the N amount of guests
- Create threads for each guest and start their execution using a loop
- If the guest finds the room to be available, go inside and mark the room as busy, and sleep for three seconds
- If guests try to get in but room is busy, simulate them walking around the Minotaur's home by sleeping for 1 to 5 seconds randomly
- As soon as a guest gets out of the room, it won't try to go in again, as it wasn't mentioned in the second strategy
- As soon as all guests have visited the room, terminate program

I think that the guests should choose strategy 2. While it will probably take longer for all of them to get a chance 
to see the vase, it also allows them to roam the Minotaur's home while waiting. Furthermore, it prevents any lines
or any discomfort that comes from waiting in a line.


Problem 2 Efficiency:
In terms of efficiency, the strategy chose for problem 2 was not the fasted one by any means. It allows the room to potentially
be empty for a few seconds, wasting time that could be spent by someone visiting the room.


Problem 2 Testing:
Much like the first problem, this one was tested with different amounts of guests, from 10 to 100. It does however take a lot
longer than the first to finish when having more than 10 guests because of all the sleeping time the threads have.
