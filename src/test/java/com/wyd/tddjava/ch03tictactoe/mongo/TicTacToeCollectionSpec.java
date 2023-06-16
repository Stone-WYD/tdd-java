package com.wyd.tddjava.ch03tictactoe.mongo;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;

import static org.mockito.Mockito.*;

public class TicTacToeCollectionSpec{

    TicTacToeCollection ticTacToeCollection;
    MongoCollection mongoCollection;
    TicTacToeBean bean;

    @BeforeEach
    public void before() throws UnknownHostException{
        ticTacToeCollection = spy(new TicTacToeCollection()) ;
        mongoCollection = mock(MongoCollection.class);
        bean = new TicTacToeBean(3, 2, 1, 'Y');
    }

    @Test
    public void whenInstantiatedThenMongoHasDbNameTicTacToe() throws UnknownHostException {
        // TicTacToeCollection ticTacToeCollection = new TicTacToeCollection();
        Assertions.assertEquals("tic-tac-toe", ticTacToeCollection.getMongoCollection()
                .getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() throws UnknownHostException {
        // TicTacToeCollection ticTacToeCollection = new TicTacToeCollection();
        Assertions.assertEquals("game",
                ticTacToeCollection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave(){
//        TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
//        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        ticTacToeCollection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue(){
//        TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
//        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(ticTacToeCollection).getMongoCollection();
        Assertions.assertTrue(ticTacToeCollection.saveMove(bean));
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse(){
        doThrow(new MongoException("Bla"))
                .when(mongoCollection).save(any(TicTacToeBean.class));
        doReturn(mongoCollection)
                .when(ticTacToeCollection).getMongoCollection();
        Assertions.assertFalse(ticTacToeCollection.saveMove(new TicTacToeBean()));
    }


}
