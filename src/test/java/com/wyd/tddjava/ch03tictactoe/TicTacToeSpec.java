package com.wyd.tddjava.ch03tictactoe;


import com.wyd.tddjava.ch03tictactoe.mongo.TicTacToeBean;
import com.wyd.tddjava.ch03tictactoe.mongo.TicTacToeCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TicTacToeSpec {

    private TicTacToe ticTacToe;

    TicTacToeCollection collection;

    @BeforeEach
    public final void before() throws UnknownHostException {
        collection = mock(TicTacToeCollection.class);
        ticTacToe = new TicTacToe(collection);
    }

    @Test
    public void whenXoutsideBoardThenRuntimeException(){
        Assertions.assertThrows( RuntimeException.class, ()-> ticTacToe.play(7, 2));
    }

    @Test
    public void whenYoutsideBoardThenRuntimeException(){
        Assertions.assertThrows( RuntimeException.class, ()-> ticTacToe.play(2, 7));
    }

    @Test
    public void whenOccupiedThenRuntimeException(){
        ticTacToe.play(2,1);
        Assertions.assertThrows( RuntimeException.class, ()-> ticTacToe.play(2, 1));
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX(){
        Assertions.assertEquals('X', ticTacToe.nowPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO(){
        ticTacToe.play(1,1);
        Assertions.assertEquals('O', ticTacToe.nowPlayer());
    }

    @Test
    public void whenPlayThenNoWinner(){
        String actual = ticTacToe.play(1,1);
        Assertions.assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,1);
        ticTacToe.play(2,2);
        String actual = ticTacToe.play(3, 1);
        Assertions.assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner(){
        ticTacToe.play(2,1);
        ticTacToe.play(1,1);
        ticTacToe.play(3,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        String actual = ticTacToe.play(1,3);
        Assertions.assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPalyAndTopBottomDiagonalLineThenWinner(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        ticTacToe.play(1,3);
        String actual = ticTacToe.play(3,3);
        Assertions.assertEquals("X is the winner", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw(){
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        Assertions.assertEquals("The result is draw", actual);
    }

    @Test
    public void whenInstantiatedThenSetCollection(){
        Assertions.assertNotNull(ticTacToe.getCollection());
    }

    @Test
    public void whenPlayThenSaveMoveIsInvoked(){
        TicTacToeBean move = new TicTacToeBean(1, 1, 3, 'X');
        ticTacToe.play(move.getX(), move.getY());
        verify(collection).saveMove(move);
    }

    /*@Rule
    public ExpectedException expectedException = ExpectedException.none();
    private TicTacToe ticTacToe;

    @Before
    public final void before(){
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXoutsideBoardThenRuntimeException(){
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(7, 2);
    }

    @Test
    public void whenYoutsideBoardThenRuntimeException(){
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(2, 7);
    }

    @Test
    public void whenOccupiedThenRuntimeException(){
        ticTacToe.play(2,1);
        expectedException.expect(RuntimeException.class);
        ticTacToe.play(2,1);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX(){
        assertEquals('X', ticTacToe.nowPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO(){
        ticTacToe.play(1,1);
        assertEquals('O', ticTacToe.nowPlayer());
    }

    @Test
    public void whenPlayThenNoWinner(){
        String actual = ticTacToe.play(1,1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,1);
        ticTacToe.play(2,2);
        String actual = ticTacToe.play(3, 1);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner(){
        ticTacToe.play(2,1);
        ticTacToe.play(1,1);
        ticTacToe.play(3,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        String actual = ticTacToe.play(1,3);
        assertEquals("O is the winner", actual);
    }

    @Test
    public void whenPalyAndTopBottomDiagonalLineThenWinner(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        ticTacToe.play(1,3);
        String actual = ticTacToe.play(3,3);
        assertEquals("X is the winner", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw(){
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);
    }*/

}
