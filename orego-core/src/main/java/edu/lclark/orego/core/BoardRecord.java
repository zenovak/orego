package edu.lclark.orego.core;

/**
 * An immutable representation of all the pieces on the board
 * @param coords the coordinate instance associated with this board's width
 * @param boardSet the pieces within the board.
 */
public record BoardRecord(CoordinateSystem coords, Color[] boardSet) {

    public Color getColorAt(int row, int col) {
        return boardSet[coords.at(row, col)];
    }

    public int getWidth() {
        return boardSet.length;
    }
}
