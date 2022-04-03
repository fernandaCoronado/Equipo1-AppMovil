package com.example.imath.ui.ConvertirCombinados;

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

public class ConvertirCombinadosFragment extends Fragment {

    private ConvertirCombinadosViewModel mViewModel;

    TextView tvResultado, tvTipo;
    Button btnGrados, btnMinutos, btnSegundos, btnBorrar;
    EditText txtGrados, txtMinutos, txtSegundos;

    int grados = 0, minutos = 0, segundos = 0;

    public static ConvertirCombinadosFragment newInstance() {
        return new ConvertirCombinadosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.convertir_combinados_fragment, container, false);
        View v = inflater.inflate(R.layout.convertir_combinados_fragment, container, false);

        tvResultado = v.findViewById(R.id.tvResultado);
        tvTipo = v.findViewById(R.id.tvTipo);
        
        txtGrados = v.findViewById(R.id.id_grados);
        txtMinutos = v.findViewById(R.id.id_minutos);
        txtSegundos = v.findViewById(R.id.id_segundos);

        // Se agregan automáticamente comas (,) cada mil
        txtGrados.addTextChangedListener(new NumberTextWatcher(txtGrados));
        txtMinutos.addTextChangedListener(new NumberTextWatcher(txtMinutos));
        txtSegundos.addTextChangedListener(new NumberTextWatcher(txtSegundos));
        
        btnGrados = v.findViewById(R.id.btnGrados);
        btnMinutos = v.findViewById(R.id.btnMinutos);
        btnSegundos = v.findViewById(R.id.btnSegundos);
        btnBorrar = v.findViewById(R.id.id_borrar);

        // BORRAR
        btnBorrar.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {
                txtGrados.setText("");
                txtMinutos.setText("");
                txtSegundos.setText("");
                tvResultado.setText("");
                tvTipo.setText("");
            }
        });

        // CALCULAR A GRADOS
        btnGrados.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                // GRADOS
                double calcularMinutos, calcularSegundos, resultadoGrados;

                if (txtGrados.length() == 0) { grados = 0; } else { grados = Integer.parseInt(txtGrados.getText().toString().replace(",", "")); }
                if (txtMinutos.length() == 0) { minutos = 0; } else { minutos = Integer.parseInt(txtMinutos.getText().toString().replace(",", "")); }
                if (txtSegundos.length() == 0) { segundos = 0; } else { segundos = Integer.parseInt(txtSegundos.getText().toString().replace(",", "")); }

                DecimalFormat decimal = new DecimalFormat("#0.00000");

                calcularMinutos = (double) minutos / 60;
                calcularSegundos = (double) segundos / 3600;

                resultadoGrados = grados + calcularMinutos + calcularSegundos;

                // tvResultado.setText("RESULTADO: \n" + decimal.format(resultadoGrados) + " °");
                tvTipo.setText("GRADOS");
                tvResultado.setText(decimal.format(resultadoGrados) + " °");
            }
        });

        // CONVERTIR A MINUTOS
        btnMinutos.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                double calcularGrados, calcularSegundos, resultadoMinutos;
                DecimalFormat decimal = new DecimalFormat("#0.00000");

                if (txtGrados.length() == 0) { grados = 0; } else { grados = Integer.parseInt(txtGrados.getText().toString().replace(",", "")); }
                if (txtMinutos.length() == 0) { minutos = 0; } else { minutos = Integer.parseInt(txtMinutos.getText().toString().replace(",", "")); }
                if (txtSegundos.length() == 0) { segundos = 0; } else { segundos = Integer.parseInt(txtSegundos.getText().toString().replace(",", "")); }

                calcularGrados = grados * 60;
                calcularSegundos = (double) segundos / 60;

                resultadoMinutos = minutos + calcularGrados + calcularSegundos;

                // tvResultado.setText("RESULTADO: \n" + decimal.format(resultadoMinutos) + " \'");
                tvTipo.setText("MINUTOS");
                tvResultado.setText(decimal.format(resultadoMinutos) + " \"");
            }
        });

        // CONVERTIR A SEGUNDOS
        btnSegundos.setOnClickListener(new View.OnClickListener() { // hago clic en el botón
            @Override
            public void onClick(View view) {

                int calcularGrados, calcularMinutos, resultadoSegundos;

                if (txtGrados.length() == 0) { grados = 0; } else { grados = Integer.parseInt(txtGrados.getText().toString().replace(",", "")); }
                if (txtMinutos.length() == 0) { minutos = 0; } else { minutos = Integer.parseInt(txtMinutos.getText().toString().replace(",", "")); }
                if (txtSegundos.length() == 0) { segundos = 0; } else { segundos = Integer.parseInt(txtSegundos.getText().toString().replace(",", "")); }

                calcularGrados = grados * 3600;
                calcularMinutos = minutos * 60;

                resultadoSegundos = segundos + calcularGrados + calcularMinutos;

                // tvResultado.setText("RESULTADO: \n" + resultadoSegundos + " \"");
                tvTipo.setText("SEGUNDOS");
                tvResultado.setText(resultadoSegundos + " \'");
            }
        });
        
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirCombinadosViewModel.class);
        // TODO: Use the ViewModel
    }

}