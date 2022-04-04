package com.example.imath.ui.ConvertirSegundos;

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

public class ConvertirSegundosFragment extends Fragment {

    private ConvertirSegundosViewModel mViewModel;

    private TextView tvResultado, tvTipo;
    private EditText txtSegundos;
    private Button btnGrados, btnMinutos, btnBorrar;
    private int segundos;
    private double grados, minutos;

    public static ConvertirSegundosFragment newInstance() {
        return new ConvertirSegundosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.convertir_segundos_fragment, container, false);
        View v = inflater.inflate(R.layout.convertir_segundos_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtSegundos = v.findViewById(R.id.id_segundos);

        // Se agregan automáticamente comas (,) cada mil
        txtSegundos.addTextChangedListener(new NumberTextWatcher(txtSegundos));

        // TextView
        tvResultado = v.findViewById(R.id.tvResultado);
        tvTipo = v.findViewById(R.id.tvTipo);

        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnGrados = v.findViewById(R.id.id_conv_grados);
        btnMinutos = v.findViewById(R.id.id_conv_min);

        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        btnGrados.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                if (txtSegundos.length() == 0)
                    txtSegundos.setError("Faltan los segundos");
                else {
                    segundos = Integer.parseInt(txtSegundos.getText().toString().replace(",", ""));
                    grados = (double) segundos / 3600;
                    // mostrarResultado.setText("CONVERSIÓN A MINUTOS: \n" + minutos + " \'");
                    if (grados >= 1000) {
                        decimalFormat[0] = new DecimalFormat("#0,000.0000");
                    } else {
                        decimalFormat[0] = new DecimalFormat("#0.0000");
                    }
                    tvTipo.setText("GRADOS");
                    tvResultado.setText(decimalFormat[0].format(grados) + "°");
                }
            }
        });

        btnMinutos.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                if (txtSegundos.length() == 0)
                    txtSegundos.setError("Faltan los segundos");
                else {
                    segundos = Integer.parseInt(txtSegundos.getText().toString().replace(",", ""));
                    minutos = (double) segundos / 60;
                    // mostrarResultado.setText("CONVERSIÓN A SEGUNDOS: \n" + segundos + " \"");
                    if (minutos >= 1000) {
                        decimalFormat[0] = new DecimalFormat("#0,000.0000");
                    } else {
                        decimalFormat[0] = new DecimalFormat("#0.0000");
                    }
                    tvTipo.setText("MINUTOS");
                    tvResultado.setText(decimalFormat[0].format(minutos) + "\'");
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtSegundos.setText("");
                tvResultado.setText("");
                tvTipo.setText("");
            }
        });
        
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirSegundosViewModel.class);
        // TODO: Use the ViewModel
    }

}