package com.example.sudokudesktopapp.computationlogic;

import java.util.Random;

import static com.example.sudokudesktopapp.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
    public static int[][] getNewGameGrid(){
        return unsolveGame(getSolvedGame());
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        return newArray;
    }

    private static int[][] unsolveGame(int[][] array){
        return array;
    }
}
