package com.example.ayushmoviesapplication.ui.Activities.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.databinding.FragmentSearchBinding;
import com.example.ayushmoviesapplication.ui.Activities.ViewModels.MainActivityViewModel;
import com.example.ayushmoviesapplication.ui.adapters.SearchAdapter;


public class SearchFragment extends Fragment {

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
        searchViewInitialization();

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
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        adapter=new SearchAdapter(this.getContext());
        binding.searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchRecyclerView.setHasFixedSize(true);
        binding.searchRecyclerView.setAdapter(adapter);
    }

    private MainActivityViewModel getViewModel() {
        return new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    private void searchViewInitialization()
    {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()>0)
                {
                    viewModel.repository.getSearchedMovieDataSourceFactory().refresh(query);

                }
                else
                {
                    Toast.makeText(getActivity(), "Please Type Something to search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

}