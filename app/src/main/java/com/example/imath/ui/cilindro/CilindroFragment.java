package com.example.imath.ui.cilindro;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.imath.R;

public class CilindroFragment extends Fragment {

    private CilindroViewModel mViewModel;

    public static CilindroFragment newInstance() {
        return new CilindroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.cilindro_fragment, container, false);
        View v = inflater.inflate(R.layout.cilindro_fragment, container, false);

        TabHost tabs = v.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("pest1");
        spec.setContent(R.id.pest1);
        spec.setIndicator("Área", getResources().getDrawable(R.drawable.ic_cubo));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("pest2");
        spec.setContent(R.id.pest2);
        spec.setIndicator("Volumen", getResources().getDrawable(R.drawable.ic_cubo));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CilindroViewModel.class);
        // TODO: Use the ViewModel
    }

}