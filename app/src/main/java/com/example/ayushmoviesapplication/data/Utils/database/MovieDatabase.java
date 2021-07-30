package com.example.ayushmoviesapplication.data.Utils.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ayushmoviesapplication.data.Utils.interfaces.forRoom.DaoAccess;
import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;

@Database(entities = {MovieForRoom.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
