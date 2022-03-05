package com.example.imath.ui.ConvertirMinutos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imath.R;

public class ConvertirMinutosFragment extends Fragment {

    private ConvertirMinutosViewModel mViewModel;

    public static ConvertirMinutosFragment newInstance() {
        return new ConvertirMinutosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.convertir_minutos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirMinutosViewModel.class);
        // TODO: Use the ViewModel
    }

}