package com.example.sudokudesktopapp.buildlogic;

import com.example.sudokudesktopapp.computationlogic.GameLogic;
import com.example.sudokudesktopapp.persistence.LocalStorageImpl;
import com.example.sudokudesktopapp.problemdomain.IStorage;
import com.example.sudokudesktopapp.problemdomain.SudokuGame;
import com.example.sudokudesktopapp.userinterface.IUserInterfaceContract;
import com.example.sudokudesktopapp.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        } catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

       IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

       userInterface.setListener(uiLogic);
       userInterface.updateBoard(initialState);
    }
}
