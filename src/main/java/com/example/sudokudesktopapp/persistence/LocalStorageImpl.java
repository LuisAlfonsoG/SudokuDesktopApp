package com.example.sudokudesktopapp.persistence;

import com.example.sudokudesktopapp.problemdomain.IStorage;
import com.example.sudokudesktopapp.problemdomain.SudokuGame;

import java.io.*;

public class LocalStorageImpl implements IStorage {
    private static final File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gamedata.txt"
    );

    @Override
    public void updateGameData(SudokuGame sudokuGame) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(sudokuGame);
            objectOutputStream.close();
        } catch (IOException e){
            throw new IOException("Unable to access Game Data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        try{
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        } catch (ClassNotFoundException e){
            throw new IOException("File not found");
        }
    }
}
