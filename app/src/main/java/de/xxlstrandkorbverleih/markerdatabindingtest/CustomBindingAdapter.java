package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.os.Bundle;
import androidx.databinding.BindingAdapter;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class CustomBindingAdapter {

    @BindingAdapter({"initMap", "markerevent"}) //initMap bezieht sich auf den Methode welche einen Parameter vom Typ List<beachchair> hat (aus layout xml app:initMap="@{viewmodel.beachchairs}")
    public static void initMap(final MapView mapView, final List<Beachchair> beachchairs, final MapFragmentViewModel viewModel) {
        if (mapView != null) {
            mapView.onCreate(new Bundle());
            mapView.getMapAsync(googleMap -> {
                if (beachchairs != null) {
                    mapView.onResume();
                    googleMap.setOnMarkerClickListener(viewModel);
                    for (Beachchair beachchair : beachchairs) {
                        if (beachchair.isFree())
                            googleMap.addMarker(new MarkerOptions().position(beachchair.getLocation()).title(String.valueOf(beachchair.getNumber())));
                    }
                }
            });
        }
    }

}
