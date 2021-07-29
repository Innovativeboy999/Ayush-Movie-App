package com.example.ayushmoviesapplication.ui.Activities.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.Api.MovieClient;
import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.PopularMoviePageListRepository;
import com.example.ayushmoviesapplication.data.repository.SearchedMoviePageListRepository;
import com.example.ayushmoviesapplication.databinding.FragmentPopularBinding;
import com.example.ayushmoviesapplication.ui.Activities.ViewModels.MainActivityViewModel;
import com.example.ayushmoviesapplication.ui.adapters.PopularMovieAdapter;
import com.example.ayushmoviesapplication.ui.adapters.SearchAdapter;

public class PopularFragment extends Fragment {
    private FragmentPopularBinding binding;
    private MainActivityViewModel viewModel;
    private PopularMovieAdapter adapter;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentPopularBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter=new PopularMovieAdapter(this.getContext());
        binding.popularMovieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.popularMovieRecyclerView.setHasFixedSize(true);
        binding.popularMovieRecyclerView.setAdapter(adapter);

        viewModel=getViewModel();
        viewModel.getPopularMoviePagedList();
        viewModel.popularMoviePagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                Log.i("111111", "onChanged:list changed ");
                adapter.submitList(movies);
            }
        });
    }


    private MainActivityViewModel getViewModel() {
//        return new ViewModelProvider(getActivity(), new ViewModelProvider.Factory() {
//            @NonNull
//            @Override
//            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//                MainActivityViewModel temp=new MainActivityViewModel(popularMoviePageListRepository);
//                return (T) temp;
//            }
//        }).get(MainActivityViewModel.class);
        return new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }
}