package edu.lclark.orego.mcts;

import edu.lclark.orego.util.Logging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlayerWithOpeningTest {
    private Player player;

    @BeforeEach
    public void setUp() throws Exception {
        Logging.setLogger(message -> {
            System.out.println(message);
        });

        player = new PlayerBuilder().msecPerMove(100).threads(4).boardWidth(19).memorySize(64)
                .openingBook(true).build();
    }

    @Test
    public void testStart() {
        player.acceptMove(
                player.bestMove()
        );

        Logging.log(player.getBoard().toString());
    }
}
