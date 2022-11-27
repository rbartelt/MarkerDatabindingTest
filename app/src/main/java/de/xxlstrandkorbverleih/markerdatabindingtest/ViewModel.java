package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.os.Bundle;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private MutableLiveData<List<Beachchair>> beachchairs = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedNumber = new MutableLiveData<>();


    public ViewModel() {
        List<Beachchair> list = new ArrayList<>();
        Beachchair beachchair1 = new Beachchair(1, "Normal", new LatLng(14,14));
        Beachchair beachchair2 = new Beachchair(2, "XL", new LatLng(14.1,14.1));
        Beachchair beachchair3 = new Beachchair(3, "XXL", new LatLng(14.2, 14.2));
        list.add(beachchair1);
        list.add(beachchair2);
        list.add(beachchair3);
        beachchairs.setValue(list);
    }

    public LiveData getBeachchairs() {
        return beachchairs;
    }

    public MutableLiveData<Integer> getSelectedNumber() {
        return selectedNumber;
    }

    @BindingAdapter("initMap")
    public static void initMap(final MapView mapView, final List<LatLng> locations) {
        if (mapView != null) {
            mapView.onCreate(new Bundle());
            mapView.getMapAsync(googleMap -> {
                if (locations != null) {
                    mapView.onResume();
                    for (LatLng latLng : locations)
                        googleMap.addMarker(new MarkerOptions().position(latLng));
                }
            });
        }
    }
}
