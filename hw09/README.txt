=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: xianhanc
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array - I use a 2D array to represent each square on the board. 

  2. I/O - I create a new file on windowclose if the game hasn't ended and write 
  		   the necessary information to recreate the game state. However, if the
  		   game has ended, the file is deleted. On reopen, if the file exists, 
  		   read the information to remake the past game state. If the file 
  		   doesn't exist, start a new game.

  3. Maps - I map a checkers board to the turn number. I use this to implement
  			an undo button that retrieves the checkers board that is 1 less than
  			the current turn number and changes the game state.

  4. Testable Component - I make the clicks call another function whose arguments 
  						  use click information. I can test my game by simply 
  						  calling those functions and manually providing the
  						  arguments that correspond with where I want to move
  						  the piece.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  Piece - Represents each square on the checkers board (what color, is it 
  		  a piece, is it a king).
  		  Contains getter and setter methods.
  Checkers - Represents the checkers board and game state (holds 2d array of 
  			 pieces, how many turns passed, whose turn, gameOver).
  			 Sets up the initial board (sets pieces, black starts, 0 turn)
  			 Contains methods to move/capture with black and red pieces.
  			 Contains getter and setter methods.
  GameBoard - Represents game and game state (checkers, whose turn, map
  			  of turn and board)
  			  Contains methods to setup status/map/mouse controls
  			  Contains methods to draw
  			  Contains methods for button actions
  RunCheckers - Holds JFrame and JPanels and JButtons
  				Creates file when window is closed and game is not over
  

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I was stuck at undo for a long time because encapsulation. My getter/setter 
  methods just gave back the 2d array of pieces when I should've made returned 
  a copy. Also, I called my mouse click position function getX()/getY() which 
  overrode another function. It caused everything to be offset and drawn 
  incorrectly. I had to go to office hours to figure out this bug.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  Nothing can be changed without calling setter functions so I think it is 
  encapsulated well. I couldn't figure out how to reset the clicks so that if it was 
  invalid, it would register as a new "clickOne". I also couldn't figure out how to 
  make checkWinner also check if red/black didn't have any valid moves/captures.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  None
