package com.example.imath.ui.GradosToGMS;

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

public class GradosToGMSFragment extends Fragment {

    private GradosToGMViewModel mViewModel;

    Button btnCalcular, btnBorrar;
    TextView tvResultado;
    EditText txtGrados;

    int grados, totalMinutos, totalSegundos;
    double calcularMinutos, calcularSegundos, residuo, gradosRecibidos, residuoMinutos;
    String strGrados, strMinutos, strSegundos;

    public static GradosToGMSFragment newInstance() {
        return new GradosToGMSFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.grados_to_g_m_fragment, container, false);
        View v = inflater.inflate(R.layout.grados_to_g_m_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtGrados = v.findViewById(R.id.id_grados);

        // Se agregan automáticamente comas (,) cada mil
        //txtGrados.addTextChangedListener(new NumberTextWatcher(txtGrados));

        // TextView
        tvResultado = v.findViewById(R.id.tvResultado);

        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnCalcular = v.findViewById(R.id.id_convertir);

        btnCalcular.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                if (txtGrados.length() == 0) { gradosRecibidos = 0; } else { gradosRecibidos = Double.parseDouble(txtGrados.getText().toString().replace(",", "")); }

                strGrados = String.valueOf(gradosRecibidos);

                grados = Integer.parseInt(strGrados.substring(0, strGrados.indexOf('.')));
                residuo = Double.parseDouble(strGrados.substring(strGrados.indexOf('.')));

                calcularMinutos = residuo * 60;
                strMinutos = String.valueOf(calcularMinutos);
                totalMinutos = Integer.parseInt(strMinutos.substring(0, strMinutos.indexOf('.')));
                residuoMinutos = Double.parseDouble(strMinutos.substring(strMinutos.indexOf('.')));

                calcularSegundos = residuoMinutos * 60;
                strSegundos = String.valueOf(calcularSegundos);
                totalSegundos = Integer.parseInt(strSegundos.substring(0, strSegundos.indexOf('.')));

                tvResultado.setText(grados + " °  " + totalMinutos + " \'  " + totalSegundos + " \"");

            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                tvResultado.setText("");
                txtGrados.setText("");
            }
        });
        
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GradosToGMViewModel.class);
        // TODO: Use the ViewModel
    }

}