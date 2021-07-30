package com.example.ayushmoviesapplication.data.repository.RoomRepository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.ayushmoviesapplication.data.Utils.database.MovieDatabase;
import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;

import java.util.List;

public class RoomMoviesRepository {
    private String DB_NAME = "db_task";
    private MovieDatabase movieDatabase;
    public RoomMoviesRepository(Context context)
    {
        movieDatabase= Room.databaseBuilder(context,MovieDatabase.class,DB_NAME).build();
    }


    public void insertTask(int id,
                           String movie_title,
                           float rating) {

        MovieForRoom movie = new MovieForRoom();
        movie.setId(id);
        movie.setMovie_title(movie_title);
        movie.setRating(rating);

        insertTask(movie);
    }

    public void insertTask(final MovieForRoom movie) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                movieDatabase.daoAccess().insertTask(movie);
                return null;
            }
        }.execute();
    }

    public void deleteTask(final int id) {
        final LiveData<MovieForRoom> task = getTask(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    movieDatabase.daoAccess().deleteTask(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteTask(final MovieForRoom movie) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                movieDatabase.daoAccess().deleteTask(movie);
                return null;
            }
        }.execute();
    }

    public LiveData<MovieForRoom> getTask(int id) {
        return movieDatabase.daoAccess().getTask(id);
    }

    public LiveData<List<MovieForRoom>> getTasks() {
        return movieDatabase.daoAccess().fetchAllTasks();
    }
}
