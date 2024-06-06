# FIT3077 Sprint 3 - Fiery Dragon (Semester 1, 2024)
Created by:
- Chong Jet Shen
- Chua Wen Yang
- Chen Jac Kern

# Project Description

This repository contains the source code and documentation of Sprint 3 of FIT3077 - Software engineering: Architecture and design.

Fiery Dragons is a memory based game for 2-4 players ages 5-99 and takes about 15 minutes to play.
Players act as dragons trying to get back to their cave by searching for the right cards to help them move.

<div>
  <div>
    <img src="src/resources/Project_cartoon/Sprint4_Main.png"  width="350" height="250">
  </div>

  <div>
    <img src="src/resources/Project_cartoon/Sprint4_Game.png"  width="350" height="250">
  </div>
</div>

# Requirement

In Sprint 3, we need to implement the basic game rules for the Fiery Dragon, this includes:
- An ‘initial’ board at the beginning of the game,
- A player flips a Dragon card,
- The dragon token moves correctly based on the last uncovered Dragon card,
- A player uncovers another Dragon card if the turn is not over yet,
- Once a turn is over, flip all the Dragon cards face down for next player’s turn,
- A specific situation where a Dragon Pirate card is uncovered or a different animal (to the
animal shown on the square where your dragon stands) is uncovered or your dragon
would land on an occupied square
- The end of the game.

Besides, we also implement some basic game rules which includes:
- Choosing 2 to 4 dragons to be played in the game.
- Allow the user to choose the dragon to be played, and enter their age.
- The first game start from the player with the smallest age, then pass to next turn
in the clockwise direction.
- If a player wins the game, the new game will start from that player then in clockwise
direction.

For the GUI framework, we also implement the home page of the game and buttons.

In Sprint 4, we need to implement two required extensions along with one self-defined extension to the Fiery Dragon game.

Required extensions:
- New Dragon Card 1:
  - Sends the current player to the closest and unoccupied cave which is behind that player. 
  - After sending the player to the nearest cave, the player has another chance to flip another dragon card to move forward. 
  - However, if the player who flips this new dragon card is at the cave, the turn will directly pass to the next player. 

- Loading and saving the game from/to an external (configuration) file:
  - Our team has chosen to ...
  - 

Self-defined extension:
- Time limit per game round

# Documentation
Refer to docs/Sprint3 for UML class diagrams and the review of each solution in Sprint 2.

# Technologies used
- Java
- Java Swing

# Executable
Windows is the main platform for the executable file. Before opening and running the file, you would like to have Java SE Development Kit 22.0.1 and OpenJDK-22 (Oracle OpenJDK version 22.0.1) installed on your machine.
Here is the website link for installation:
https://www.oracle.com/my/java/technologies/downloads/#jdk22-windows 

