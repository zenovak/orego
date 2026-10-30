package edu.lclark.orego.core;

/** Type returned by Board.play indicating if a move is legal or, if not, why. */
public enum Legality {

	/**
	 * While the move is technically legal, it would cause the playout to be too
	 * long.
	 */
	GAME_TOO_LONG,

	/**
	 * While technically legal, the game has already ended. Either by passing or someone resigned
	 */
	GAME_OVER,

	/**
	 * Used for downstream classes if checking for turns or plays needed a turn.
	 */
	WRONG_TURN,

	/** Simple ko or superko would be violated. */
	KO_VIOLATION,

	/** The point is occupied. */
	OCCUPIED,

	/** The move is legal. */
	OK,

	/** The move is suicide. */
	SUICIDE;

}
