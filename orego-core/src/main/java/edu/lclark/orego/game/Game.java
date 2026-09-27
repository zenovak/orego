package edu.lclark.orego.game;

import edu.lclark.orego.core.Board;
import edu.lclark.orego.core.Legality;
import edu.lclark.orego.mcts.Player;

/**
 * an Instance of a game of Go.
 */
public class Game {

    private Board board;
    private Player player;

    public Game() {}

    public Game addMoveGenerator(Player player) {
        this.player = player;
        this.board = player.getBoard();
        return this;
    }

    public Legality playSync(String move) {
        if (this.player != null) {
            return player.acceptMove(board.getCoordinateSystem().at(move));
        }

        return board.play(move);
    }

    public Short suggestMoveSync() {
        if (player != null) {
            return player.bestMove();
        }
        return null;
    }

    public boolean checkWin() {
        if (board.getPasses() == 2) {
            return true;
        }

        return false;
    }
}
