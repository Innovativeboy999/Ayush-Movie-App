package com.example.ayushmoviesapplication.ui.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.databinding.ActivityMainBinding;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.NowPlayingFragment;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.PopularFragment;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.SearchFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    private ActivityMainBinding binding;
    private FragmentManager manager=getSupportFragmentManager();
    private NowPlayingFragment fragmentPlaying=new NowPlayingFragment();;
    private PopularFragment fragmentPopular=new PopularFragment();
    private SearchFragment fragmentSearch=new SearchFragment();
    private Fragment active =fragmentPopular;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager.beginTransaction().add(R.id.holderContainer,fragmentPlaying,"2").hide(fragmentPlaying).commit();
        manager.beginTransaction().add(R.id.holderContainer,fragmentSearch,"3").hide(fragmentSearch).commit();
        manager.beginTransaction().add(R.id.holderContainer,fragmentPopular,"1").commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        binding.bottomNavigationView.setOnItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.popularMovie:
            {
                manager.beginTransaction().hide(active).show(fragmentPopular).commit();
                active=fragmentPopular;
                return true;
            }
            case R.id.playing:
            {
                manager.beginTransaction().hide(active).show(fragmentPlaying).commit();
                active=fragmentPlaying;
                return true;
            }
            case R.id.search:
            {
                manager.beginTransaction().hide(active).show(fragmentSearch).commit();
                active=fragmentSearch;
                return true;
            }

        }
        return false;
    }

}