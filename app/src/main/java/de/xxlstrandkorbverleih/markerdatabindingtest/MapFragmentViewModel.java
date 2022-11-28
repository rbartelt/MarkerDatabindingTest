package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapFragmentViewModel extends androidx.lifecycle.ViewModel implements GoogleMap.OnMarkerClickListener {

    public MutableLiveData<List<LatLng>> locations = new MutableLiveData<>();
    public MutableLiveData<List<Beachchair>> beachchairs = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedNumber = new MutableLiveData<>();


    public MapFragmentViewModel() {
        List<Beachchair> list = new ArrayList<>();
        Beachchair beachchair1 = new Beachchair(1, "Normal", new LatLng(14,14));
        Beachchair beachchair2 = new Beachchair(2, "XL", new LatLng(14.1,14.1));
        Beachchair beachchair3 = new Beachchair(3, "XXL", new LatLng(14.2, 14.2));
        list.add(beachchair1);
        list.add(beachchair2);
        list.add(beachchair3);
        beachchairs.setValue(list);
        /*List<LatLng> list = new ArrayList<>();
        list.add(new LatLng(14,14));
        list.add(new LatLng(14.1,14));
        list.add(new LatLng(14.2,14));
        locations.setValue(list);*/
        selectedNumber.setValue(99);
    }

    public LiveData getBeachchairs() {
        return beachchairs;
    }

    public LiveData<Integer> getSelectedNumber() {
        return selectedNumber;
    }

    public void changeNumber() {
        selectedNumber.setValue(selectedNumber.getValue()+1);
    }
    @BindingAdapter("initMap")
    public static void initMap(final MapView mapView, final List<Beachchair> beachchairs) {
        if (mapView != null) {
            mapView.onCreate(new Bundle());
            mapView.getMapAsync(googleMap -> {
                if (beachchairs != null) {
                    mapView.onResume();
                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            Log.d("Marker :", marker.getTitle());
                            return false;
                        }
                    });
                    for (Beachchair beachchair : beachchairs)
                        googleMap.addMarker(new MarkerOptions().position(beachchair.getLocation()).title(String.valueOf(beachchair.getNumber())));
                }
            });
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        selectedNumber.setValue(Integer.valueOf(marker.getTitle()));
        return false;
    }
}
