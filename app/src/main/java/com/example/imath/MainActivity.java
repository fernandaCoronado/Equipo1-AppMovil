package com.example.imath;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.imath.ui.help.Help;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.imath.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_triangle, R.id.nav_square, R.id.nav_rectangle,
                R.id.nav_circle, R.id.nav_cubo, R.id.nav_cilindro, R.id.nav_esfera,
                R.id.nav_calcular_hipotenusa, R.id.nav_calcular_cateto_a, R.id.nav_calcular_cateto_b,
                R.id.nav_suma_angulos, R.id.nav_resta_angulos, R.id.nav_multiplicacion_angulos,
                R.id.nav_dividir_angulos, R.id.nav_convertir_grados, R.id.nav_convertir_minutos,
                R.id.nav_convertir_segundos, R.id.nav_convertir_combinados, R.id.nav_grados_to_gms,
                R.id.nav_grados_to_radianes, R.id.nav_gms_to_radianes, R.id.nav_help)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                // User chose the "Settings" item, show the app settings UI...
                // Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
                // toast1.show();
                Intent intent = new Intent(this, Help.class);
                startActivity(intent);
                return false;

            /**
             * Intent intent = new Intent(getActivity(), mFragmentFavorite.class);
             * startActivity(intent);
             */

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}