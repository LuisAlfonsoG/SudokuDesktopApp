package com.example.sudokudesktopapp.computationlogic;

import com.example.sudokudesktopapp.constants.GameState;
import com.example.sudokudesktopapp.problemdomain.SudokuGame;

public class GameLogic {
    public static GameState checkForCompletion(int[][] arr){
        return GameState.COMPLETE;
    }

    public static SudokuGame getNewGame(){
        return new SudokuGame(GameState.NEW ,new int[9][9]);
    }
}
