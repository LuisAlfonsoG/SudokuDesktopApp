package com.example.sudokudesktopapp.computationlogic;

import com.example.sudokudesktopapp.problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.sudokudesktopapp.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
    public static int[][] getNewGameGrid(){
        return unsolveGame(getSolvedGame());
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        for( int value = 1; value <= GRID_BOUNDARY; value++){
            int allocations = 0;
            int interrupt = 0;

            List<Coordinates> allocTracker = new ArrayList<>();

            int attempts = 0;

            while (allocations < GRID_BOUNDARY){
                if( interrupt > 200){
                    allocTracker.forEach(coor -> {
                       newGrid[coor.getX()][coor.getY()] = 0;
                    });

                    allocations = 0;
                    interrupt = 0;

                    allocTracker.clear();
                    attempts++;

                    if(attempts > 500){
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if(newGrid[xCoordinate][yCoordinate] == 0){
                    newGrid[xCoordinate][yCoordinate] = value;

                    if(GameLogic.sudokuIsInvalid(newGrid)){
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    } else {
                        allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
                        allocations++;
                    }
                }
            }
        }
        return newGrid;
    }

    private static void clearArray(int[][] newGrid) {
        for(int x = 0; x < GRID_BOUNDARY; x++){
            for( int y = 0; y < GRID_BOUNDARY; y++){
                newGrid[x][y] = 0;
            }
        }
    }

    private static int[][] unsolveGame(int[][] solvedArray){
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvable == false){
            SudokuUtilities.copySudokuArrayValues(solvedArray, solvableArray);
            int index = 0;
            while (index < 40){
                int xCoor = random.nextInt(GRID_BOUNDARY);
                int yCoor = random.nextInt(GRID_BOUNDARY);

                if (solvableArray[xCoor][yCoor] != 0){
                    solvableArray[xCoor][yCoor] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);

            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

        }
        return solvableArray;
    }
}
