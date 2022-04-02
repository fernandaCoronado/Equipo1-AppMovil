package com.example.imath.ui.DivisionAngulos;

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

public class DivisionAngulosFragment extends Fragment {

    private DivisionAngulosViewModel mViewModel;

    TextView tvGrados, tvMinutos, tvSegundos;
    EditText txtGrados, txtMinutos, txtSegundos, txtDivisor;
    Button btnCalcular, btnBorrar;

    int grados, minutos, segundos;
    int grados1, minutos1, segundos1, divisor;
    int rGrados, rMinutos;

    public static DivisionAngulosFragment newInstance() {
        return new DivisionAngulosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.division_angulos_fragment, container, false);
        View v = inflater.inflate(R.layout.division_angulos_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtGrados = v.findViewById(R.id.id_grados);
        txtMinutos = v.findViewById(R.id.id_minutos);
        txtSegundos = v.findViewById(R.id.id_segundos);
        txtDivisor = v.findViewById(R.id.id_dividir);

        // Se agregan automáticamente comas (,) cada mil
        txtGrados.addTextChangedListener(new NumberTextWatcher(txtGrados));
        txtMinutos.addTextChangedListener(new NumberTextWatcher(txtMinutos));
        txtSegundos.addTextChangedListener(new NumberTextWatcher(txtSegundos));
        txtDivisor.addTextChangedListener(new NumberTextWatcher(txtDivisor));

        // TextView
        tvGrados = v.findViewById(R.id.tvGrados);
        tvMinutos = v.findViewById(R.id.tvMinutos);
        tvSegundos = v.findViewById(R.id.tvSegundos);
        // Botones
        btnBorrar = v.findViewById(R.id.id_borrar);
        btnCalcular = v.findViewById(R.id.id_calcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                if (txtDivisor.length() == 0 || txtDivisor.getText().toString().equals("0")) {
                    if (txtDivisor.length() == 0)
                        txtDivisor.setError("Faltan datos");
                    if (txtDivisor.getText().toString().equals("0"))
                        txtDivisor.setError("El divisor no puede ser 0");
                } else {
                    if (txtGrados.length() == 0) { grados1 = 0; } else { grados1 = Integer.parseInt(txtGrados.getText().toString().replace(",", "")); }
                    if (txtMinutos.length() == 0) { minutos1 = 0; } else { minutos1 = Integer.parseInt(txtMinutos.getText().toString().replace(",", "")); }
                    if (txtSegundos.length() == 0) { segundos1 = 0; } else { segundos1 = Integer.parseInt(txtSegundos.getText().toString().replace(",", "")); }
                    divisor = Integer.parseInt(txtDivisor.getText().toString().replace(",", ""));

                    grados = grados1 / divisor;
                    rGrados = (grados1 % divisor) * 60;
                    minutos1 = minutos1 + rGrados;
                    minutos = minutos1 / divisor;
                    rMinutos = (minutos1 % divisor) * 60;
                    segundos1 = segundos1 + rMinutos;
                    segundos = segundos1 / divisor;

                    // mostrarResultado.setText("RESULTADO\n" + grados + " °  " + minutos + " \'  " + segundos + " \"");
                    tvGrados.setText(grados + " °");
                    tvMinutos.setText(minutos + " \'");
                    tvSegundos.setText(segundos + " \"");
                }

            }

        });

        // Botón BORRAR
        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtGrados.setText("");
                txtMinutos.setText("");
                txtSegundos.setText("");
                txtDivisor.setText("");
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
        mViewModel = new ViewModelProvider(this).get(DivisionAngulosViewModel.class);
        // TODO: Use the ViewModel
    }

}