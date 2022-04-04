package com.example.imath.ui.help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.imath.R;
import com.example.imath.Versions;
import com.example.imath.ui.VersionsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Help extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        FloatingActionButton fab = findViewById(R.id.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        initData();
        setRecyclerView();

    }

    private void setRecyclerView() {

        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);

    }

    private void initData() {

        versionsList = new ArrayList<>();

        // Add required information
        versionsList.add(new Versions("¿Es necesario una cuenta para utilizar la aplicación?",
                "No, la aplicación no guarda registro de usuarios por lo que cualquier persona puede utilizarla."));
        versionsList.add(new Versions("¿La aplicación guarda algún historial de operaciones?",
                "No, iMath no guarda ningún registro de actividad."));
        versionsList.add(new Versions("¿Para quién está dirigida la aplicación?",
                "iMath está dirigida para estudiantes de secundaria y preparatoria, aunque también puede ser " +
                        "utilizada por cualquier otro tipo de estudiante o padre de familia que la requiera."));


    }
}