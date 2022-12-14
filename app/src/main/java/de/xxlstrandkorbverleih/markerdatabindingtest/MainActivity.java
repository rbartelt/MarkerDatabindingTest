package de.xxlstrandkorbverleih.markerdatabindingtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();                         //get NavController

        //set TopLevelDestinations (wich dont show the Backbutton in Actionbar)
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.mapFragment);
        //find DrawerLayout
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        //setup AppBarConfiguration
        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestinations).setOpenableLayout(drawerLayout).build();

        Toolbar toolbar = findViewById(R.id.toolbar);                               //find Toolbar
        setSupportActionBar(toolbar);                                               //set Toolbar as Actionbar in Mainactivity
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);   //connects the Toolbar with Navgraph

        //Connect Drawer with Navgraph
        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Ask for Permissions
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);


        //Request GPS Permission
        ActivityCompat.requestPermissions(
                this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0
        );


    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

}