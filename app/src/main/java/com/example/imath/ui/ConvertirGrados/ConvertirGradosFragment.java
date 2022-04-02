package com.example.imath.ui.ConvertirGrados;

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

public class ConvertirGradosFragment extends Fragment {

    private ConvertirGradosViewModel mViewModel;

    private TextView tvResultado, tvTipo;
    private EditText txtGrados;
    private Button btnMinutos, btnSegundos, btnBorrar;
    private int grados, minutos, segundos;

    public static ConvertirGradosFragment newInstance() {
        return new ConvertirGradosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.convertir_grados_fragment, container, false);
        View v = inflater.inflate(R.layout.convertir_grados_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtGrados = v.findViewById(R.id.id_grados);

        // Se agregan automáticamente comas (,) cada mil
        txtGrados.addTextChangedListener(new NumberTextWatcher(txtGrados));

        // TextView
        tvResultado = v.findViewById(R.id.tvResultado);
        tvTipo = v.findViewById(R.id.tvTipo);

        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnMinutos = v.findViewById(R.id.id_conv_min);
        btnSegundos = v.findViewById(R.id.id_conv_seg);

        btnMinutos.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                if (txtGrados.length() == 0)
                    txtGrados.setError("Faltan datos");
                else {
                    grados = Integer.parseInt(txtGrados.getText().toString());
                    minutos = grados * 60;
                    // mostrarResultado.setText("CONVERSIÓN A MINUTOS: \n" + minutos + " \'");
                    tvTipo.setText("MINUTOS");
                    tvResultado.setText(minutos + "\'");
                }
            }
        });

        btnSegundos.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                if (txtGrados.length() == 0)
                    txtGrados.setError("Faltan datos");
                else {
                    grados = Integer.parseInt(txtGrados.getText().toString().replace(",", ""));
                    segundos = grados * 3600;
                    // mostrarResultado.setText("CONVERSIÓN A SEGUNDOS: \n" + segundos + " \"");
                    tvTipo.setText("SEGUNDOS");
                    tvResultado.setText(segundos + "\"");
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtGrados.setText("");
                tvResultado.setText("");
                tvTipo.setText("");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirGradosViewModel.class);
        // TODO: Use the ViewModel
    }

}