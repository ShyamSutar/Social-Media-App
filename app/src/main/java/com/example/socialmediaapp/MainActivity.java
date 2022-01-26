package com.example.socialmediaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.socialmediaapp.Fragment.AddFragment;
import com.example.socialmediaapp.Fragment.NotificationFragment;
import com.example.socialmediaapp.Fragment.ProfileFragment;
import com.example.socialmediaapp.Fragment.SearchFragment;
import com.example.socialmediaapp.Fragment.homeFragment;
import com.example.socialmediaapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new homeFragment());
        transaction.commit();


        binding.readableBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){
                    case R.id.home:
                        transaction.replace(R.id.container,new homeFragment());
                        break;

                    case R.id.notification:
                        transaction.replace(R.id.container,new NotificationFragment());
                        break;

                    case R.id.add:
                        transaction.replace(R.id.container,new AddFragment());
                        break;

                    case R.id.search:
                        transaction.replace(R.id.container,new SearchFragment());
                        break;

                    case R.id.profile:
                        transaction.replace(R.id.container,new ProfileFragment());
                        break;
                }
                transaction.commit();
                return true;
            }
        });

    }
}