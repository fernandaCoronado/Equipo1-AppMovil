package com.example.imath.ui.CalcularHipotenusa;

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

public class CalcularHipotenusaFragment extends Fragment {

    private CalcularHipotenusaViewModel mViewModel;

    private Button btnCalcular, btnBorrar;
    private EditText txtCatetoB, txtCatetoA;
    private TextView tvResultado;
    private Spinner spUnidad;

    private String unidad;
    private double hipotenusa, catetoB;
    private double catetoA;

    public static CalcularHipotenusaFragment newInstance() {
        return new CalcularHipotenusaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.calcular_hipotenusa_fragment, container, false);
        View v = inflater.inflate(R.layout.calcular_hipotenusa_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtCatetoB = v.findViewById(R.id.id_cateto_b);
        txtCatetoA = v.findViewById(R.id.id_cateto_a);
        btnCalcular = v.findViewById(R.id.id_calcular);
        btnBorrar = v.findViewById(R.id.id_eliminar);
        tvResultado = v.findViewById(R.id.tvResultado);
        spUnidad = v.findViewById(R.id.id_unidad);

        // Se agregan automáticamente comas (,) cada mil
        txtCatetoA.addTextChangedListener(new NumberTextWatcher(txtCatetoA));
        txtCatetoB.addTextChangedListener(new NumberTextWatcher(txtCatetoB));

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
                if (txtCatetoA.length() == 0 || txtCatetoB.length() == 0) {
                    // txtRadioPerimetro.setError("Faltan datos");
                    // txtRadioPerimetro.requestFocus();
                    // c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    if (txtCatetoA.length() == 0) {
                        txtCatetoA.setError("Falta el cateto a");
                    }
                    if (txtCatetoB.length() == 0) {
                        txtCatetoB.setError("Falta el cateto b");
                    }
                } else {
                    catetoA = Double.parseDouble(txtCatetoA.getText().toString().replace(",", ""));
                    catetoB = Double.parseDouble(txtCatetoB.getText().toString().replace(",", ""));
                    if (unidad.equals("cm") || unidad.equals("m") || unidad.equals("km")) {
                        // b = Math.sqrt(Math.pow(h, 2) - Math.pow(a, 2));
                        //catetoB = Math.sqrt(Math.pow(hipotenusa, 2) - Math.pow(catetoA, 2));
                        hipotenusa = Math.sqrt(Math.pow(catetoA, 2) + Math.pow(catetoB, 2));
                        if (hipotenusa >= 1000) {
                            decimalFormat[0] = new DecimalFormat("#0,000.0000");
                            tvResultado.setText("Cateto b \n" + decimalFormat[0].format(hipotenusa) + " " + unidad);
                        } else {
                            decimalFormat[0] = new DecimalFormat("#0.0000");
                            tvResultado.setText("Cateto b \n" + decimalFormat[0].format(hipotenusa) + " " + unidad);
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
                txtCatetoB.setText("");
                tvResultado.setText("");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcularHipotenusaViewModel.class);
        // TODO: Use the ViewModel
    }

}