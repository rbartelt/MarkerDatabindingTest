package de.xxlstrandkorbverleih.markerdatabindingtest;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapFragmentViewModel extends androidx.lifecycle.ViewModel implements GoogleMap.OnMarkerClickListener {

    private String TAG = "MapFragmentViewModel";
    public MutableLiveData<LatLng> viewCentre = new MutableLiveData<>();
    public MutableLiveData<Integer> zoomlevel = new MutableLiveData<>();
    public MutableLiveData<List<Beachchair>> beachchairs = new MutableLiveData<>();
    public MutableLiveData<List<Beachchair>> selectedBeachchairs = new MutableLiveData<>();
    private List<Beachchair> list = new ArrayList<>();
    private List<Beachchair> selectedList = new ArrayList<>();
    Beachchair beachchair1 = new Beachchair(1, "Normal", new LatLng(54.01770492015488,14.069148800869902), false);
    Beachchair beachchair2 = new Beachchair(2, "XL", new LatLng(54.0178,14.069148800869902), false);



    public MapFragmentViewModel() {
        int i=1;
        double x=(double)1/30000;
        double y=(double)1/50000;
        double lat = 54.0177;
        double lon  = 14.069148800869902;
        while (i<200) {
            lat = lat+y;
            lon = lon-x;
            Beachchair beachchair = new Beachchair(i, "Normal", new LatLng(lat,lon), false);
            list.add(beachchair);
            i++;
        }
        viewCentre.setValue(getViewCenter(list));
        beachchairs.setValue(list);
        zoomlevel.setValue(20);
    }

    public LiveData getBeachchairs() {
        return beachchairs;
    }

    public void changeNumber() {
        beachchair1.setNumber(77);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        for (Beachchair bc:beachchairs.getValue()) {
            if (bc.getNumber()== Integer.valueOf(marker.getTitle())) {
                bc.setSelected(true);
                selectedList.add(bc);
            }
        }
        viewCentre.setValue(getViewCenter(selectedList));
        beachchairs.setValue(list);
        return false;
    }

    public void addBeachchair() {
        //add values to encapsulated data
        list.add(beachchair2);
        //need to call MutableLivedata.setValue() to inform Observers and update the UI
        beachchairs.setValue(list);
    }

    //returns the centerpoint of Beachchairlocations
    public LatLng getViewCenter(List<Beachchair> beachchairs) {
        double smallestLat=99;
        double greatestLat=0;
        double smallestLon=99;
        double greatestLon=0;

        for (Beachchair bc:beachchairs) {
            if (bc.getLocation().latitude < smallestLat)
                smallestLat = bc.getLocation().latitude;
            if (bc.getLocation().latitude > greatestLat)
                greatestLat = bc.getLocation().latitude;
            if (bc.getLocation().longitude < smallestLon)
                smallestLon = bc.getLocation().longitude;
            if (bc.getLocation().longitude > greatestLon)
                greatestLon = bc.getLocation().longitude;
        }
        double medLat = (greatestLat-smallestLat)+smallestLat;
        double medLon = (greatestLon-smallestLon)+smallestLon;
        return new LatLng(medLat,medLon);
    }
}
