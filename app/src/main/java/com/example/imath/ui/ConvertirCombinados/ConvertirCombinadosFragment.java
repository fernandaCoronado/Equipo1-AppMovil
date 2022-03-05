package com.example.imath.ui.ConvertirCombinados;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imath.R;

public class ConvertirCombinadosFragment extends Fragment {

    private ConvertirCombinadosViewModel mViewModel;

    public static ConvertirCombinadosFragment newInstance() {
        return new ConvertirCombinadosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.convertir_combinados_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConvertirCombinadosViewModel.class);
        // TODO: Use the ViewModel
    }

}