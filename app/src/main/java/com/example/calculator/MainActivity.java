package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.calculator:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new calculatorFragment()).commit();
                        break;
                    case R.id.currency:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new currencyFragment()).commit();
                        break;
                    case R.id.unit:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new unitFragment()).commit();
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new calculatorFragment()).commit();
    }
}