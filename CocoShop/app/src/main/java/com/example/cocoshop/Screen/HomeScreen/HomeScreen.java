package com.example.cocoshop.Screen.HomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cocoshop.R;
import com.example.cocoshop.ui.Learnbytopic.FragmentLearningTopic;
import com.example.cocoshop.ui.Progressivelearning.FragmentProgressive;
import com.example.cocoshop.ui.Audio.FragmentAudio;
import com.example.cocoshop.ui.Profile.FragmentProfile;
import com.example.cocoshop.ui.Home.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    //private ActionBar tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigatorView);
        bottomNavigationView.setItemIconTintList(null);
        //tabBar = getSupportActionBar();
        //tabBar.setTitle("Home");
        bottomNavigationView.setOnNavigationItemSelectedListener(new onNavigatoritemSelectListener());
        FragmentHome home = new FragmentHome();
        loadFragment(home);
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }

    class onNavigatoritemSelectListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.itemProgressive:
                    if(!(FragmentProgressive.class.isInstance(fragment))){
                        fragment = new FragmentProgressive();
                        loadFragment(fragment);
                        return true;
                    }
                    return false;
                case R.id.itemProfile:
                    if(!(FragmentProfile.class.isInstance(fragment))){
                        fragment = new FragmentProfile();
                        //tabBar.setTitle("Profile");
                        loadFragment(fragment);
                        return true;
                    }
                    return false;
                case R.id.itemHome:
                    //tabBar.setTitle("Home");
                    loadFragment(new FragmentHome());
                    return true;
                case R.id.itemLearnigTopic:
                    //tabBar.setTitle("Learning by topic");
                    loadFragment(new FragmentLearningTopic());
                    return true;
                case R.id.itemAudio:
                    //tabBar.setTitle("Audio");
                    loadFragment(new FragmentAudio());
                    return true;
            }
            return false;
        }
    }

}