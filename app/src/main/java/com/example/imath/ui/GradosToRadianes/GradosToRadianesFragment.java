package com.example.imath.ui.GradosToRadianes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.imath.R;

import java.text.DecimalFormat;

public class GradosToRadianesFragment extends Fragment {

    private GradosToRadianesViewModel mViewModel;

    private TextView mostrarResultado;
    private EditText recibirDatos;
    private Button botonBorrar, botonConvertir;

    private double radianes;
    private double grados;

    public static GradosToRadianesFragment newInstance() {
        return new GradosToRadianesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.grados_to_radianes_fragment, container, false);
        View v = inflater.inflate(R.layout.grados_to_radianes_fragment, container, false);

        mostrarResultado = v.findViewById(R.id.tvResultado);
        recibirDatos = v.findViewById(R.id.id_grados);
        botonBorrar = v.findViewById(R.id.id_borrar);
        botonConvertir = v.findViewById(R.id.id_convertir);

        botonConvertir.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                DecimalFormat decimal = new DecimalFormat("#0.0000");
                if (recibirDatos.length() == 0)
                    recibirDatos.setError("Faltan datos");
                else {
                    grados = Double.parseDouble(recibirDatos.getText().toString().replace(",", ""));
                    radianes = (grados * Math.PI) / 180;
                    mostrarResultado.setText(decimal.format(radianes) + " rad");
                }
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                mostrarResultado.setText("");
                recibirDatos.setText("");

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GradosToRadianesViewModel.class);
        // TODO: Use the ViewModel
    }

}