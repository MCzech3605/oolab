package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {
    @Test
    void testCorrectConversion()
    {
        String[] words = {"f", "forward", "b", "backward", "r", "right", "l", "left"};
        String[] words2 = {"f", "forward", "b", "backward", "r", "right", "l", "left", "lef", "for", "back"};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
        MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        OptionsParser parser1 = new OptionsParser();
        MoveDirection[] parsedMoves = parser1.parse(words);
        for (int i=0; i<8; i++)
            Assertions.assertEquals(moves[i], parsedMoves[i]);
        Assertions.assertEquals(moves.length, parsedMoves.length);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           parser1.parse(words2);
        });
    }
}
