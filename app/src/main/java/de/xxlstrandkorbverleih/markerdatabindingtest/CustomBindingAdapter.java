package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class CustomBindingAdapter {

    @BindingAdapter({"initMap", "markerevent"})
    public static void initMap(final MapView mapView, final List<Beachchair> beachchairs, final GoogleMap.OnMarkerClickListener markerEvent) {
        if (mapView != null) {
            mapView.onCreate(new Bundle());
            mapView.getMapAsync(googleMap -> {
                if (beachchairs != null) {
                    mapView.onResume();
                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            markerEvent.onMarkerClick(marker);
                            return false;
                        }
                    });
                    for (Beachchair beachchair : beachchairs)
                        googleMap.addMarker(new MarkerOptions().position(beachchair.getLocation()).title(String.valueOf(beachchair.getNumber())));
                }
            });
        }
    }

}
