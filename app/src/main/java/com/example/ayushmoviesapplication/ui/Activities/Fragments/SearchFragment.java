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
import android.widget.Toast;

import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.Api.MovieClient;
import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.SearchedMoviePageListRepository;
import com.example.ayushmoviesapplication.databinding.FragmentSearchBinding;
import com.example.ayushmoviesapplication.ui.Activities.ViewModels.MainActivityViewModel;
import com.example.ayushmoviesapplication.ui.adapters.SearchAdapter;
import com.google.android.material.theme.MaterialComponentsViewInflater;

import kotlin.Suppress;


public class SearchFragment extends Fragment implements View.OnClickListener{

    private FragmentSearchBinding binding;
    private SearchAdapter adapter=new SearchAdapter(getContext());
    private MainActivityViewModel viewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentSearchBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel=getViewModel();
        viewModel.getSearchedMoviePagedList();

        viewModel.searchedMoviePagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<SearchedMovie>>() {
            @Override
            public void onChanged(PagedList<SearchedMovie> searchedMovies) {
                Log.i("111111", "onChanged:list changed ");
                adapter.submitList(searchedMovies);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setOnClickListener();

        adapter=new SearchAdapter(this.getContext());
        binding.searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchRecyclerView.setHasFixedSize(true);
        binding.searchRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.search_button:
            {
                callonClick();
            }
        }
    }

    private MainActivityViewModel getViewModel() {
        return new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    private void setOnClickListener()
    {
        binding.searchButton.setOnClickListener(this);
    }

    public void callonClick()
    {
        if(binding.searchEditText.getText().toString().length()>0)
        {
            viewModel.searchedMoviePageListRepository.getSearchedMovieDataSourceFactory().refresh(binding.searchEditText.getText().toString());
        }
        else
        {
            Toast.makeText(getActivity(), "Please Type Something to search", Toast.LENGTH_SHORT).show();
        }
    }
}