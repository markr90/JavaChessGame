# Java Chess Game

Simple chess game written in Java, gui is written using Swing. The game is fully functional for human vs human. It is possible to undo moves and it is possible to restart the game.

The move history is not official chess algebraic notation. It was just added as a crude logging mechanism for moves.

## How to build and run

You will need
- Java Runtime Environment v17
- Java Development Kit v17

The below steps work on Ubuntu, tested on LTS 22.04. Opening the project in IntelliJ or Eclipse should also work, however I haven't verified this. To run this on Linux follow the below steps:

1. Clone the repository
2. cd to git repo that you just cloned
3. run the build.sh script
4. cd to the directory location_of_repo/out
5. run `java Game.Main`

## How to play

Chess pieces can be moved by either dragging a piece with the mouse or clicking on a piece and clicking on a target location. If you want to reset the game go to the menu in the top left and click restart. To quit the game just close the game.

## Known issues

- En passant has not been implemented
- Move history is not proper chess algebraic notation
- Pieces / text do not scale with window size

![Alt text](chessgame_screenshot.jpg?raw=true "Screenshot of the game")
