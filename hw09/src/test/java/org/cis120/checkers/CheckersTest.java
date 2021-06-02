package org.cis120.checkers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckersTest {
    private Checkers checkers;

    @BeforeEach
    public void setUp() {
        checkers = new Checkers();
    }

    @Test
    public void moveBlackTest() {
        assertTrue(checkers.getCell(0, 5).getIsPiece());
        assertEquals("Black", checkers.getCell(0, 5).getColor());
        assertTrue(checkers.moveBlack(0, 5, 1, 4));
        assertFalse(checkers.getCell(0, 6).getIsPiece());
        assertTrue(checkers.getCell(1, 4).getIsPiece());
        assertEquals("Black", checkers.getCell(1, 4).getColor());
    }

    @Test
    public void moveRedTest() {
        assertTrue(checkers.moveRed(1, 2, 0, 3));
        assertFalse(checkers.getCell(0, 3).getIsKing());
        assertFalse(checkers.getCell(1, 2).getIsPiece());
        assertTrue(checkers.getCell(0, 3).getIsPiece());
        assertEquals("Red", checkers.getCell(0, 3).getColor());
    }

    @Test
    public void captureBlackTest() {
        checkers.moveBlack(0, 5, 1, 4);
        checkers.moveRed(3, 2, 2, 3);
        assertTrue(checkers.moveBlack(1, 4, 2, 3));
        assertFalse(checkers.getCell(1, 4).getIsPiece());
        assertFalse(checkers.getCell(2, 3).getIsPiece());
        assertTrue(checkers.getCell(3, 2).getIsPiece());
        assertEquals("Black", checkers.getCell(3, 2).getColor());
    }

    @Test
    public void playTurnTest() {
        assertTrue(checkers.getCurrentPlayer());
        assertEquals(0, checkers.getNumTurns());
        assertTrue(checkers.playTurn(0, 5, 1, 4));
        assertEquals(1, checkers.getNumTurns());
        assertTrue(checkers.playTurn(3, 2, 2, 3));
        assertEquals(2, checkers.getNumTurns());
        assertTrue(checkers.playTurn(1, 4, 2, 3));
        assertEquals(3, checkers.getNumTurns());
        assertFalse(checkers.getCell(1, 4).getIsPiece());
        assertFalse(checkers.getCell(2, 3).getIsPiece());
        assertTrue(checkers.getCell(3, 2).getIsPiece());

        assertTrue(checkers.playTurn(4, 1, 3, 2));
        assertEquals(4, checkers.getNumTurns());
        assertFalse(checkers.getCell(4, 1).getIsPiece());
        assertFalse(checkers.getCell(3, 2).getIsPiece());
        assertTrue(checkers.getCell(2, 3).getIsPiece());
    }
}
