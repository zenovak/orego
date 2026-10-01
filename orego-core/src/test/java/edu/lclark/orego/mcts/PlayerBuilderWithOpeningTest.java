package edu.lclark.orego.mcts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerBuilderWithOpeningTest {

    private PlayerBuilder builder;

    @BeforeEach
    public void setup() {
        builder = new PlayerBuilder().openingBook(true);
    }

    @Test
    public void testBoardSize() {
        builder.boardWidth(9);
        assertEquals(9, builder.build().getBoard().getCoordinateSystem().getWidth());
        builder.boardWidth(19);
        assertEquals(19, builder.build().getBoard().getCoordinateSystem().getWidth());
    }
}
