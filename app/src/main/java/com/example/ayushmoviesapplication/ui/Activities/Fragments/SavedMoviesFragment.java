package com.example.ayushmoviesapplication.ui.Activities.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;
import com.example.ayushmoviesapplication.databinding.FragmentSavedMoviesBinding;
import com.example.ayushmoviesapplication.ui.Activities.ViewModels.MainActivityViewModel;
import com.example.ayushmoviesapplication.ui.adapters.PopularMovieAdapter;
import com.example.ayushmoviesapplication.ui.adapters.SavedMovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class SavedMoviesFragment extends Fragment {
    private MainActivityViewModel viewModel;
    private SavedMovieAdapter adapter;
    private FragmentSavedMoviesBinding binding;

    public SavedMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentSavedMoviesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.savedMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.savedMovieRecyclerView.setHasFixedSize(true);
        viewModel=getViewModel();
        viewModel.getListOfSavedMovies(getContext().getApplicationContext(),getActivity());
        viewModel.savedMoviesLiveData.observe(getViewLifecycleOwner(), new Observer<List<MovieForRoom>>() {
            @Override
            public void onChanged(List<MovieForRoom> movieForRooms) {
                adapter=new SavedMovieAdapter(movieForRooms);
                binding.savedMovieRecyclerView.setAdapter(adapter);
                Log.i("11111", "onChanged: size of saved movies"+movieForRooms.size());
            }
        });

    }

    private MainActivityViewModel getViewModel() {
        return new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }
}