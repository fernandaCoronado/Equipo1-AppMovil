package com.example.imath.ui.GMSToRadianes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imath.R;

public class GMSToRadianesFragment extends Fragment {

    private GMSToRadianesViewModel mViewModel;

    public static GMSToRadianesFragment newInstance() {
        return new GMSToRadianesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.g_m_s_to_radianes_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GMSToRadianesViewModel.class);
        // TODO: Use the ViewModel
    }

}