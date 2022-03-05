package com.example.imath.ui.CalcularCatetoB;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imath.R;

public class CalcularCatetoBFragment extends Fragment {

    private CalcularCatetoBViewModel mViewModel;

    public static CalcularCatetoBFragment newInstance() {
        return new CalcularCatetoBFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calcular_cateto_b_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcularCatetoBViewModel.class);
        // TODO: Use the ViewModel
    }

}