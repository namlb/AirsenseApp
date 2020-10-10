package com.example.aqiapp.custom;

import android.view.MotionEvent;

import com.example.aqiapp.api.object.Aqi;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MyMarker extends Marker {
    private Aqi aqi;

    public MyMarker(MapView mapView) {
        super(mapView);
    }

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event, MapView mapView) {
        boolean touched = hitTest(event, mapView);
        if (touched){
            if (mOnMarkerClickListener == null){
                return onMarkerClickDefault(this, mapView);
            } else {
                return mOnMarkerClickListener.onMarkerClick(this, mapView);
            }
        } else
            return touched;
    }

    @Override
    public void setOnMarkerClickListener(OnMarkerClickListener listener) {
        super.setOnMarkerClickListener(listener);
        System.out.println("this is custom event" + aqi.getAqi());
    }
}
