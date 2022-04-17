package com.example.imath.ui.RestaAngulos;

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

public class RestaAngulosFragment extends Fragment {

    private RestaAngulosViewModel mViewModel;

    private EditText txtGrados, txtGrados2, txtMinutos, txtMinutos2, txtSegundos, txtSegundos2;
    private TextView tvGrados, tvMinutos, tvSegundos;
    private TextView tvAlerta;
    private Button btnCalcular, btnBorrar;

    private int grados1, minutos1, segundos1;
    private int grados2, minutos2, segundos2;
    private int totalGrados, totalMinutos, totalSegundos;

    public static RestaAngulosFragment newInstance() {
        return new RestaAngulosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.resta_angulos_fragment, container, false);
        View v = inflater.inflate(R.layout.resta_angulos_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtGrados = v.findViewById(R.id.id_grados);
        txtGrados2 = v.findViewById(R.id.id_grados2);
        txtMinutos = v.findViewById(R.id.id_minutos);
        txtMinutos2 = v.findViewById(R.id.id_minutos2);
        txtSegundos = v.findViewById(R.id.id_segundos);
        txtSegundos2 = v.findViewById(R.id.id_segundos2);

        // Se agregan automáticamente comas (,) cada mil
        txtGrados.addTextChangedListener(new NumberTextWatcher(txtGrados));
        txtMinutos.addTextChangedListener(new NumberTextWatcher(txtMinutos));
        txtSegundos.addTextChangedListener(new NumberTextWatcher(txtSegundos));
        txtGrados2.addTextChangedListener(new NumberTextWatcher(txtGrados2));
        txtMinutos2.addTextChangedListener(new NumberTextWatcher(txtMinutos2));
        txtSegundos2.addTextChangedListener(new NumberTextWatcher(txtSegundos2));

        // TextView
        tvGrados = v.findViewById(R.id.tvGrados);
        tvMinutos = v.findViewById(R.id.tvMinutos);
        tvSegundos = v.findViewById(R.id.tvSegundos);
        tvAlerta = v.findViewById(R.id.textView2);
        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnCalcular = v.findViewById(R.id.id_calcular);

        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        // Botón CALCULAR
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtGrados.length() == 0) {
                    grados1 = 0;
                } else {
                    grados1 = Integer.parseInt(txtGrados.getText().toString().replace(",", ""));
                }
                if (txtMinutos.length() == 0) {
                    minutos1 = 0;
                } else {
                    minutos1 = Integer.parseInt(txtMinutos.getText().toString().replace(",", ""));
                }
                if (txtSegundos.length() == 0) {
                    segundos1 = 0;
                } else {
                    segundos1 = Integer.parseInt(txtSegundos.getText().toString().replace(",", ""));
                }
                if (txtGrados2.length() == 0) {
                    grados2 = 0;
                } else {
                    grados2 = Integer.parseInt(txtGrados2.getText().toString().replace(",", ""));
                }
                if (txtMinutos2.length() == 0) {
                    minutos2 = 0;
                } else {
                    minutos2 = Integer.parseInt(txtMinutos2.getText().toString().replace(",", ""));
                }
                if (txtSegundos2.length() == 0) {
                    segundos2 = 0;
                } else {
                    segundos2 = Integer.parseInt(txtSegundos2.getText().toString().replace(",", ""));
                }

                if (segundos2 > segundos1) {
                    while (segundos2 > segundos1) {
                        minutos1 -= 1;
                        segundos1 += 60;
                    }
                    totalSegundos = segundos1 - segundos2;
                } else {
                    totalSegundos = segundos1 - segundos2;
                }

                if (minutos2 > minutos1) {
                    while (minutos2 > minutos1) {
                        grados1 -= 1;
                        minutos1 += 60;
                    }
                    totalMinutos = minutos1 - minutos2;
                } else {
                    totalMinutos = minutos1 - minutos2;
                }

                if (grados2 > grados1) {
                    tvAlerta.setText("No se puede calcular. Los grados " + grados2 + " son mayores a " + grados1);
                    tvGrados.setText("");
                    tvMinutos.setText("");
                    tvSegundos.setText("");
                } else {
                    tvAlerta.setText("Resultado");
                    totalGrados = grados1 - grados2;
                    // mostrarResultado.setText("RESULTADO\n" + grados + " °  " + minutos + " \'  " + segundos + " \"");
                    if (totalGrados >= 1000) {
                        decimalFormat[0] = new DecimalFormat("#0,000");
                    } else {
                        decimalFormat[0] = new DecimalFormat("#0");
                    }
                    tvGrados.setText(decimalFormat[0].format(totalGrados) + " °");

                    if (totalMinutos >= 1000) {
                        decimalFormat[0] = new DecimalFormat("#0,000");
                    } else {
                        decimalFormat[0] = new DecimalFormat("#0");
                    }
                    tvMinutos.setText(decimalFormat[0].format(totalMinutos) + " \'");

                    if (totalSegundos >= 1000) {
                        decimalFormat[0] = new DecimalFormat("#0,000");
                    } else {
                        decimalFormat[0] = new DecimalFormat("#0");
                    }
                    tvSegundos.setText(decimalFormat[0].format(totalSegundos) + " \"");
                }
            }
        });


        // Botón BORRAR
        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtGrados.setText("");
                txtGrados2.setText("");
                txtMinutos.setText("");
                txtMinutos2.setText("");
                txtSegundos.setText("");
                txtSegundos2.setText("");
                tvGrados.setText("");
                tvMinutos.setText("");
                tvSegundos.setText("");
                tvAlerta.setText("Resultado");
            }

        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RestaAngulosViewModel.class);
        // TODO: Use the ViewModel
    }

}