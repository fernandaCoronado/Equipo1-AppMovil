package com.example.imath.ui.CalcularCatetoA;

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

public class CalcularCatetoAFragment extends Fragment {

    private CalcularCatetoAViewModel mViewModel;
    private Button btnCalcular, btnBorrar;
    private EditText txtHipotenusa, txtCatetoB;
    private TextView tvResultado;
    private Spinner spUnidad;

    private String unidad;
    private double hipotenusa, catetoB;
    private double catetoA;

    public static CalcularCatetoAFragment newInstance() {
        return new CalcularCatetoAFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.calcular_cateto_a_fragment, container, false);
        View v = inflater.inflate(R.layout.calcular_cateto_a_fragment, container, false);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtHipotenusa = v.findViewById(R.id.id_hipo_a);
        txtCatetoB = v.findViewById(R.id.id_cateto_b);
        btnCalcular = v.findViewById(R.id.id_calcular_cata);
        btnBorrar = v.findViewById(R.id.id_eliminar_cata);
        tvResultado = v.findViewById(R.id.tvResultado);
        spUnidad = v.findViewById(R.id.id_unidad);

        // Se agregan automáticamente comas (,) cada mil
        txtCatetoB.addTextChangedListener(new NumberTextWatcher(txtCatetoB));
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
                if (txtCatetoB.length() == 0 || txtHipotenusa.length() == 0) {
                    // txtRadioPerimetro.setError("Faltan datos");
                    // txtRadioPerimetro.requestFocus();
                    if (txtCatetoB.length() == 0) {
                        txtCatetoB.setError("Faltan datos");
                    }
                    if (txtHipotenusa.length() == 0) {
                        txtHipotenusa.setError("Faltan datos");
                    }
                } else {
                    catetoB = Double.parseDouble(txtCatetoB.getText().toString().replace(",", ""));
                    hipotenusa = Double.parseDouble(txtHipotenusa.getText().toString().replace(",", ""));
                    if (unidad.equals("cm") || unidad.equals("m") || unidad.equals("km")) {
                        // Math.sqrt(Math.pow(h, 2) - Math.pow(b, 2));
                        catetoA = Math.sqrt(Math.pow(hipotenusa, 2) - Math.pow(catetoB, 2));
                        if (catetoA >= 1000) {
                            decimalFormat[0] = new DecimalFormat("#0,000.0000");
                            tvResultado.setText("Cateto a \n" + decimalFormat[0].format(catetoA) + " " + unidad);
                            //tvResultado.setText("res: " + catetoA + "\ncatb: " + catetoB + "\nhip: " + hipotenusa);
                        } else {
                            decimalFormat[0] = new DecimalFormat("#0.0000");
                            tvResultado.setText("Cateto a \n" + decimalFormat[0].format(catetoA) + " " + unidad);
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
                txtCatetoB.setText("");
                txtHipotenusa.setText("");
                tvResultado.setText("");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcularCatetoAViewModel.class);
        // TODO: Use the ViewModel
    }

}