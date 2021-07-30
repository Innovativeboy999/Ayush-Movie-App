package com.example.ayushmoviesapplication.data.Utils.interfaces.forRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    Long insertTask(MovieForRoom movie);

    @Delete
    void deleteTask(MovieForRoom movie);


    @Query("SELECT * FROM MovieForRoom WHERE id =:taskId")
    LiveData<MovieForRoom> getTask(int taskId);

    @Query("SELECT * FROM MovieForRoom ")
    LiveData<List<MovieForRoom>> fetchAllTasks();
}
