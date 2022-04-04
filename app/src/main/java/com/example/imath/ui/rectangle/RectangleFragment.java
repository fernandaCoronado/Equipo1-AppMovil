package com.example.imath.ui.rectangle;

import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
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

public class RectangleFragment extends Fragment {

    private RectangleViewModel mViewModel;

    private EditText txtBasePerimetro, txtAlturaPerimetro, txtBaseArea, txtAlturaArea;
    private TextView tvPerimetro, tvArea;
    private Button btnPerimetro, btnArea, btnBorrarPerimetro, btnBorrarArea;

    private Spinner spUnidadPerimetro, spUnidadArea;

    private String unidadPerimetro, unidadArea;
    private double base, altura;
    private double perimetro, area;

    public static RectangleFragment newInstance() {
        return new RectangleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.rectangle_fragment, container, false);
        View v = inflater.inflate(R.layout.rectangle_fragment, container, false);

        TabHost tabs = v.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("pest1");
        spec.setContent(R.id.pest1);
        spec.setIndicator("Perímetro", getResources().getDrawable(R.drawable.ic_rectangle));
        tabs.addTab(spec);

        spec.setContent(R.id.pest2);
        spec.setIndicator("Área", getResources().getDrawable(R.drawable.ic_rectangle));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        /*
         * Rescatamos los atributos
         */

        // Cajas de texto
        txtBasePerimetro = v.findViewById(R.id.txtBasePerimetro);
        txtAlturaPerimetro = v.findViewById(R.id.txtAlturaPerimetro);
        txtBaseArea = v.findViewById(R.id.txtBaseArea);
        txtAlturaArea = v.findViewById(R.id.txtAlturaArea);

        // Se agregan automáticamente comas (,) cada mil
        txtBasePerimetro.addTextChangedListener(new NumberTextWatcher(txtBasePerimetro));
        txtAlturaPerimetro.addTextChangedListener(new NumberTextWatcher(txtAlturaPerimetro));
        txtBaseArea.addTextChangedListener(new NumberTextWatcher(txtBaseArea));
        txtAlturaArea.addTextChangedListener(new NumberTextWatcher(txtAlturaArea));

        // TextView
        tvPerimetro = v.findViewById(R.id.tvPerimetro);
        tvArea = v.findViewById(R.id.tvArea);
        // Botones
        btnPerimetro = v.findViewById(R.id.btnPerimetro);
        btnArea = v.findViewById(R.id.btnArea);
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
                if (txtBasePerimetro.length() == 0 || txtAlturaPerimetro.length() == 0) {
                    // Mensajes de error
                    if (txtBasePerimetro.length() == 0) {
                        txtBasePerimetro.setError("Falta el perímetro");
                        txtBasePerimetro.requestFocus();
                    }
                    if (txtAlturaPerimetro.length() == 0) {
                        txtAlturaPerimetro.setError("Falta la altura");
                        txtAlturaPerimetro.requestFocus();
                    }
                } else {
                    // Con ".replace(",", "")" se borra la coma (,) agregada anteriormente
                    base = Double.parseDouble(txtBasePerimetro.getText().toString().replace(",", ""));
                    altura = Double.parseDouble(txtAlturaPerimetro.getText().toString().replace(",", ""));
                    if (unidadPerimetro.equals("cm") || unidadPerimetro.equals("m") || unidadPerimetro.equals("km")) {
                        perimetro = base * 2 + altura * 2;
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
                txtBasePerimetro.setText("");
                txtAlturaPerimetro.setText("");
                tvPerimetro.setText("");
            }
        });

        // Botón del ÁREA
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unidadArea = spUnidadArea.getSelectedItem().toString();
                if (txtBaseArea.length() == 0 || txtAlturaArea.length() == 0) {
                    // Mensajes de error
                    if (txtBaseArea.length() == 0) {
                        txtBaseArea.setError("Falta la base");
                        txtBaseArea.requestFocus();
                    }
                    if (txtAlturaArea.length() == 0) {
                        txtAlturaArea.setError("Falta la altura");
                        txtAlturaArea.requestFocus();
                    }
                } else {
                    // Con ".replace(",", "")" se borra la coma (,) agregada anteriormente
                    base = Double.parseDouble(txtBaseArea.getText().toString().replace(",", ""));
                    altura = Double.parseDouble(txtAlturaArea.getText().toString().replace(",", ""));
                    if (unidadArea.equals("cm") || unidadArea.equals("m") || unidadArea.equals("km")) {
                        area = base * altura;
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
                txtBaseArea.setText("");
                txtAlturaArea.setText("");
                tvArea.setText("");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RectangleViewModel.class);
        // TODO: Use the ViewModel
    }

}