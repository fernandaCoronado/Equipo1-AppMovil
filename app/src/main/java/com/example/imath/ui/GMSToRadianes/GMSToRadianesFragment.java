package com.example.imath.ui.GMSToRadianes;

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

import com.example.imath.NumberTextWatcher;
import com.example.imath.R;

import java.text.DecimalFormat;

public class GMSToRadianesFragment extends Fragment {

    private GMSToRadianesViewModel mViewModel;

    TextView mostrarResultado;
    Button botonConvertir, botonBorrar;
    EditText recibirGrados, recibirMinutos, recibirSegundos;

    int grados = 0, minutos = 0, segundos = 0;
    double radianes;

    public static GMSToRadianesFragment newInstance() {
        return new GMSToRadianesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.g_m_s_to_radianes_fragment, container, false);
        View v = inflater.inflate(R.layout.g_m_s_to_radianes_fragment, container, false);

        mostrarResultado = v.findViewById(R.id.tvResultado);

        recibirGrados = v.findViewById(R.id.id_grados);
        recibirMinutos = v.findViewById(R.id.id_minutos);
        recibirSegundos = v.findViewById(R.id.id_segundos);

        // Se agregan automáticamente comas (,) cada mil
        recibirGrados.addTextChangedListener(new NumberTextWatcher(recibirGrados));
        recibirMinutos.addTextChangedListener(new NumberTextWatcher(recibirMinutos));
        recibirSegundos.addTextChangedListener(new NumberTextWatcher(recibirSegundos));

        botonConvertir = v.findViewById(R.id.id_convertir);
        botonBorrar = v.findViewById(R.id.id_borrar);

        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        //BORRAR
        botonBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                recibirGrados.setText("");
                recibirMinutos.setText("");
                recibirSegundos.setText("");
                mostrarResultado.setText("");
            }
        });

        botonConvertir.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                // GRADOS
                double calcularMinutos, calcularSegundos, resultadoGrados;

                if (recibirGrados.length() == 0) { grados = 0; } else { grados = Integer.parseInt(recibirGrados.getText().toString().replace(",", "")); }
                if (recibirMinutos.length() == 0) { minutos = 0; } else { minutos = Integer.parseInt(recibirMinutos.getText().toString().replace(",", "")); }
                if (recibirSegundos.length() == 0) { segundos = 0; } else { segundos = Integer.parseInt(recibirSegundos.getText().toString().replace(",", "")); }

                calcularMinutos = (double) minutos / 60;
                calcularSegundos = (double) segundos / 3600;

                resultadoGrados = grados + calcularMinutos + calcularSegundos;

                radianes = (resultadoGrados * Math.PI) / 180;

                if (radianes >= 1000) {
                    decimalFormat[0] = new DecimalFormat("#0,000.0000");
                } else {
                    decimalFormat[0] = new DecimalFormat("#0.0000");
                }
                mostrarResultado.setText(decimalFormat[0].format(radianes) + " rad");

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GMSToRadianesViewModel.class);
        // TODO: Use the ViewModel
    }

}