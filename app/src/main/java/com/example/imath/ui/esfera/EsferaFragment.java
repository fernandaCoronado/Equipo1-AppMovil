package com.example.imath.ui.esfera;

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

public class EsferaFragment extends Fragment {

    private EsferaViewModel mViewModel;

    private Spinner spUnidadVolumen, spUnidadArea;
    private EditText txtRadioVolumen, txtRadioArea;
    private TextView tvVolumen, tvArea;
    private Button btnVolumen, btnArea, btnBorrarVolumen, btnBorrarArea;

    private String unidadVolumen, unidadArea;
    private double radioVolumen, radioArea;
    private double volumen, area;

    public static EsferaFragment newInstance() {
        return new EsferaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.esfera_fragment, container, false);
        View v = inflater.inflate(R.layout.esfera_fragment, container, false);

        TabHost tabs = v.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("pest1");
        spec.setContent(R.id.pest1);
        spec.setIndicator("Área", getResources().getDrawable(R.drawable.ic_esfera));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("pest2");
        spec.setContent(R.id.pest2);
        spec.setIndicator("Volumen", getResources().getDrawable(R.drawable.ic_esfera));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtRadioVolumen = v.findViewById(R.id.txtRadioVolumen);
        txtRadioArea = v.findViewById(R.id.txtRadioArea);

        // Se agregan automáticamente comas (,) cada mil
        txtRadioVolumen.addTextChangedListener(new NumberTextWatcher(txtRadioVolumen));
        txtRadioArea.addTextChangedListener(new NumberTextWatcher(txtRadioArea));

        // TextView
        tvVolumen = v.findViewById(R.id.tvVolumen);
        tvArea = v.findViewById(R.id.tvArea);
        // Botones
        btnVolumen = v.findViewById(R.id.btnCalcularVolumen);
        btnArea = v.findViewById(R.id.btnCalcularArea);
        btnBorrarVolumen = v.findViewById(R.id.btnBorrarVolumen);
        btnBorrarArea = v.findViewById(R.id.btnBorrarArea);
        // Spinners
        spUnidadVolumen = v.findViewById(R.id.unidadVolumen);
        spUnidadArea = v.findViewById(R.id.unidadArea);
        // Formato números
        final DecimalFormat[] decimalFormat = new DecimalFormat[1];

        /*
         * Funcionamiento de las pantallas
         */

        String [] unidadesVolumen = {"Unidad", "cm", "m", "km"};
        String [] unidadesArea = {"Unidad", "cm", "m", "km"};
        ArrayAdapter<String> uvAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unidadesVolumen);
        spUnidadVolumen.setAdapter(uvAdapter);
        ArrayAdapter <String> uaAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, unidadesArea);
        spUnidadArea.setAdapter(uaAdapter);

        // Botón del VOLUMEN
        btnVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unidadVolumen = spUnidadVolumen.getSelectedItem().toString();
                if (txtRadioVolumen.length() == 0) {
                    txtRadioVolumen.setError("Faltan datos");
                    txtRadioVolumen.requestFocus();
                } else {
                    radioVolumen = Double.parseDouble(txtRadioVolumen.getText().toString().replace(",", ""));
                    if (unidadVolumen.equals("cm") || unidadVolumen.equals("m") || unidadVolumen.equals("km")) {
                        volumen = (4  * Math.PI * Math.pow(radioVolumen, 3)) / 3;
                        if (volumen >= 1000) {
                            decimalFormat[0] = new DecimalFormat("#0,000.0000");
                            tvVolumen.setText("Volumen \n" + decimalFormat[0].format(volumen) + " " + unidadVolumen + "³");
                        } else {
                            decimalFormat[0] = new DecimalFormat("#0.0000");
                            tvVolumen.setText("Volumen \n" + decimalFormat[0].format(volumen) + " " + unidadVolumen + "³");
                        }
                    } else {
                        tvVolumen.setText("UNIDAD NO VÁLIDA");
                    }
                }
            }
        });

        // Botón de borrar VOLUMEN
        btnBorrarVolumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRadioVolumen.setText("");
                tvVolumen.setText("");
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
                        area = 4 * Math.PI * Math.pow(radioArea, 2);
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
        mViewModel = new ViewModelProvider(this).get(EsferaViewModel.class);
        // TODO: Use the ViewModel
    }

}