package com.example.imath.ui.circle;

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
import android.widget.TabHost;
import android.widget.TextView;

import com.example.imath.NumberTextWatcher;
import com.example.imath.R;

import java.text.DecimalFormat;

public class CircleFragment extends Fragment {

    private CircleViewModel mViewModel;

    private Spinner spUnidadPerimetro, spUnidadArea;
    private EditText txtRadioPerimetro, txtRadioArea;
    private TextView tvPerimetro, tvArea;
    private Button btnPerimetro, btnArea, btnBorrarPerimetro, btnBorrarArea;

    private String unidadPerimetro, unidadArea;
    private double radioPerimetro, radioArea;
    private double perimetro, area;

    public static CircleFragment newInstance() {
        return new CircleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.circle_fragment, container, false);
        View v = inflater.inflate(R.layout.circle_fragment, container, false);

        TabHost tabs = v.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("pest1");
        spec.setContent(R.id.pest1);
        spec.setIndicator("Perímetro", getResources().getDrawable(R.drawable.ic_circle));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("pest2");
        spec.setContent(R.id.pest2);
        spec.setIndicator("Área", getResources().getDrawable(R.drawable.ic_circle));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtRadioPerimetro = v.findViewById(R.id.txtRadioPerimetro);
        txtRadioArea = v.findViewById(R.id.txtRadioArea);

        // Se agregan automáticamente comas (,) cada mil
        txtRadioPerimetro.addTextChangedListener(new NumberTextWatcher(txtRadioPerimetro));
        txtRadioArea.addTextChangedListener(new NumberTextWatcher(txtRadioArea));

        // TextView
        tvPerimetro = v.findViewById(R.id.tvPerimetro);
        tvArea = v.findViewById(R.id.tvArea);
        // Botones
        btnPerimetro = v.findViewById(R.id.btnCalcularPerimetro);
        btnArea = v.findViewById(R.id.btnCalcularArea);
        btnBorrarPerimetro = v.findViewById(R.id.btnBorrarPerimetro);
        btnBorrarArea = v.findViewById(R.id.btnBorrarArea);
        // Spinners
        spUnidadPerimetro = v.findViewById(R.id.unidadPerimetro);
        spUnidadArea = v.findViewById(R.id.unidadArea);
        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        /*
         * Funcionamiento de las pantallas
         */

        String [] unidadesPerimetro = {"Unidad", "cm", "m", "km"};
        String [] unidadesArea = {"Unidad", "cm", "m", "km"};
        ArrayAdapter<String> upAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unidadesPerimetro);
        spUnidadPerimetro.setAdapter(upAdapter);
        ArrayAdapter <String> uaAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unidadesArea);
        spUnidadArea.setAdapter(uaAdapter);

        // Botón del PERÍMETRO
        btnPerimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unidadPerimetro = spUnidadPerimetro.getSelectedItem().toString();
                if (txtRadioPerimetro.length() == 0) {
                    txtRadioPerimetro.setError("Faltan datos");
                    txtRadioPerimetro.requestFocus();
                } else {
                    radioPerimetro = Double.parseDouble(txtRadioPerimetro.getText().toString().replace(",", ""));
                    if (unidadPerimetro.equals("cm") || unidadPerimetro.equals("m") || unidadPerimetro.equals("km")) {
                        perimetro = Math.PI * radioPerimetro * 2;
                        if (perimetro >= 1000) {
                            decimalFormat[0] = new DecimalFormat("#0,000.0000");
                            tvPerimetro.setText("Perímetro \n" + decimalFormat[0].format(perimetro) + " " + unidadPerimetro);
                        } else {
                            decimalFormat[0] = new DecimalFormat("#0.0000");
                            tvPerimetro.setText("Perímetro \n" + decimalFormat[0].format(perimetro) + " " + unidadPerimetro);
                        }
                    } else {
                        tvPerimetro.setText("UNIDAD NO VÁLIDA");
                    }
                }
            }
        });

        // Botón de borrar PERÍMETRO
        btnBorrarPerimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRadioPerimetro.setText("");
                tvPerimetro.setText("");
            }
        });

        // Botón del ÁREA
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unidadArea = spUnidadArea.getSelectedItem().toString();
                if (txtRadioArea.length() == 0) {
                    txtRadioArea.setError("Faltan datos");
                    txtRadioArea.requestFocus();
                } else {
                    radioArea = Double.parseDouble(txtRadioArea.getText().toString().replace(",", ""));
                    if (unidadArea.equals("cm") || unidadArea.equals("m") || unidadArea.equals("km")) {
                        area = Math.PI * Math.pow(radioArea, 2);
                        if (area >= 1000) {
                            decimalFormat[0] = new DecimalFormat("#0,000.0000");
                            tvArea.setText("Área \n" + decimalFormat[0].format(area) + " " + unidadArea + "²");
                        } else {
                            decimalFormat[0] = new DecimalFormat("#0.0000");
                            tvArea.setText("Área \n" + decimalFormat[0].format(area) + " " + unidadArea + "²");
                        }
                    } else {
                        tvArea.setText("UNIDAD NO VÁLIDA");
                    }
                }
            }
        });

        // Botón de borrar ÁREA
        btnBorrarArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRadioArea.setText("");
                tvArea.setText("");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CircleViewModel.class);
        // TODO: Use the ViewModel
    }

}