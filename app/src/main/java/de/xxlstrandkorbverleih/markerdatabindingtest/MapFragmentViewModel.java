package de.xxlstrandkorbverleih.markerdatabindingtest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapFragmentViewModel extends androidx.lifecycle.ViewModel implements GoogleMap.OnMarkerClickListener {

    public MutableLiveData<List<Beachchair>> beachchairs = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedNumber = new MutableLiveData<>();
    private List<Beachchair> list = new ArrayList<>();
    Beachchair beachchair1 = new Beachchair(1, "Normal", new LatLng(14,14), true);
    Beachchair beachchair2 = new Beachchair(2, "XL", new LatLng(14.1,14.1), true);
    Beachchair beachchair3 = new Beachchair(3, "XXL", new LatLng(14.2, 14.2), true);


    public MapFragmentViewModel() {
        //Init some demo data
        list.add(beachchair1);
        list.add(beachchair2);
        list.add(beachchair3);
        beachchairs.setValue(list);
        selectedNumber.setValue(99);
    }

    public LiveData getBeachchairs() {
        return beachchairs;
    }

    public LiveData<Integer> getSelectedNumber() {
        return selectedNumber;
    }

    public void changeNumber() {
        beachchair1.setNumber(77);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        selectedNumber.setValue(Integer.valueOf(marker.getTitle()));
        return false;
    }
}
