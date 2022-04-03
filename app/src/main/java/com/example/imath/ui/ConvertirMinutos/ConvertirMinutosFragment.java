package com.example.imath.ui.ConvertirMinutos;

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

public class ConvertirMinutosFragment extends Fragment {

    private ConvertirMinutosViewModel mViewModel;

    private TextView tvResultado, tvTipo;
    private EditText txtMinutos;
    private Button btnGrados, btnSegundos, btnBorrar;
    private int minutos, segundos;
    private double grados;

    public static ConvertirMinutosFragment newInstance() {
        return new ConvertirMinutosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.convertir_minutos_fragment, container, false);
        View v = inflater.inflate(R.layout.convertir_minutos_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtMinutos = v.findViewById(R.id.id_minutos);

        // Se agregan automáticamente comas (,) cada mil
        txtMinutos.addTextChangedListener(new NumberTextWatcher(txtMinutos));

        // TextView
        tvResultado = v.findViewById(R.id.tvResultado);
        tvTipo = v.findViewById(R.id.tvTipo);

        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnGrados = v.findViewById(R.id.id_conv_grados);
        btnSegundos = v.findViewById(R.id.id_conv_seg);

        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        btnGrados.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                if (txtMinutos.length() == 0)
                    txtMinutos.setError("Faltan datos");
                else {
                    minutos = Integer.parseInt(txtMinutos.getText().toString().replace(",", ""));
                    grados = (double) minutos / 60;
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

        btnSegundos.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                if (txtMinutos.length() == 0)
                    txtMinutos.setError("Faltan datos");
                else {
                    minutos = Integer.parseInt(txtMinutos.getText().toString().replace(",", ""));
                    segundos = minutos * 60;
                    // mostrarResultado.setText("CONVERSIÓN A SEGUNDOS: \n" + segundos + " \"");
                    if (segundos >= 1000) {
                        decimalFormat[0] = new DecimalFormat("#0,000");
                    } else {
                        decimalFormat[0] = new DecimalFormat("#0");
                    }
                    tvTipo.setText("SEGUNDOS");
                    tvResultado.setText(decimalFormat[0].format(segundos) + "\"");
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtMinutos.setText("");
                tvResultado.setText("");
                tvTipo.setText("");
            }
        });
        
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirMinutosViewModel.class);
        // TODO: Use the ViewModel
    }

}