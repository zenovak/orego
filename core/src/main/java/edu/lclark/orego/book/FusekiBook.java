package edu.lclark.orego.book;

import java.io.ObjectInputStream;

import edu.lclark.orego.core.Board;
import edu.lclark.orego.core.CoordinateSystem;

/**
 * Produces moves from a book extracted from strong players' games.
 * 
 * @see FusekiBookBuilder
 */
public final class FusekiBook implements OpeningBook {

	/** The fuseki book proper. */
	private SmallHashMap book;

	/** Don't bother looking in the book after this many moves into the game. */
	private int maxMoves;

	public FusekiBook() {
		this("books");
	}

	/** Gets the hashMap out of the file. */
	@SuppressWarnings("boxing")
	public FusekiBook(String directory) {
		try (ObjectInputStream in = new ObjectInputStream(
			getClass().getResourceAsStream("/" + directory + "/" + "fuseki19.data")
		)) {
			maxMoves = (Integer) in.readObject();
			book = (SmallHashMap) in.readObject();
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public short nextMove(Board board) {
		final long fancyHash = board.getFancyHash();
		if (board.getTurn() < maxMoves) {
			if (book.containsKey(fancyHash)) {
				final short move = book.get(fancyHash);
				if (board.isLegal(move)) {
					return move;
				}
			}
		}
		return CoordinateSystem.NO_POINT;
	}
}
