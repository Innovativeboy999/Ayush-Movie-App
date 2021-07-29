package com.example.ayushmoviesapplication.ui.Activities.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.databinding.FragmentNowPlayingBinding;
import com.example.ayushmoviesapplication.ui.Activities.ViewModels.MainActivityViewModel;
import com.example.ayushmoviesapplication.ui.adapters.NowPlayingAdapter;

public class NowPlayingFragment extends Fragment
{
    private FragmentNowPlayingBinding binding;
    private MainActivityViewModel viewModel;
    private NowPlayingAdapter adapter;;


    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentNowPlayingBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new NowPlayingAdapter(this.getContext());
        binding.nowPlayingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.nowPlayingRecyclerView.setHasFixedSize(true);
        binding.nowPlayingRecyclerView.setAdapter(adapter);

        viewModel=getViewModel();
        viewModel.getNowPlayingPagedList();
        viewModel.nowPlayingPagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                Log.i("111111", "onChanged:list changed ");
                adapter.submitList(movies);
            }
        });
    }

    private MainActivityViewModel getViewModel() {
        return new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }
}