package com.example.analisishasilpertanian.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.analisishasilpertanian.R;
import com.example.analisishasilpertanian.user.AboutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainAdminActivity extends AppCompatActivity {
    private Fragment analysis, information, about;
    private Fragment fragment = null;
    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.analysis:
                    fragment = analysis;
                    if (fragment==null){
                        analysis= new AnalysisFragment();
                        fragment=analysis;
                    }
                    break;
                case R.id.information:
                    fragment = information;
                    if (fragment==null){
                        information= new InformationFragment();
                        fragment=information;
                    }
                    break;
                case R.id.About:
                    fragment = about;
                    if (fragment==null){
                        about= new AboutFragment();
                        fragment=about;
                    }
                    break;
            }
            if (fragment!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.layout,fragment).commit();
            }
            return fragment!=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(listener);
        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.analysis);
        }
    }
}
