htwg-scala-puzzlerun
=========================

This game is a single-player puzzle game where the user has to get from the starting point to the goal. In every level he has a set of moves he can choose from. He has to create a way with these moves, avoiding the obstacles along the way. The user has to do so BEFORE hitting play and once he does hit play, the sequence of moves he has set, will play all at once. If he hits an obstacle, he dies and can retry the level, if he reaches the goal, he proceeds to the next level.

* Has a folder structure prepared for a MVC-style application
* Has .gitignore defaults

## Model
* Grid
	* Consists of cells
	* Functions to return or set the cell with given coordinates
* Cell
	* Methods to check by whom it is occupied
	* occupiedBy var = gameObject
* Gameobject
	* Player
		* Has coordinates (set as var) with start coordinate
		* name
	* Obstacle
		* Has coordinates (set as val)
		* val allowedMoves = []
	* Goal
		* Has coordinates (set as val)
* Move
	* up, down, right, left methods
	* returns changed coordinates
* Level
	* Contains all the other model data

## Controller
* Master-controller
* Other controllers
* Creates a dictionary of moves (move-pool)
* Creates queue of moves by player

## View
* TUI
	* If player jumps on obstacle, player is visible, obstacle possibly disappears
* GUI