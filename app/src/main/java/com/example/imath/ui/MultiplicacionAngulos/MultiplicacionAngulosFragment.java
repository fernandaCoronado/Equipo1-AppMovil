package com.example.imath.ui.MultiplicacionAngulos;

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

public class MultiplicacionAngulosFragment extends Fragment {

    private MultiplicacionAngulosViewModel mViewModel;

    TextView tvGrados, tvMinutos, tvSegundos;
    EditText txtGrados, txtMinutos, txtSegundos, txtMultiplicador;
    Button btnCalcular, btnBorrar;

    int grados1, minutos1, segundos1;
    int grados, minutos, segundos, cantidad;
    int mGrados, mMinutos, mSegundos;
    int rMinutos, rSegundos, dMinutos, dSegundos;

    public static MultiplicacionAngulosFragment newInstance() {
        return new MultiplicacionAngulosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.multiplicacion_angulos_fragment, container, false);
        View v = inflater.inflate(R.layout.multiplicacion_angulos_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtGrados = v.findViewById(R.id.id_grados);
        txtMinutos = v.findViewById(R.id.id_minutos);
        txtSegundos = v.findViewById(R.id.id_segundos);
        txtMultiplicador = v.findViewById(R.id.id_multiplicador);

        // Se agregan automáticamente comas (,) cada mil
        txtGrados.addTextChangedListener(new NumberTextWatcher(txtGrados));
        txtMinutos.addTextChangedListener(new NumberTextWatcher(txtMinutos));
        txtSegundos.addTextChangedListener(new NumberTextWatcher(txtSegundos));
        txtMultiplicador.addTextChangedListener(new NumberTextWatcher(txtMultiplicador));

        // TextView
        tvGrados = v.findViewById(R.id.tvGrados);
        tvMinutos = v.findViewById(R.id.tvMinutos);
        tvSegundos = v.findViewById(R.id.tvSegundos);
        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnCalcular = v.findViewById(R.id.id_calcular);

        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        btnCalcular.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                if (txtGrados.length() == 0) { grados1 = 0; } else { grados1 = Integer.parseInt(txtGrados.getText().toString().replace(",", "")); }
                if (txtMinutos.length() == 0) { minutos1 = 0; } else { minutos1 = Integer.parseInt(txtMinutos.getText().toString().replace(",", "")); }
                if (txtSegundos.length() == 0) { segundos1 = 0; } else { segundos1 = Integer.parseInt(txtSegundos.getText().toString().replace(",", "")); }
                if (txtMultiplicador.length() == 0) { cantidad = 0; } else { cantidad = Integer.parseInt(txtMultiplicador.getText().toString().replace(",", "")); }

                mSegundos = segundos1 * cantidad;
                mMinutos = minutos1 * cantidad;
                mGrados = grados1 * cantidad;

                if (mSegundos >= 60) {
                    dSegundos = mSegundos / 60;
                    rSegundos = mSegundos % 60;
                    segundos = rSegundos;
                    mMinutos = mMinutos + dSegundos;
                    if (mMinutos >= 60) {
                        dMinutos = mMinutos / 60;
                        rMinutos = mMinutos % 60;
                        minutos = rMinutos;
                        grados = mGrados + dMinutos;
                    } else {
                        minutos = mMinutos;
                        grados = mGrados;
                    }
                } else {
                    segundos = mSegundos;
                    if (mMinutos >= 60) {
                        dMinutos = mMinutos / 60;
                        rMinutos = mMinutos % 60;
                        minutos = rMinutos;
                        grados = mGrados + dMinutos;
                    } else {
                        minutos = mMinutos;
                        grados = mGrados;
                    }
                }

                //mostrarResultado.setText("RESULTADO\n" + grados + " °  " + minutos + " \'  " + segundos + " \"");
                if (grados >= 1000) {
                    decimalFormat[0] = new DecimalFormat("#0,000");
                } else {
                    decimalFormat[0] = new DecimalFormat("#0");
                }
                tvGrados.setText(decimalFormat[0].format(grados) + " °");

                if (minutos >= 1000) {
                    decimalFormat[0] = new DecimalFormat("#0,000");
                } else {
                    decimalFormat[0] = new DecimalFormat("#0");
                }
                tvMinutos.setText(decimalFormat[0].format(minutos) + " \'");

                if (segundos >= 1000) {
                    decimalFormat[0] = new DecimalFormat("#0,000");
                } else {
                    decimalFormat[0] = new DecimalFormat("#0");
                }
                tvSegundos.setText(decimalFormat[0].format(segundos) + " \"");

            }

        });

        // Botón BORRAR
        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtGrados.setText("");
                txtMinutos.setText("");
                txtSegundos.setText("");
                txtMultiplicador.setText("");
                tvGrados.setText("");
                tvMinutos.setText("");
                tvSegundos.setText("");
            }

        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MultiplicacionAngulosViewModel.class);
        // TODO: Use the ViewModel
    }

}