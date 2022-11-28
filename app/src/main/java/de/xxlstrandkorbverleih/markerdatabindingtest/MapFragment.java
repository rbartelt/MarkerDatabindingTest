package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.MapView;

import de.xxlstrandkorbverleih.markerdatabindingtest.databinding.FragmentMapBinding;


public class MapFragment extends Fragment {

    /////////////////////////////////////////////////////////////////////////////
    //Membervariables
    private MapView google_map;
    private MapFragmentViewModel viewModel;
    private FragmentMapBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MapFragmentViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewmodel(viewModel);

        google_map=binding.korbMap;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        google_map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        google_map.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        google_map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        google_map.onLowMemory();
    }

    @Override
    public void onStop() {
        super.onStop();
        google_map.onStop();
    }
}