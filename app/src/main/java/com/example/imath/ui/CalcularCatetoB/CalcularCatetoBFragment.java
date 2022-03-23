package com.example.imath.ui.CalcularCatetoB;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.imath.NumberTextWatcher;
import com.example.imath.R;

import java.text.DecimalFormat;

public class CalcularCatetoBFragment extends Fragment {

    private CalcularCatetoBViewModel mViewModel;

    private Button btnCalcular, btnBorrar;
    private EditText txtHipotenusa, txtCatetoA;
    private TextView tvResultado;
    private Spinner spUnidad;

    private String unidad;
    private double hipotenusa, catetoB;
    private double catetoA;

    public static CalcularCatetoBFragment newInstance() {
        return new CalcularCatetoBFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.calcular_cateto_b_fragment, container, false);

        View v = inflater.inflate(R.layout.calcular_cateto_b_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtHipotenusa = v.findViewById(R.id.id_hipo_a);
        txtCatetoA = v.findViewById(R.id.id_cateto_a);
        btnCalcular = v.findViewById(R.id.id_calcular_catb);
        btnBorrar = v.findViewById(R.id.id_eliminar_catb);
        tvResultado = v.findViewById(R.id.tvResultado);
        spUnidad = v.findViewById(R.id.id_unidad);

        // Se agregan automáticamente comas (,) cada mil
        txtCatetoA.addTextChangedListener(new NumberTextWatcher(txtCatetoA));
        txtHipotenusa.addTextChangedListener(new NumberTextWatcher(txtHipotenusa));

        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        /*
         * Funcionamiento de las pantallas
         */

        String [] unidades = {"Unidad", "cm", "m", "km"};
        ArrayAdapter<String> uAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unidades);
        spUnidad.setAdapter(uAdapter);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unidad = spUnidad.getSelectedItem().toString();
                if (txtCatetoA.length() == 0 || txtHipotenusa.length() == 0) {
                    // txtRadioPerimetro.setError("Faltan datos");
                    // txtRadioPerimetro.requestFocus();
                    if (txtCatetoA.length() == 0) {
                        txtCatetoA.setError("Faltan datos");
                    }
                    if (txtHipotenusa.length() == 0) {
                        txtHipotenusa.setError("Faltan datos");
                    }
                } else {
                    catetoA = Double.parseDouble(txtCatetoA.getText().toString().replace(",", ""));
                    hipotenusa = Double.parseDouble(txtHipotenusa.getText().toString().replace(",", ""));
                    if (unidad.equals("cm") || unidad.equals("m") || unidad.equals("km")) {
                        // b = Math.sqrt(Math.pow(h, 2) - Math.pow(a, 2));
                        catetoB = Math.sqrt(Math.pow(hipotenusa, 2) - Math.pow(catetoA, 2));
                        if (catetoB >= 1000) {
                            decimalFormat[0] = new DecimalFormat("#0,000.0000");
                            tvResultado.setText("Cateto b \n" + decimalFormat[0].format(catetoB) + " " + unidad);
                        } else {
                            decimalFormat[0] = new DecimalFormat("#0.0000");
                            tvResultado.setText("Cateto b \n" + decimalFormat[0].format(catetoB) + " " + unidad);
                        }
                    } else {
                        tvResultado.setText("UNIDAD NO VÁLIDA");
                    }
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCatetoA.setText("");
                txtHipotenusa.setText("");
                tvResultado.setText("");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcularCatetoBViewModel.class);
        // TODO: Use the ViewModel
    }

}