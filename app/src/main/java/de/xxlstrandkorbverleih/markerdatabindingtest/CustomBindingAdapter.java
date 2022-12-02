package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.databinding.BindingAdapter;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;
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
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Beachchair beachchair : beachchairs) {
                        builder.include(beachchair.getLocation());
                        ////////////////////////////////////////////////////////////////////////////
                        //create Marker Bitmap
                        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                        Bitmap bitmap = Bitmap.createBitmap(80,80,conf);
                        Canvas canvas1 = new Canvas(bitmap);
                        Paint textColor = new Paint();
                        Paint circleColor = new Paint();
                        circleColor.setColor(Color.WHITE);
                        textColor.setTextSize(45);
                        switch (beachchair.getType()) {
                            case "Normal":
                                textColor.setColor(Color.RED);
                                break;
                            case "XL":
                                textColor.setColor(Color.BLUE);
                                break;
                            case "XXL":
                                textColor.setColor(Color.GREEN);
                                break;
                        }
                        if (beachchair.isSelected())
                            canvas1.drawCircle(40,40,40, circleColor);
                        canvas1.drawText(String.valueOf(beachchair.getNumber()), 30,50,textColor);

                        ////////////////////////////////////////////////////////////////////////////
                        //configure Marker
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                        markerOptions.position(beachchair.getLocation());
                        markerOptions.title(String.valueOf(beachchair.getNumber()));
                        markerOptions.flat(false);
                        googleMap.addMarker(markerOptions);
                    }
                    ////////////////////////////////////////////////////////////////////////////
                    //set Camera with BoundsBuilder to get the zoom Factor
                    LatLngBounds bounds = builder.build();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 0);
                    googleMap.moveCamera(cameraUpdate);
                    float zoom = googleMap.getCameraPosition().zoom;
                    ////////////////////////////////////////////////////////////////////////////
                    //set Camera
                    CameraPosition cameraPosition=new CameraPosition.Builder()
                            .target(viewModel.viewCentre.getValue()) // Sets the center of the map
                            .tilt(0) // Sets the tilt of the camera to 0 degrees (topview)
                            .zoom(zoom) // Sets the zoom
                            .bearing(45) // Sets the orientation of the camera to northeast
                            .build();
                    cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                    googleMap.moveCamera(cameraUpdate);
                }
            });
        }
    }
}
