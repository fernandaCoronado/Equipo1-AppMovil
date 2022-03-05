package com.example.imath.ui.ConvertirSegundos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imath.R;

public class ConvertirSegundosFragment extends Fragment {

    private ConvertirSegundosViewModel mViewModel;

    public static ConvertirSegundosFragment newInstance() {
        return new ConvertirSegundosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.convertir_segundos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirSegundosViewModel.class);
        // TODO: Use the ViewModel
    }

}