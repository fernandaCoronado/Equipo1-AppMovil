package com.example.imath.ui.MultiplicacionAngulos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imath.R;

public class MultiplicacionAngulosFragment extends Fragment {

    private MultiplicacionAngulosViewModel mViewModel;

    public static MultiplicacionAngulosFragment newInstance() {
        return new MultiplicacionAngulosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.multiplicacion_angulos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MultiplicacionAngulosViewModel.class);
        // TODO: Use the ViewModel
    }

}