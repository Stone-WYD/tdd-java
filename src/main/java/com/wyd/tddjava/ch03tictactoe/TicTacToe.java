package com.wyd.tddjava.ch03tictactoe;

import com.wyd.tddjava.ch03tictactoe.mongo.TicTacToeBean;
import com.wyd.tddjava.ch03tictactoe.mongo.TicTacToeCollection;

import java.net.UnknownHostException;

public class TicTacToe {

    private Character[][] board = {{'\0','\0','\0'}, {'\0','\0','\0'},
            {'\0','\0','\0'}};

    private char nextPlayer = 'O';
    private char nowPlayer = 'X';
    private int putNum = 0;
    private TicTacToeCollection collection;

    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
    }

    public TicTacToe(TicTacToeCollection ticTacToeCollection) {
        this.collection = ticTacToeCollection;
    }

    public String play(int x, int y) {
        String actual = "No winner";

        checkXAxis(x);
        checkYAxis(y);
        setBox(x, y);
        // 保存信息到数据库
        TicTacToeBean bean = new TicTacToeBean(putNum+1, x, y, nowPlayer);
        collection.saveMove(bean);
        putNum ++;
        // 判断是否有人获胜
        if (isWin()) {
            actual = nowPlayer + " is the winner";
            if (putNum == 9) return actual;
        } else if (putNum == 9) return "The result is draw";
        changePlayer();
        return actual;
    }

    private boolean isWin() {

        int playTotal = nowPlayer * 3;
        for (int index = 0; index < 3; index++) {
            if (board[0][index] + board[1][index] + board[2][index] == playTotal) return true;
            if (board[index][0] + board[index][1] + board[index][2] == playTotal) return true;
        }
        if (board[0][0] + board[1][1] + board[2][2] == playTotal ) return true;
        if (board[0][2] + board[1][1] + board[2][0] == playTotal ) return true;
        return false;
    }

    public char nextPlayer() {
        return nextPlayer;
    }

    private void setBox(int x, int y) {
        if (board[x -1][y -1] != '\0' ){
            throw new RuntimeException("Box is occupied!");
        }
        board[x -1][y -1] = nowPlayer;
    }

    private static void checkYAxis(int y) {
        if (y <1 || y > 3){
            throw new RuntimeException("Y is outside board!");
        }
    }

    private static void checkXAxis(int x) {
        if (x < 1 || x > 3){
            throw new RuntimeException("X is outside board!");
        }
    }

    private void changePlayer() {
        char temp = nextPlayer;
        nextPlayer = nowPlayer;
        nowPlayer = temp;
    }

    public char nowPlayer() {
        return nowPlayer;
    }

    public TicTacToeCollection getCollection() {
        return collection;
    }

}
