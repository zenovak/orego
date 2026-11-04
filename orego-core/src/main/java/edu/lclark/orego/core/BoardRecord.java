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
        return coords.getWidth();
    }

    public int[] toColorPointSet() {
        var pointSet = new int[boardSet.length];
        for (int i = 0; i < boardSet.length; i++) {
            pointSet[i] = boardSet[i].index();
        }
        return pointSet;
    }

    public static BoardRecord fromColorPointSet(int boardSize, int[] colorPointSet) {
        var colorArr = new Color[colorPointSet.length];

        for (int i = 0; i < colorPointSet.length; i++) {
            colorArr[i] = fromIndex(colorPointSet[i]);
        }
        return new BoardRecord(CoordinateSystem.forWidth(boardSize), colorArr);
    }

    private static Color fromIndex(int index) {
        switch (index) {
            case 0:
                return StoneColor.BLACK;
            case 1:
                return StoneColor.WHITE;
            case 2:
                return NonStoneColor.VACANT;
            case 3:
                return NonStoneColor.OFF_BOARD;
        }
        return null;
    }
}
