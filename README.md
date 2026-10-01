# Modernization of Orego
I found Dr Peter Drake's computer Go program for Java as I was looking for a Baduk Java engine with rule based
implementation for my android Go app. Orego was old and doesn't fit modern Java build practices, but it had most of
all the features I needed. Thus, I begin my attempt to modernize the codebase to compile as 2 jars:

1. The Core Orego Engine and essential tools
A practical Go engine with proper documentation and usage examples.

2. Dr. Peter Drake's Original Orego CLI game program & other experiments. 
Much of the experiment's code remains untouched and undocumented.


> [!NOTE] About Dr. Peter Drake's original Orego\
> Orego is an ongoing, multi-year project to research and develop Java programs for playing the game of Go. It is 
supervised by Dr. Peter Drake of Lewis & Clark College in Portland, OR. For more information, including a list of 
Orego-related publications, go to the [Orego research page](https://sites.google.com/a/lclark.edu/drake/research/orego "Orego").


# Go engine features & terminology

## `Board` game board state class
In Orego, the board state of a GO game from the first stone placed to infinity is represented by the 
`edu.lclark.orego.core.Board` class. This class does the following:
- Tracks W/B Turn
- Tracks Num of passes made consecutively
- Check a given move and Play if valid then updates the board state after play like caputures, etc.

This however does not include other functionality for playing a game such as wining states, score calculations, undo moves,
or SGF recordings.


## `Player` & `PlayerBuilder` Engine class
The Player is the interface into Orego's engine. This class represents an instance of an Orego move generator. The 
player can generate moves for both Black and White. 

A player instance is initialized with its own instance of the board class. This board is used by the player to 
generate moves, and thus should not be modified directly by an outside class. 

Instead, when using the player class for move generation, plays should call the `player.play(...)` wrapper, not 
`player.getBoard().play(...)`

We use the builder class to generate an instance of player.


## Game class
The game class

