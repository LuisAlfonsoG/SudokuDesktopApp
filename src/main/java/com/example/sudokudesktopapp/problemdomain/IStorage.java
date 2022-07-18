package com.example.sudokudesktopapp.problemdomain;

import java.io.IOException;

public interface IStorage {
    void updateGameData(SudokuGame sudokuGame) throws IOException;
    SudokuGame getGameData() throws IOException;
}
